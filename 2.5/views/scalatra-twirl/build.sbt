organization := "com.example"
name := "Scalatra Twirl"
version := "0.1.0-SNAPSHOT"
scalaVersion := "2.11.8"

val ScalatraVersion = "2.5.+"

libraryDependencies ++= Seq(
  "org.scalatra"            %% "scalatra"          % ScalatraVersion,
  "org.scalatra"            %% "scalatra-specs2"   % ScalatraVersion    % "test",
  "org.eclipse.jetty"       %  "jetty-webapp"      % "9.2.19.v20160908" % "provided",
  "javax.servlet"           %  "javax.servlet-api" % "3.1.0"            % "provided"
)

enablePlugins(SbtTwirl)
enablePlugins(JettyPlugin)
