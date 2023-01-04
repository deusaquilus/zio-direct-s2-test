package org.deusaquilus

import zio._
import zio.direct._

import java.sql.SQLException

object Main extends ZIOAppDefault {

  case class ConfigA(value: String)
  case class ConfigB(value: String)
  case class ConfigC(value: String)
  case class ConfigD(value: String)
  case class ConfigE(value: String)

  class ErrorA extends Exception("A")
  class ErrorB extends Exception("B")
  class ErrorC extends Exception("C")
  class ErrorD extends Exception("D")
  class ErrorE extends Exception("E")

  {
  val withAttempt =
    defer {
      val a = ZIO.service[ConfigA].run.value
      val b = ZIO.attempt("foo").run
      a
    }
  }

  {
    val altType = defer.info { val a = ZIO.service[ConfigA].run.value; a}
  }
  {
    val altType = defer.tpe {val a = ZIO.service[ConfigA].run.value; a}
  }
  {
    val altType = defer.verbose {val a = ZIO.service[ConfigA].run.value; a}
  }
  {
    val altType = defer.verboseTree {val a = ZIO.service[ConfigA].run.value; a}
  }

  val out =
    defer {
      val a = ZIO.service[ConfigA].run.value
      if (a != "foo") ZIO.fail(new SQLException("foo")).run
      val b = ZIO.service[ConfigB].run.value
      if (b != "bar") ZIO.fail(new IllegalArgumentException("bar")).run
      val c = {
        val z = defer(123)
        z
      }
      a + "bar"
    }










  {
    val out = {
      defer.info {
        val a = ZIO.service[ConfigA].run.value
        ZIO.fail(new ErrorA).run
        val bc = {
          val b = ZIO.service[ConfigB].run.value
          ZIO.fail(new ErrorB).run
          val cc =
            defer {
              val c = ZIO.service[ConfigC].run.value
              ZIO.fail(new ErrorC).run
              c
            }
          b + cc.run
        }
        val d = ZIO.service[ConfigD].run.value
        ZIO.fail(new ErrorD).run
        a + d
      }
    }
  }

  override def run = out
}
