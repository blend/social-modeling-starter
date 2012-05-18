import sbt._
import Keys._

import org.ensime.sbt.Plugin.Settings.ensimeConfig
import org.ensime.sbt.util.SExp._

object SocialModelingStarterBuild extends Build {
  val appVersion = "0.1"

  val appResolvers = Seq(
    "Maven Repository" at "http://repo1.maven.org/maven2/",
    "cloudera" at "https://repository.cloudera.com/artifactory/cloudera-repos/"
  )

  val commands = """
  import com.blendlabsinc.shell.Commands._
"""

  val appDependencies = Seq(
    "org.specs2" %% "specs2" % "1.8.2",
    "net.databinder" %% "dispatch-http" % "0.8.8",
    "net.databinder" %% "dispatch-json" % "0.8.8",
    "net.databinder" %% "dispatch-lift-json" % "0.8.5",
    "com.gravity" % "gravity-hpaste" % "0.1.5",
    "org.apache.hbase" % "hbase" % "0.92.1-cdh4.0.0b2",
    "org.apache.hadoop" % "hadoop-core" % "0.20.2-cdh3u4"
  )

  lazy val social_modeling = Project(id = "social-modeling-starter",
                                     base = file("."),
                                     settings = Project.defaultSettings ++ Seq(
                                       libraryDependencies ++= appDependencies,
                                       resolvers ++= appResolvers,
                                       initialCommands := commands
                                     ))
}
