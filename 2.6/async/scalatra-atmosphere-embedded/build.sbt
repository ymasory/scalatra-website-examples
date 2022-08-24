import com.typesafe.sbt.SbtNativePackager.autoImport.NativePackagerHelper._

organization := "org.scalatra.example"
name := "Scalatra Atmosphere Embedded"
version := "0.1.0-SNAPSHOT"
scalaVersion := "2.12.4"

val ScalatraVersion = "2.6.+"
val jettyVersion = "9.4.7.v20170914"

libraryDependencies ++= Seq(
  "org.json4s"                  %% "json4s-jackson"      % "3.5.3",
  "org.scalatra"                %% "scalatra"            % ScalatraVersion,
  "org.scalatra"                %% "scalatra-scalate"    % ScalatraVersion,
  "org.scalatra"                %% "scalatra-specs2"     % ScalatraVersion   % Test,
  "org.scalatra"                %% "scalatra-atmosphere" % ScalatraVersion,
  "org.eclipse.jetty"           %  "jetty-webapp"        % jettyVersion      % Compile,
  "org.eclipse.jetty.websocket" %  "websocket-server"    % jettyVersion      % "compile;provided",
  "ch.qos.logback"              %  "logback-classic"     % "1.2.3",
  "javax.servlet"               %  "javax.servlet-api"   % "3.1.0"
)

enablePlugins(ScalatraPlugin, JavaServerAppPackaging)
