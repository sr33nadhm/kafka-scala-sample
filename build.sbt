name := "kafka-scala-sample"

version := "0.1"

scalaVersion := "2.12.0"

mainClass in (Compile,run) := Some("com.sample.kafka.MainClass")

libraryDependencies += "org.apache.kafka" % "kafka_2.12" % "2.5.0"
libraryDependencies += "org.apache.kafka" % "kafka-streams-scala_2.12" % "2.5.0"
libraryDependencies += "org.apache.spark" % "spark-streaming-kafka-0-10_2.12" % "3.0.0-preview2"
libraryDependencies += "org.apache.spark" % "spark-sql-kafka-0-10_2.12" % "3.0.0-preview2"
libraryDependencies += "org.apache.spark" % "spark-core_2.12" % "3.0.0-preview2"
libraryDependencies += "org.apache.spark" % "spark-sql_2.12" % "3.0.0-preview2"
libraryDependencies += "org.apache.spark" % "spark-streaming_2.12" % "3.0.0-preview2"


//libraryDependencies += "org.apache.spark" % "spark-mllib_2.10" % "1.1.0"

//libraryDependencies += "org.apache.logging.log4j" % "log4j-api" % "2.13.3"
//libraryDependencies += "org.apache.logging.log4j" % "log4j-core" % "2.13.3"
//libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.3" % Runtime