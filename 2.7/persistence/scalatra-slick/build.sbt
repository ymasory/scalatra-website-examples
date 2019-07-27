organization := "org.scalatra.slickexample"
name := "Scalatra Slick"
version := "0.1.0-SNAPSHOT"
scalaVersion := "2.13.0"

val ScalatraVersion = "2.7.+"

libraryDependencies ++= Seq(
  "org.scalatra"            %% "scalatra"          % ScalatraVersion,
  "org.scalatra"            %% "scalatra-scalate"  % ScalatraVersion,
  "org.scalatra"            %% "scalatra-specs2"   % ScalatraVersion    % "test",
  "com.typesafe.slick"      %% "slick"             % "3.3.2",
  "com.h2database"          %  "h2"                % "1.4.199",
  "com.mchange"             %  "c3p0"              % "0.9.5.4",
  "ch.qos.logback"          %  "logback-classic"   % "1.2.3"             % "provided",
  "org.eclipse.jetty"       %  "jetty-webapp"      % "9.4.19.v20190610"  % "provided",
  "javax.servlet"           %  "javax.servlet-api" % "3.1.0"             % "provided"
)

enablePlugins(ScalatraPlugin)
