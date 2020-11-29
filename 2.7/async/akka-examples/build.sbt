organization := "com.example"
name := "Akka Examples"
version := "0.1.0-SNAPSHOT"
scalaVersion := "2.13.1"

val ScalatraVersion = "2.7.+"

libraryDependencies ++= Seq(
  "com.typesafe.akka"       %% "akka-actor"        % "2.5.23",
  "com.typesafe.akka"       %% "akka-http"         % "10.1.9",
  "org.scalaj"              %% "scalaj-http"       % "2.4.2",
  "org.scalatra"            %% "scalatra"          % ScalatraVersion,
  "org.scalatra"            %% "scalatra-scalate"  % ScalatraVersion,
  "org.scalatra"            %% "scalatra-specs2"   % ScalatraVersion    % "test",
  "org.eclipse.jetty"       %  "jetty-webapp"      % "9.4.19.v20190610" % "provided",
  "javax.servlet"           %  "javax.servlet-api" % "3.1.0"            % "provided"
)

enablePlugins(JettyPlugin)
