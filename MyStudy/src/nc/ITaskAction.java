package nc;

import java.util.Map;

/**
 * 具体的任务处理组件
 * @author zhaojianc
 *
 */
public interface ITaskAction {
	/**
	 * 任务处理方法
	 * @param taskInfo
	 * @throws Exception
	 */
	Object doAction(TaskInfo taskInfo) throws Exception;
	
	/**
	 * 对传递捡来的数据进行包装，包装成任务对象，相当于协议转换
	 * @param objAttr
	 * @return
	 * @throws Exception
	 */
	TaskInfo wrap(Map<String,Object> objAttr) throws Exception;
}
