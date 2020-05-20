name := "Phonebook"
 
version := "1.0"

scalaVersion := "2.13.2"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  guice,
  "com.typesafe.play" %% "play" % "2.8.1"
)

      