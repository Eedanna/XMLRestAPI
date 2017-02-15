package com.cs.rest.kafka;

import java.net.InetAddress;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.log4j.Logger;

import com.cs.rest.utils.Constants;

/**
 * The Class KafkaProduce
 * 
 */
public class KafkaProduce {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(KafkaProduce.class);

	/** The Constant KAFKA_STRING_SERIALIZER. */
	final static String KAFKA_STRING_SERIALIZER = "org.apache.kafka.common.serialization.StringSerializer";

	/**
	 * Send message to producer.
	 *
	 * @param topicName
	 *            the topic name
	 * @param message
	 *            the message
	 * @throws Exception
	 */
	public static void sendMessageToProducer(final String topic, final String message) throws Exception {

		Producer<String, String> producer = null;
		try {

			Properties props = new Properties();

			System.out.println("IP Address >>>>   " + InetAddress.getLocalHost().getHostAddress() + ":9092");

			props.put(Constants.BOOTSTRAP_SERVERS, InetAddress.getLocalHost().getHostAddress() + ":9092");

			props.put(Constants.ACKS, "all");

			props.put(Constants.RETRIES, 0);

			props.put(Constants.BATCH_SIZE, 16384);

			props.put(Constants.KEY_SERIALIZER, KAFKA_STRING_SERIALIZER);

			props.put(Constants.VALUE_KEY_SERIALIZER, KAFKA_STRING_SERIALIZER);

			Thread.currentThread().setContextClassLoader(null);

			producer = new KafkaProducer<String, String>(props);

			producer.send(new ProducerRecord<String, String>(topic, message));
			System.out.println("<<<< Message sent successfully >>>>");

		} catch (Exception ex) {
			LOGGER.info("Excception Occured due to :: " + ex.getStackTrace());
			throw ex;
		} finally {
			producer.close();
		}
	}
}
