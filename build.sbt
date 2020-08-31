import Dependencies._

ThisBuild / scalaVersion     := "2.13.2"
ThisBuild / version          := "0.1.0-SNAPSHOT"

lazy val SportDate = (project in file("."))
  .settings(
  )

libraryDependencies ++= Seq(
  scalaTest % Test,
  "com.github.nscala-time" %% "nscala-time" % "2.24.0",
)
// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
