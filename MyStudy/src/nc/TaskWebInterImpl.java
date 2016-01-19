package nc;

public class TaskWebInterImpl implements ITaskWebInter {

	@Override
	public void pushTask(String xmlinfo) throws Exception {
		TaskUtil.execAsyn(xmlinfo);
	}

	@Override
	public String execSync(String xmlInfo) throws Exception {
		String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?><params><uiurl>http://www.baidu.com</uiurl><context>aaaaa</context></params>";
		Object oRet = TaskUtil.execSync(xml);
		if (oRet != null)
			return oRet.toString();
		return "";
	}

}
