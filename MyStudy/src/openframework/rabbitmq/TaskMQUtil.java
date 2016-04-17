package openframework.rabbitmq;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * �йط���Ϣ�Ĺ������ֻ࣬���ڷ���Ϣ
 * 
 * @author zhaojianc
 *
 */
public class TaskMQUtil {

	/**
	 * �����໺��
	 */
	private static class TaskUtilHolder {
		private static final TaskMQUtil INSTANCE = new TaskMQUtil();
	}

	public static final TaskMQUtil getInstance() {
		return TaskUtilHolder.INSTANCE;
	}

	/**
	 * ����Bean
	 */
	MQConsumer maAdminbean;

	private TaskMQUtil() {
		// ��ʼ�������๤��
		// String mqconfilg = RuntimeEnv.getInstance().getNCHome()
		// + "\\modules\\sscbd\\META-INF\\ssctaskmqcfg.xml";
		String mqconfilg = "sscsndmqcfg.xml";

		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinitions(new ClassPathResource(mqconfilg, TaskMQUtil.class));

		maAdminbean = (MQConsumer) factory.getBean("mqAdmin");
		if (maAdminbean == null) {
		}
	}

	public void sendMQMsg(String msg) throws Exception {
		if (maAdminbean != null) {
			maAdminbean.send(msg);
		}
	}
}