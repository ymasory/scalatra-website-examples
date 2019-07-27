addSbtPlugin("org.scalatra.sbt" % "sbt-scalatra" % "1.0.3")
addSbtPlugin("com.typesafe.sbt" % "sbt-twirl"    % "1.4.2")

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")
