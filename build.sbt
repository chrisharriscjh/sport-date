import Dependencies._

ThisBuild / scalaVersion     := "2.13.2"
ThisBuild / version          := "0.1.0"

lazy val sportsdate = (project in file("."))
  .settings(
  )

libraryDependencies ++= Seq(
  scalaTest % Test,
  "com.github.nscala-time" %% "nscala-time" % "2.24.0",
)
