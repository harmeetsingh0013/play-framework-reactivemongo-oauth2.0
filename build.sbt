name := """play-scala"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

resolvers += "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

EclipseKeys.createSrc := EclipseCreateSrc.All

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "org.reactivemongo" % "reactivemongo_2.11" % "0.10.5.0.akka23",
  "org.reactivemongo" % "reactivemongo-extensions-json_2.11" % "0.10.5.0.0.akka23",
  "org.reactivemongo" % "reactivemongo-extensions-bson_2.11" % "0.10.5.0.0.akka23",
  "org.scaldi" %% "scaldi-play" % "0.5.3",
  "com.nulab-inc" %% "play2-oauth2-provider" % "0.13.3",
  "be.objectify" %% "deadbolt-scala" % "2.3.3",
  "jp.t2v" %% "play2-auth"      % "0.13.2",
  "jp.t2v" %% "play2-auth-test" % "0.13.2" % "test"
)


fork in run := true