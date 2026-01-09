name := """mada_aws"""
organization := "Inqsol"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

 scalaVersion := "3.3.5"

javaOptions ++= Seq(
 "--illegal-access=permit",
 "-Dio.netty.tryReflectionSetAccessible=true"
)

javacOptions ++= Seq(
 "-Xlint:deprecation",
 "-Xlint:unchecked",
 "--add-exports=java.base/sun.security.x509=ALL-UNNAMED",
 "--add-exports=java.base/sun.security.util=ALL-UNNAMED",
 "--add-opens=java.base/java.lang=ALL-UNNAMED",
 "--add-opens=java.base/java.util=ALL-UNNAMED"
)

resolvers ++= Seq(
 Resolver.mavenLocal,
 Resolver.mavenCentral,
 "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/",
 "Play Repository" at "https://repo.playframework.org/maven/",
 "webjars" at "https://webjars.github.com/m2",
 "sonatype-snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
 "sonatype-releases"  at "https://oss.sonatype.org/content/repositories/releases"
)


libraryDependencies ++= Seq(
 guice,
 javaJdbc,
 javaWs,
 "com.mysql" % "mysql-connector-j" % "8.4.0",

 // --- PLAY MAILER & AWS & QR ---
 "org.playframework" %% "play-mailer" % "10.1.0",
 "org.playframework" %% "play-mailer-guice" % "10.1.0",
 "com.google.zxing" % "core" % "3.5.3",
 "com.google.zxing" % "javase" % "3.5.3",
 "software.amazon.awssdk" % "s3" % "2.25.15",

 // --- APACHE POI 5.2.5 ---
 "org.apache.poi" % "poi" % "5.2.5",
 "org.apache.poi" % "poi-ooxml" % "5.2.5",
 "org.apache.poi" % "poi-ooxml-full" % "5.2.5",
 "org.apache.xmlbeans" % "xmlbeans" % "5.1.1",

 // --- XDOCREPORT (Nombres correctos para Maven) ---
 // Este es el núcleo de XDocReport
 "fr.opensagres.xdocreport" % "fr.opensagres.xdocreport.core" % "2.0.4",
 // Estos son los conversores que funcionan con POI
 "fr.opensagres.xdocreport" % "fr.opensagres.poi.xwpf.converter.pdf" % "2.0.4",
 "fr.opensagres.xdocreport" % "fr.opensagres.poi.xwpf.converter.core" % "2.0.4",

 // --- DOCX4J ---
 "org.docx4j" % "docx4j-JAXB-ReferenceImpl" % "11.4.9",
 "org.docx4j" % "docx4j-export-fo" % "11.4.9",
 "jakarta.activation" % "jakarta.activation-api" % "2.1.2",
 "com.sun.activation" % "jakarta.activation" % "2.0.1",
 "javax.activation" % "activation" % "1.1.1",


 // PDF y relacionados
 "com.lowagie" % "itext" % "2.1.7",
 "org.apache.pdfbox" % "pdfbox" % "2.0.30",
 "org.apache.commons" % "commons-compress" % "1.21",

 // JSON
 "com.googlecode.json-simple" % "json-simple" % "1.1.1",
 "org.json" % "json" % "20231013",

 // Commons
 "commons-io" % "commons-io" % "2.11.0",
 "org.apache.commons" % "commons-lang3" % "3.12.0",
 "commons-codec" % "commons-codec" % "1.15",

 // Logging
 "org.slf4j" % "slf4j-api" % "2.0.7",
 "ch.qos.logback" % "logback-classic" % "1.4.11",
 "org.apache.logging.log4j" % "log4j-to-slf4j" % "2.20.0",

 // Testing
 "org.junit.jupiter" % "junit-jupiter-api" % "5.10.0" % Test,
 "org.junit.jupiter" % "junit-jupiter-engine" % "5.10.0" % Test,
 "org.mockito" % "mockito-core" % "5.5.0" % Test

)

// Resuelve conflictos de dependencias
dependencyOverrides ++= Seq(
 "org.apache.poi" % "poi" % "5.2.5",
 "org.apache.poi" % "poi-ooxml" % "5.2.5",
 "org.apache.poi" % "poi-ooxml-lite" % "5.2.5",
 "com.google.guava" % "guava" % "33.1.0-jre"
)

// ===== Docker / ECS Fargate =====
import com.typesafe.sbt.packager.docker.DockerChmodType
dockerChmodType := DockerChmodType.UserGroupWriteExecute
dockerExposedPorts ++= Seq(9000)
dockerExposedVolumes := Seq("/opt/docker/run")
dockerBaseImage := "489478819445.dkr.ecr.us-west-2.amazonaws.com/amazoncorretto:21"
dockerEntrypoint := Seq("/opt/docker/bin/mada_aws", "-Dpidfile.path=/opt/docker/run/RUNNING_PID", "-Dconfig.file=/opt/docker/conf/application.conf")
Docker / daemonUserUid := None
Docker / daemonUser := "nobody"


resolvers ++= Seq(
 "Maven Central" at "https://repo1.maven.org/maven2/",
 "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/",
 "webjars" at "https://webjars.github.com/m2",
 //"Atlassian Releases" at "https://maven.atlassian.com/public/",
 "sonatype-snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
 "sonatype-releases"  at "https://oss.sonatype.org/content/repositories/releases"
)

// ThisBuild / resolvers := Seq(Resolver.mavenCentral)


// ===== ECLIPSE (opcional) =====
// EclipseKeys.projectFlavor := EclipseProjectFlavor.Java
// EclipseKeys.createSrc := EclipseCreateSrc.ValueSet(EclipseCreateSrc.ManagedClasses, EclipseCreateSrc.ManagedResources)

