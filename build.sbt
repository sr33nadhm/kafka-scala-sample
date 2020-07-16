name := "kafka-scala-sample"

version := "0.1"

scalaVersion := "2.11.0"

mainClass in (Compile,run) := Some("com.sample.kafka.MainClass")

libraryDependencies += "org.apache.kafka" % "kafka_2.11" % "2.4.1"
libraryDependencies += "org.apache.kafka" % "kafka-streams-scala_2.11" % "2.4.1"
libraryDependencies += "org.apache.spark" % "spark-streaming-kafka-0-10_2.11" % "2.4.6"
libraryDependencies += "org.apache.spark" % "spark-sql-kafka-0-10_2.11" % "2.4.6"
libraryDependencies += "org.apache.spark" % "spark-core_2.11" % "2.4.6"
libraryDependencies += "org.apache.spark" % "spark-sql_2.11" % "2.4.6"
libraryDependencies += "org.apache.spark" % "spark-streaming_2.11" % "2.4.6"
libraryDependencies += "org.elasticsearch" % "elasticsearch-spark-20_2.11" % "7.8.0"


//libraryDependencies += "org.apache.spark" % "spark-mllib_2.10" % "1.1.0"

libraryDependencies += "org.apache.logging.log4j" % "log4j-api" % "2.11.0"
libraryDependencies += "org.apache.logging.log4j" % "log4j-core" % "2.11.0"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.3" % Runtime