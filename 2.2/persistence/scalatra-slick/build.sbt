organization := "org.dots42"

name := "Route Test"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.10.0"

seq(webSettings :_*)

classpathTypes ~= (_ + "orbit")

resolvers ++= Seq(
  "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository",
  "snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
  "releases"  at "http://oss.sonatype.org/content/repositories/releases")

resolvers += "Typesafe Snapshots" at "http://repo.typesafe.com/typesafe/snapshots/"

libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % "2.2.0-SNAPSHOT",
  "ch.qos.logback" % "logback-classic" % "1.0.6" % "runtime",
  "org.eclipse.jetty" % "jetty-webapp" % "8.1.7.v20120910" % "container",
  "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container;provided;test" artifacts (Artifact("javax.servlet", "jar", "jar")),
  "com.typesafe" % "slick_2.10" % "1.0.0-RC1",
  "com.h2database" % "h2" % "1.3.166",
  "c3p0" % "c3p0" % "0.9.1.2"
)
