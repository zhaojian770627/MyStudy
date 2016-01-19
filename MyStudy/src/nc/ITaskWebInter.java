package nc;


public interface ITaskWebInter {
	/**
	 * xml形式的消息
	 * 异步执行任务
	 * 
	 * @param xmlinfo
	 * @throws Exception
	 */
	void pushTask(String xmlinfo) throws Exception;
	
	/**
	 * 同步执行任务
	 * @param xmlInfo
	 * @return
	 * @throws Exception
	 */
	String execSync(String xmlInfo) throws Exception;
}
