package com.sample.kafka

import org.apache.log4j.Logger
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.elasticsearch.hadoop.cfg.ConfigurationOptions

class SparkConsumer extends Thread {
  override def run(): Unit = {

    val logger = Logger.getLogger(this.getClass.getName)
    val sparkSession = SparkSession.builder()
      .config(ConfigurationOptions.ES_NODES, "127.0.0.1")
      .config(ConfigurationOptions.ES_PORT, "9200")
      .master("local[*]")
      .appName("sample-structured-streaming")

    try {
      val spark = sparkSession.getOrCreate()
      spark.sparkContext.setLogLevel("ERROR")
      val sparkDF = spark
        .readStream
        .format("kafka")
        .option("kafka.bootstrap.servers", "localhost:9092")
        .option("subscribe", "scala_topic")
        .option("auto.offset.reset", "latest")
        .option("group.id", "consumer-group-0")
        .option("failOnDataLoss","false")

      val df = sparkDF.load()
      val personStringDF = df.selectExpr("CAST(value AS STRING)")
      personStringDF.writeStream
        .outputMode("append")
        .format("org.elasticsearch.spark.sql")
        .option("checkpointLocation", "/tmp/new_dir/")
        .start("kafka-consumer-messages/doc-type")
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
