package nc;


public interface ITaskWebInter {
	/**
	 * xml��ʽ����Ϣ
	 * �첽ִ������
	 * 
	 * @param xmlinfo
	 * @throws Exception
	 */
	void pushTask(String xmlinfo) throws Exception;
	
	/**
	 * ͬ��ִ������
	 * @param xmlInfo
	 * @return
	 * @throws Exception
	 */
	String execSync(String xmlInfo) throws Exception;
}
