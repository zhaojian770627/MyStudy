package openframework.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * /** ��spring�����ļ���ȡ�õ���
 * 
 * @author chenzmb
 *
 */
public class MQConsumer {
	private MQConfigVO config;
	private String exchangename;
	private long recvwait = 3000;

	// private Channel channel;
	private Connection connection;

	// ���ն�������
	private String revQueue;

	// �������������У���ʱδ�ã�
	private String sscTaskQueue;

	// ��ִ��������
	private String sndQueue;

	// private ConsumeThread consumeThread;

	public String getSndQueue() {
		return sndQueue;
	}

	public void setSndQueue(String sndQueue) {
		this.sndQueue = sndQueue;
	}

	public MQConsumer() {

	}

	public String getSscTaskQueue() {
		return sscTaskQueue;
	}

	public void setSscTaskQueue(String sscTaskQueue) {
		this.sscTaskQueue = sscTaskQueue;
	}

	public String getRevQueue() {
		return revQueue;
	}

	public void setRevQueue(String revQueue) {
		this.revQueue = revQueue;
	}

	public static void main(String[] args) {
		// try {
		// MQAdmin mq = Variant.getInst().getMQAdmin();
		// mq.startRecv();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}

	@PostConstruct
	public void init() throws IOException, TimeoutException {
		// if (starStatus) {
		// return;
		// }

		if (config == null || exchangename == null) {
			throw new IOException("MQ������Ϣ��ȫ");
		}

		// �������Ӻ�Ƶ��
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(config.getHost());
		factory.setPort(config.getPort());
		factory.setUsername(config.getUser());
		factory.setPassword(config.getPwd());
		// factory.setConnectionTimeout( config.getTimeout()) ;

		connection = factory.newConnection();
		// channel = connection.createChannel();
		// ����ת����������
		// channel.exchangeDeclare(exchangename, "direct", true);
	}

	public void send(String msg) throws Exception {
		// if (starStatus) {
		Channel sndchannel = connection.createChannel();
		// sndchannel.exchangeDeclare(exchangename, "direct", true);
		sndchannel.basicPublish(exchangename, sndQueue, null, msg.getBytes());
		sndchannel.close();
		// }

	}

	/*
	 * û����һ�Σ�����һ���߳�
	 */
	public void startRecv() throws IOException {
		Channel revchannel = connection.createChannel();
		revchannel.basicQos(0);
		// ��������
		revchannel.txSelect();

		TestMQConsumer consumer = new TestMQConsumer("C1", revchannel,this.revQueue);
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();
		// �����߳�
		//revchannel.basicConsume(this.revQueue, false, new TestMQConsumer("C1", revchannel));
	}

	@PreDestroy
	public void close() throws IOException {
		// if (consumeThread != null) {
		// consumeThread.setRunStatus(false);
		// }
		// channel.close();
		connection.close();
	}

	public MQConfigVO getConfig() {
		return config;
	}

	public void setConfig(MQConfigVO config) {
		this.config = config;
	}

	public String getExchangename() {
		return exchangename;
	}

	public void setExchangename(String exchangename) {
		this.exchangename = exchangename;
	}

	public long getRecvwait() {
		return recvwait;
	}

	public void setRecvwait(long recvwait) {
		this.recvwait = recvwait;
	}

}
