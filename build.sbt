name := """mada_aws"""
organization := "Inqsol"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

 scalaVersion := "2.13.12"

// esto estoy agregando
javaOptions += "--illegal-access=permit"
libraryDependencies += "org.dom4j" % "dom4j" % "2.1.4"


libraryDependencies ++= Seq(
 guice,
 play.sbt.PlayImport.filters
)

libraryDependencies ++= Seq(javaJdbc)
libraryDependencies ++= Seq("mysql" % "mysql-connector-java" % "8.0.32")
libraryDependencies ++= Seq(javaWs)
javacOptions ++= Seq("-Xlint:deprecation", "-Xlint:unchecked")


libraryDependencies += "org.apache.poi" % "poi" % "3.8"
libraryDependencies += "org.apache.poi" % "poi-ooxml" % "3.8"
libraryDependencies += "org.apache.poi" % "poi-scratchpad" % "3.8"
libraryDependencies += "commons-io" % "commons-io" % "2.4"

libraryDependencies += "org.apache.pdfbox" % "pdfbox" % "2.0.30"

libraryDependencies += "com.typesafe.play" %% "play-mailer" % "8.0.0"
libraryDependencies += "com.typesafe.play" %% "play-mailer-guice" % "8.0.0"

libraryDependencies += "fr.opensagres.xdocreport" % "org.apache.poi.xwpf.converter.pdf" % "1.0.1"
libraryDependencies += "com.google.zxing" % "core" % "2.0"

libraryDependencies += "software.amazon.awssdk" % "s3" % "2.13.29"


// Agregar configuraciones Java para manejar las advertencias
javacOptions ++= Seq(
 "--add-exports=java.base/sun.security.x509=ALL-UNNAMED",
 "--add-exports=java.base/sun.security.util=ALL-UNNAMED",
 "--add-opens=java.base/java.lang=ALL-UNNAMED",
 "--add-opens=java.base/java.util=ALL-UNNAMED"
)

// Agregar configuraciones para el runtime de Java
javaOptions ++= Seq(
 "-Dio.netty.tryReflectionSetAccessible=true"
)

import com.typesafe.sbt.packager.docker.DockerChmodType
dockerChmodType := DockerChmodType.UserGroupWriteExecute
dockerExposedPorts ++= Seq(9000)
dockerExposedVolumes := Seq("/opt/docker/run")
dockerBaseImage := "489478819445.dkr.ecr.us-west-2.amazonaws.com/amazoncorretto:24"
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