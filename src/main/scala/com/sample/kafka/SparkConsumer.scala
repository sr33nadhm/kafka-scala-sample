package com.sample.kafka

import org.apache.spark.sql.SparkSession

class SparkConsumer extends Thread {
  override def run(): Unit = {
    val spark = SparkSession.builder().master("local").appName("Spark Consumer")
      .config("bootstrap.servers", "localhost:9092")
      .config("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
      .config("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
      .config("group.id", "consumer-group")
      .config("auto.offset.reset", "latest")
      .config("enable.auto.commit", (false: java.lang.Boolean))
      .getOrCreate()

    try {
      while (true) {
        val df = spark
          .readStream
          .format("kafka")
          .option("kafka.bootstrap.servers", "localhost:9092")
          .option("subscribe", "scala_topic")
          .load()
        val personStringDF = df.selectExpr("CAST(value AS STRING)")
        personStringDF.writeStream
          .format("console")
          .outputMode("append")
          .start()
          .awaitTermination()
      }
    }
    catch {
      case e: Exception =>
        println("*************************************\n" +
          e
          + "\n*************************************")
    }
  }
}
