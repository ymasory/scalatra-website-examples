import sbt._
import Keys._
import com.mojolly.scalate.ScalatePlugin._
import ScalateKeys._
import com.typesafe.sbt.SbtStartScript

object build extends Build {
  val Organization = "com.example"
  val Name = "Scalatra Atmosphere Embedded"
  val Version = "0.1.0-SNAPSHOT"
  val ScalaVersion = "2.11.1"
  val ScalatraVersion = "2.3.0"
  val jettyVersion = "9.1.3.v20140225"

  lazy val project = Project (
    "atmosphere-embedded",
    file("."),
    settings = SbtStartScript.startScriptForClassesSettings ++ Defaults.defaultSettings ++  scalateSettings ++ Seq(
      organization := Organization,
      name := Name,
      version := Version,
      scalaVersion := ScalaVersion,
      resolvers += "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/",
      resolvers += "Akka Repo" at "http://repo.akka.io/repository",
      libraryDependencies ++= Seq(
        "org.json4s"                  %% "json4s-jackson"      % "3.2.9",
        "org.scalatra"                %% "scalatra"            % ScalatraVersion,
        "org.scalatra"                %% "scalatra-scalate"    % ScalatraVersion,
        "org.scalatra"                %% "scalatra-specs2"     % ScalatraVersion   % "test",
        "org.scalatra"                %% "scalatra-atmosphere" % ScalatraVersion,
        "ch.qos.logback"              %  "logback-classic"     % "1.1.1"           % "runtime",
        "org.eclipse.jetty"           %  "jetty-plus"          % jettyVersion      % "compile;provided",
        "org.eclipse.jetty"           %  "jetty-webapp"        % jettyVersion      % "compile",
        "org.eclipse.jetty.websocket" %  "websocket-server"    % "9.1.3.v20140225" % "compile;provided",
        "javax.servlet"               %  "javax.servlet-api"   % "3.1.0"           % "compile;provided;test"
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
