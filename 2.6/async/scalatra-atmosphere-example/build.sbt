organization := "com.example"
name := "Scalatra Atmosphere Example"
version := "0.1.0-SNAPSHOT"
scalaVersion := "2.12.4"

val ScalatraVersion = "2.6.+"

libraryDependencies ++= Seq(
  "org.json4s"                  %% "json4s-jackson"      % "3.5.2",
  "org.scalatra"                %% "scalatra"            % ScalatraVersion,
  "org.scalatra"                %% "scalatra-scalate"    % ScalatraVersion,
  "org.scalatra"                %% "scalatra-specs2"     % ScalatraVersion    % "test",
  "org.scalatra"                %% "scalatra-atmosphere" % ScalatraVersion,
  "org.eclipse.jetty"           %  "jetty-webapp"        % "9.4.6.v20170531"  % "provided",
  "javax.servlet"               %  "javax.servlet-api"   % "3.1.0"            % "provided"
)

enablePlugins(JettyPlugin)
