import sbt._
import Keys._
import org.scalatra.sbt._
import com.mojolly.scalate.ScalatePlugin._
import ScalateKeys._

object build extends Build {
  val Organization = "com.example"
  val Name = "Scalatra Atmosphere Example"
  val Version = "0.2.0-SNAPSHOT"
  val ScalaVersion = "2.11.1"
  val ScalatraVersion = "[2.4,)"
  val jettyVersion = "9.1.3.v20140225"
  val JacksonVersion = "3.2.11"

  lazy val project = Project (
    "atmosphere-example",
    file("."),
    settings =  Defaults.defaultSettings ++ ScalatraPlugin.scalatraFullSettings ++ scalateSettings ++ Seq(
      organization := Organization,
      name := Name,
      version := Version,
      scalaVersion := ScalaVersion,
      resolvers += "Scalaz Bintray" at "http://dl.bintray.com/scalaz/releases",
      libraryDependencies ++= Seq(
        "org.json4s"                  %% "json4s-jackson"      % JacksonVersion,
        "org.scalatra"                %% "scalatra"            % ScalatraVersion,
        "org.scalatra"                %% "scalatra-scalate"    % ScalatraVersion,
        "org.scalatra"                %% "scalatra-specs2"     % ScalatraVersion  % "test",
        "org.scalatra"                %% "scalatra-atmosphere" % ScalatraVersion,
        "ch.qos.logback"              %  "logback-classic"     % "1.1.1"          % "runtime",
        "org.eclipse.jetty"           %  "jetty-plus"          % jettyVersion     % "container;provided",
        "org.eclipse.jetty"           %  "jetty-webapp"        % jettyVersion     % "container",
        "org.eclipse.jetty.websocket" %  "websocket-server"    % jettyVersion     % "container;provided",
        "javax.servlet"               %  "javax.servlet-api"   % "3.1.0"          % "container;provided;test"
      ),
      scalateTemplateConfig in Compile <<= (sourceDirectory in Compile){ base =>
        Seq(
          TemplateConfig(
            base / "webapp" / "WEB-INF" / "templates",
            Seq.empty,  /* default imports should be added here */
            Seq.empty,  /* add extra bindings here */
            Some("templates")
          )
        )
      }
    )
  )
}
