name := "baseapp"

version := "1.0"

lazy val `baseapp` = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq( javaJdbc ,  javaJpa, cache , javaWs,   "org.hibernate" % "hibernate-entitymanager" % "5.1.0.Final" )

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"  