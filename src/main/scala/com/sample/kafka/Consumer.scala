package com.sample.kafka

import java.util
import java.util.Properties

import org.apache.kafka.clients.consumer.KafkaConsumer

import scala.collection.JavaConverters._
class Consumer extends Thread{
  override def run() = {
    val props = new Properties()
    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("auto.offset.reset", "latest")
    props.put("group.id", "consumer-group")
    val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](props)
    consumer.subscribe(util.Arrays.asList("scala_topic"))
    while (true) {
      val record = consumer.poll(5000).asScala
      for (data <- record.iterator) {
        println(data.key() + " : " + data.value())
      }
    }
  }
}