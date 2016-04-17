package openframework.rabbitmq;

import java.io.IOException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class TestMQConsumer extends DefaultConsumer {
	Channel channel = null;
	String cousumerName = "";

	@Override
	public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
			throws IOException {
		String message = new String(body, "UTF-8");

		System.out.println(" [x] Received '" + message + "'");
		try {
			doWork(message);
		} finally {
			System.out.println(" [x] Done");
			channel.basicAck(envelope.getDeliveryTag(), false);
		}
	}

	private void doWork(String message) {
		System.out.println(this.cousumerName + "[x] Message: " + message);

	}

	public TestMQConsumer(String cousumerName, Channel channel) {
		super(channel);
		this.cousumerName = cousumerName;
		this.channel = channel;
		System.out.println(cousumerName + " instanced!");
	}

}
