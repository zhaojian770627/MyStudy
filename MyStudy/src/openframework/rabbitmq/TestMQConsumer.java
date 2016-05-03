package openframework.rabbitmq;

import java.io.IOException;
import java.util.Date;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class TestMQConsumer extends DefaultConsumer implements Runnable {
	Channel channel = null;
	String cousumerName = "";
	String revQueueName = "";

	@Override
	public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
			throws IOException {
		String message = new String(body, "UTF-8");

		// System.out.println(" [x] Received '" + message + "'");
		try {
			doWork(message);
		} finally {
			// System.out.println(" [x] Done");
			channel.basicAck(envelope.getDeliveryTag(), false);
			// channel.txRollback();
			channel.txCommit();
		}
	}

	private void doWork(String message) {
		Date dt = new Date();
		System.out.println(this.cousumerName + " " + dt.getTime() + "[x] Message: " + message);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public TestMQConsumer(String cousumerName, Channel channel, String revQueue) {
		super(channel);
		this.cousumerName = cousumerName;
		this.channel = channel;
		this.revQueueName = revQueue;
		System.out.println(cousumerName + " instanced!");
	}

	@Override
	public void run() {
		try {
			channel.basicConsume(this.revQueueName, false, this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
