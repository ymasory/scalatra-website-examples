organization := "com.example"
name := "Scalatra Slick"
version := "0.1.0-SNAPSHOT"
scalaVersion := "2.11.8"

val ScalatraVersion = "2.5.+"

libraryDependencies ++= Seq(
  "org.scalatra"            %% "scalatra"          % ScalatraVersion,
  "org.scalatra"            %% "scalatra-scalate"  % ScalatraVersion,
  "org.scalatra"            %% "scalatra-specs2"   % ScalatraVersion    % "test",
  "com.typesafe.slick"      %% "slick"             % "3.2.0",
  "com.h2database"          %  "h2"                % "1.4.196",
  "com.mchange"             %  "c3p0"              % "0.9.5.2",
  "org.eclipse.jetty"       %  "jetty-webapp"      % "9.2.19.v20160908" % "provided",
  "javax.servlet"           %  "javax.servlet-api" % "3.1.0"            % "provided"
)

enablePlugins(JettyPlugin)
