organization  := "rhrrecipe"

version       := "0.1"

scalaVersion  := "2.10.2"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers ++= Seq(
  "spray repo" at "http://repo.spray.io/"
)

libraryDependencies ++= {
  val akkaV = "2.1.4"
  val sprayV = "1.1.0"
  Seq(
    "io.spray"            %   "spray-can"     % sprayV,
    "io.spray"            %   "spray-routing" % sprayV,
    "io.spray"            %   "spray-testkit" % sprayV,
    "io.spray"            %  "spray-json_2.10"    % "1.2.5",
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV,
    "com.typesafe.slick"  %  "slick_2.10"     % "2.0.0",
    "ch.qos.logback"      % "logback-classic" % "1.0.13",
    "com.h2database"      %   "h2"            % "1.3.166",
    "org.xerial"          % "sqlite-jdbc"     % "3.7.2",
    "org.scalatest"       %%  "scalatest"     % "1.9.1",
    "org.specs2"          %%  "specs2"        % "2.2.3" % "test"
  )
}

seq(Revolver.settings: _*)
