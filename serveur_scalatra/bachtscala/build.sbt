val ScalatraVersion = "2.7.0-RC1"

organization := "be.mobile"

name := "backend_scala"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.12.10"

resolvers += Classpaths.typesafeReleases

libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % ScalatraVersion,
  "org.scalatra" %% "scalatra-scalatest" % ScalatraVersion % "test",
  "ch.qos.logback" % "logback-classic" % "1.2.3" % "runtime",
  "org.eclipse.jetty" % "jetty-webapp" % "9.4.19.v20190610" % "container",
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
  "org.mongodb"             %% "casbah"            % "3.1.1",
  "org.json4s"              %% "json4s-mongo"      % "3.5.2",
  "org.scalatra" %% "scalatra-json" % ScalatraVersion,
  "org.json4s"   %% "json4s-jackson" % "3.5.2",
  "com.lihaoyi" %% "os-lib" % "0.4.2",
  
)

enablePlugins(SbtTwirl)
enablePlugins(ScalatraPlugin)
