import sbt._
import Keys._
import org.scalatra.sbt._
import org.scalatra.sbt.PluginKeys._
import com.mojolly.scalate.ScalatePlugin._
import ScalateKeys._

object ScalatraGzipBuild extends Build {
  val Organization = "com.example"
  val Name = "Scalatra Gzip"
  val Version = "0.1.0-SNAPSHOT"
  val ScalaVersion = "2.11.7"
  val ScalatraVersion = "2.4.+"
  val jettyVersion = "9.1.5.v20140505"

  lazy val project = Project (
    "scalatra-gzip",
    file("."),
    settings = ScalatraPlugin.scalatraWithJRebel ++ scalateSettings ++ Seq(
      organization := Organization,
      name := Name,
      version := Version,
      scalaVersion := ScalaVersion,
      libraryDependencies ++= Seq(
        "org.scalatra"                %% "scalatra"            % ScalatraVersion,
        "org.scalatra"                %% "scalatra-scalate"    % ScalatraVersion,
        "org.scalatra"                %% "scalatra-specs2"     % ScalatraVersion  % "test",
        "ch.qos.logback"              %  "logback-classic"     % "1.1.3"          % "runtime",
        "org.eclipse.jetty"           %  "jetty-plus"          % jettyVersion     % "container;provided",
        "org.eclipse.jetty"           %  "jetty-webapp"        % jettyVersion     % "container",
        "org.eclipse.jetty.websocket" %  "websocket-server"    % jettyVersion     % "container;provided",
        "javax.servlet"               %  "javax.servlet-api"   % "3.1.0"          % "container;provided;test" artifacts Artifact("javax.servlet-api", "jar", "jar")
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
