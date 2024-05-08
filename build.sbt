name := """mada_aws"""
organization := "Inqsol"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.12"

libraryDependencies += guice

libraryDependencies ++= Seq(javaJdbc)
libraryDependencies ++= Seq("mysql" % "mysql-connector-java" % "8.0.32")
// libraryDependencies ++= Seq("mysql" % "mysql-connector-java" % "5.1.41")
libraryDependencies ++= Seq(javaWs)

libraryDependencies += "org.apache.poi" % "poi" % "3.8"
libraryDependencies += "org.apache.poi" % "poi-ooxml" % "3.8"
libraryDependencies += "org.apache.poi" % "poi-scratchpad" % "3.8"
libraryDependencies += "commons-io" % "commons-io" % "2.4"

libraryDependencies += "org.apache.pdfbox" % "pdfbox" % "2.0.24"

libraryDependencies += "com.typesafe.play" %% "play-mailer" % "8.0.0"
libraryDependencies += "com.typesafe.play" %% "play-mailer-guice" % "8.0.0"

libraryDependencies += "fr.opensagres.xdocreport" % "org.apache.poi.xwpf.converter.pdf" % "1.0.1"
libraryDependencies += "com.google.zxing" % "core" % "2.0"

libraryDependencies += "software.amazon.awssdk" % "s3" % "2.13.29"


import com.typesafe.sbt.packager.docker.DockerChmodType
dockerChmodType := DockerChmodType.UserGroupWriteExecute
dockerExposedPorts ++= Seq(9000)
dockerExposedVolumes := Seq("/opt/docker/run")
dockerBaseImage := "489478819445.dkr.ecr.us-west-2.amazonaws.com/amazoncorretto:11"
dockerEntrypoint := Seq("/opt/docker/bin/mada_aws", "-Dpidfile.path=/opt/docker/run/RUNNING_PID", "-Dconfig.file=/opt/docker/conf/application.conf")
Docker / daemonUserUid := None
Docker / daemonUser := "nobody"



resolvers ++= Seq(
 "webjars" at "https://webjars.github.com/m2",
 "Atlassian Releases" at "https://maven.atlassian.com/public/",
 "sonatype-snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
 "sonatype-releases"  at "https://oss.sonatype.org/content/repositories/releases"
)

EclipseKeys.projectFlavor := EclipseProjectFlavor.Java
EclipseKeys.createSrc := EclipseCreateSrc.ValueSet(EclipseCreateSrc.ManagedClasses, EclipseCreateSrc.ManagedResources)
// EclipseKeys.preTasks := Seq(compile in Compile)