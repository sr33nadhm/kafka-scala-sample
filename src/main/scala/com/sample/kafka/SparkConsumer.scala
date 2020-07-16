package com.sample.kafka

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession

class SparkConsumer extends Thread {
  override def run(): Unit = {

    val logger = Logger.getLogger(this.getClass.getName)
    val sparkSession = SparkSession.builder().master("local").appName("Spark Consumer")
      .config("bootstrap.servers", "localhost:9092")
      .config("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
      .config("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
      .config("group.id", "spark-consumer-group")
      .config("auto.offset.reset", "latest")
      .config("enable.auto.commit", (false: java.lang.Boolean))
    try {
      val spark = sparkSession.getOrCreate()
      spark.sparkContext.setLogLevel("ERROR")
      val sparkDF = spark
        .readStream
        .format("kafka")
        .option("kafka.bootstrap.servers", "localhost:9092")
        .option("subscribe", "scala_topic")

      val df = sparkDF.load()
      val personStringDF = df.selectExpr("CAST(value AS STRING)")
      personStringDF.writeStream
        .outputMode("append")
        .format("csv")
        .option("format", "append")
        .option("path", "/tmp/log4j/spark-consumer-messages/")
        .option("checkpointLocation", "/tmp/test")
        .outputMode("append")

        //        .queryName("writing_to_es")
        //        .format("org.elasticsearch.spark.sql")
        //        .option("checkpointLocation", "/tmp/")
        //        .option("es.resource", "index/type")
        //        .option("es.nodes", "localhost")

        .start()
        .awaitTermination()
    }
    catch {
      case e: Exception =>
        logger.error("*************************************\n" +
          e
          + "\n*************************************")
    }
  }
}
