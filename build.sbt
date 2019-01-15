name := "akka_blog_rest_api"

version := "0.1"

scalaVersion := "2.12.8"

lazy val akkaHttpVersion = "10.0.11"
lazy val akkaVersion = "2.5.11"
lazy val macwire = "2.3.0"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-xml" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,

  "com.typesafe.slick" %% "slick" % "3.2.3",
  "org.slf4j" % "slf4j-nop" % "1.7.25",

  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
  "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test,
  "org.scalatest" %% "scalatest" % "3.0.1" % Test,

  // Config file parser
  "com.github.pureconfig" %% "pureconfig" % "0.9.1",

  //Dependency Injection
  "com.softwaremill.macwire" %% "macros" % macwire,
  "com.softwaremill.macwire" %% "util" % macwire,

  // Support of CORS requests, version depends on akka-http
  "ch.megard" %% "akka-http-cors" % "0.3.0",

  // Postgres driver
  "org.postgresql" % "postgresql" % "42.1.4",

  "com.github.tminglei"         %% "slick-pg_spray-json"  % "0.16.3",
  "com.github.tminglei"         %% "slick-pg"             % "0.16.3",

  // Connection pool for database
  "com.zaxxer" % "HikariCP" % "2.7.0",
)