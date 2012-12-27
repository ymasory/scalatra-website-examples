organization := "org.scalatra"

name := "org.scalatra.example"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.9.2"

classpathTypes ~= (_ + "orbit")

seq(webSettings :_*)

libraryDependencies ++= Seq(
  "org.scalatra" % "scalatra" % "2.2.0-RC2",
  "org.scalatra" % "scalatra-scalate" % "2.2.0-RC2",
  "org.scalatra" % "scalatra-specs2" % "2.2.0-RC2" % "test",
  "ch.qos.logback" % "logback-classic" % "1.0.6" % "runtime",
  "org.eclipse.jetty" % "jetty-webapp" % "8.1.7.v20120910" % "container",
  "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container;provided;test" artifacts (Artifact("javax.servlet", "jar", "jar"))
)

resolvers += "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"

seq(jelasticSettings:_*)

JelasticKeys.email in JelasticKeys.deploy := sys.env.get("JELASTIC_USERNAME").getOrElse(
sys error "Please export JELASTIC_USERNAME in your shell!"
)

JelasticKeys.password in JelasticKeys.deploy := sys.env.get("JELASTIC_PWD").getOrElse(
sys error "Please export JELASTIC_PWD in your shell!"
)

JelasticKeys.apiHoster := "app.jelastic.servint.net"

JelasticKeys.environment in JelasticKeys.deploy := "scalatra-test"

publishTo := None
