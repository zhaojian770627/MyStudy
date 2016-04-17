package openframework.rabbitmq;

import java.io.IOException;
import java.util.Date;

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

		//System.out.println(" [x] Received '" + message + "'");
		try {
			doWork(message);
		} finally {
			//System.out.println(" [x] Done");
			channel.basicAck(envelope.getDeliveryTag(), false);
		}
	}

	private void doWork(String message) {
		Date dt = new Date();
		System.out.println(this.cousumerName + " " + dt.getTime() + "[x] Message: " + message);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public TestMQConsumer(String cousumerName, Channel channel) {
		super(channel);
		this.cousumerName = cousumerName;
		this.channel = channel;
		System.out.println(cousumerName + " instanced!");
	}

}
