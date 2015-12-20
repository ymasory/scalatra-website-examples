import sbt._
import Keys._
import org.scalatra.sbt._
import org.scalatra.sbt.PluginKeys._
import com.mojolly.scalate.ScalatePlugin._
import ScalateKeys._

object ScentryauthdemoBuild extends Build {
  val Organization = "com.constructiveproof"
  val Name = "ScentryAuthDemo"
  val Version = "0.1.0-SNAPSHOT"
  val ScalaVersion = "2.11.7"
  val ScalatraVersion = "2.4.0"
  val jettyVersion = "9.1.5.v20140505"

  lazy val project = Project (
    "scentryauthdemo",
    file("."),
    settings = ScalatraPlugin.scalatraWithJRebel ++ scalateSettings ++ Seq(
      organization := Organization,
      name := Name,
      version := Version,
      scalaVersion := ScalaVersion,
      libraryDependencies ++= Seq(
        "org.scalatra"                %% "scalatra"            % ScalatraVersion,
        "org.scalatra"                %% "scalatra-scalate"    % ScalatraVersion,
        "org.scalatra"                %% "scalatra-auth"       % ScalatraVersion,
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
            Seq(
              Binding("context", "_root_.org.scalatra.scalate.ScalatraRenderContext", importMembers = true, isImplicit = true)
            ),  /* add extra bindings here */
            Some("templates")
          )
        )
      }
    )
  )
}
