name := "play-java-intro"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava,PlayEbean)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  // If you enable PlayEbean plugin you must remove these
  // JPA dependencies to avoid conflicts.
  javaJpa,
  "org.hibernate" % "hibernate-entitymanager" % "4.3.7.Final"

)

libraryDependencies ++= Seq(
   javaJdbc,
  cache,
  javaWs,
  javaJpa,
  evolutions,
  jdbc,

  "org.postgresql" % "postgresql" % "9.4-1206-jdbc42"
)

