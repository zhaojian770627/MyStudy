package nc;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * ����ִ�й�����
 * 
 * @author zhaojianc
 * 
 */
public class TaskUtil {

	/**
	 * �첽ִ������
	 * 
	 * @param xmlInfo
	 * @return
	 * @throws Exception
	 */
	public static void execAsyn(String xmlInfo) throws Exception {
		ITaskAction taskAction = getTaskProcessor("nc.ssc.task.NCWorkFlowTaskAction");
		TaskInfo ti = taskAction.wrap(null);
		DefaultTaskQueueProcessor.getInstance().addTask(ti);
	}

	/**
	 * ͬ��ִ������
	 * 
	 * @param xmlInfo
	 * @return
	 * @throws Exception
	 */
	public static Object execSync(String xmlInfo) throws Exception {
		ITaskAction taskAction = getTaskProcessor("nc.ssc.task.NCWorkFlowTaskAction");
		Map<String, Object> map = parseXml(xmlInfo);
		TaskInfo ti = taskAction.wrap(map);
		// ��Ҫ���õ�ǰ����
		//UserExit.getInstance().setGroupId("0001F2100000000005FQ");

		return taskAction.doAction(ti);
	}

	public static ITaskAction getTaskProcessor(String processClassName)
			throws Exception {
		Object taskObject = null;
		return null;
	}

	public static Map<String, Object> parseXml(String xmlInfo)
			throws DocumentException {
		Document doc = DocumentHelper.parseText(xmlInfo);
		Map<String, Object> attrsMaps = new HashMap<String, Object>();
		Element root = doc.getRootElement();
		iterateElement(root, attrsMaps);
		return attrsMaps;
	}

	private static void iterateElement(Element ele, Map<String, Object> map) {
		Iterator<Element> it = ele.elementIterator();
		boolean flag = it.hasNext();
		if (flag) {
			while (flag) {
				Element element = it.next();
				Map<String, Object> m = new HashMap<String, Object>();

				if (element.elementIterator().hasNext()) {

					if (map.containsKey(element.getName())) {
						map.put(element.getName() + new Date().getTime(), m);
					} else {
						map.put(element.getName(), m);
					}

					iterateElement(element, m);
				} else {
					map.put(element.getName(), element.getText());
				}
				flag = it.hasNext();
			}
		} else
			map.put(ele.getName(), ele.getText());
	}
}
