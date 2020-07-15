package com.sample.kafka

object MainClass {
  def main(args: Array[String]): Unit = {

// Creating Streams for topic
    var stream = new Stream()
    stream.start()

// Spark Consumer for Kafka Streams
    var sparkConsumer = new SparkConsumer()
    sparkConsumer.start()

// Kafka Consumer for Kafka Streams

//    var consumer=new Consumer()
//    consumer.start()
  }
}
