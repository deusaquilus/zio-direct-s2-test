ThisBuild / scalaVersion := "2.13.8"
ThisBuild / organization := "org.deusaquilus"

lazy val `zio-direct-s2-test` =
  (project in file("."))
  .settings(
    resolvers ++= Seq(
      Resolver.mavenLocal,
      "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
      "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases"
    ),
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % "2.0.2",
      "dev.zio" %% "zio-direct" % "1.0.0-RC2"
    )
  )
