import sbt._
import Keys._

import org.ensime.sbt.Plugin.Settings.ensimeConfig
import org.ensime.sbt.util.SExp._

object SocialModelingStarterBuild extends Build {
  val appVersion = "0.1"

  val ensimeConfig_ = sexp(
    key(":formatting-prefs"), sexp(
      key(":alignParameters"), true,
      key(":preserveDanglingCloseParenthesis"), true
    )
  )

  val appResolvers = Seq(
    "Maven Repository" at "http://repo1.maven.org/maven2/"
  )

  val liftVersion = "2.4" // Put the current/latest lift version here

  val appDependencies = Seq(
    "org.specs2" %% "specs2" % "1.8.2",
    "net.databinder" %% "dispatch-http" % "0.8.8",
    "net.databinder" %% "dispatch-json" % "0.8.8",
    "net.databinder" %% "dispatch-lift-json" % "0.8.5"
  )

  lazy val common = Project(id = "social-modeling-starter",
                            base = file("."),
                            settings = Project.defaultSettings ++ Seq(
                              libraryDependencies ++= appDependencies,
                              resolvers ++= appResolvers
                            ))
}
