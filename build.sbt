import sbt.Package.ManifestAttributes

scalaVersion := "2.12.8"

name := "vertx-scala-practice"
version := "1.0"

libraryDependencies += "io.vertx" %% "vertx-lang-scala" % "3.9.1"
libraryDependencies += "io.vertx" %% "vertx-web-scala" % "3.9.1"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.3" % Test
libraryDependencies += "junit"         %  "junit"     % "4.12"             % Test

packageOptions += ManifestAttributes(
  ("Main-Verticle", "scala:MainVerticle")
)

fork in run := true
outputStrategy := Some(StdoutOutput)