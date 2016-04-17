package openframework.rabbitmq;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * 有关发消息的工作流类，只用于发消息
 * 
 * @author zhaojianc
 *
 */
public class TaskMQUtil {

	/**
	 * 处理类缓存
	 */
	private static class TaskUtilHolder {
		private static final TaskMQUtil INSTANCE = new TaskMQUtil();
	}

	public static final TaskMQUtil getInstance() {
		return TaskUtilHolder.INSTANCE;
	}

	/**
	 * 配置Bean
	 */
	MQConsumer maAdminbean;

	private TaskMQUtil() {
		// 初始化配置类工厂
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