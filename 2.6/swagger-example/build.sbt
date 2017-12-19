organization := "com.example"
name := "Flowershop"
version := "0.1.0-SNAPSHOT"
scalaVersion := "2.12.4"

val ScalatraVersion = "2.6.+"

libraryDependencies ++= Seq(
  "org.json4s"              %% "json4s-native"     % "3.5.2",
  "org.scalatra"            %% "scalatra-swagger"  % ScalatraVersion,
  "org.scalatra"            %% "scalatra"          % ScalatraVersion,
  "org.scalatra"            %% "scalatra-scalate"  % ScalatraVersion,
  "org.scalatra"            %% "scalatra-specs2"   % ScalatraVersion    % Test,
  "org.eclipse.jetty"       %  "jetty-webapp"      % "9.4.6.v20170531"  % Provided,
  "javax.servlet"           %  "javax.servlet-api" % "3.1.0"            % Provided
)

enablePlugins(JettyPlugin)
