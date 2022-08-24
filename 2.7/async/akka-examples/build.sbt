organization := "com.example"
name := "Akka Examples"
version := "0.1.0-SNAPSHOT"
scalaVersion := "2.13.4"

val ScalatraVersion = "2.7.+"

libraryDependencies ++= Seq(
  "com.typesafe.akka"       %% "akka-actor"        % "2.6.10",
  "com.typesafe.akka"       %% "akka-http"         % "10.2.2",
  "org.scalaj"              %% "scalaj-http"       % "2.4.2",
  "ch.qos.logback"          %  "logback-classic"   % "1.2.3"            % Provided,
  "org.scalatra"            %% "scalatra"          % ScalatraVersion,
  "org.scalatra"            %% "scalatra-scalate"  % ScalatraVersion,
  "org.scalatra"            %% "scalatra-specs2"   % ScalatraVersion    % "test",
  "org.eclipse.jetty"       %  "jetty-webapp"      % "9.4.35.v20201120" % "provided",
  "javax.servlet"           %  "javax.servlet-api" % "3.1.0"            % "provided"
)

enablePlugins(JettyPlugin)
