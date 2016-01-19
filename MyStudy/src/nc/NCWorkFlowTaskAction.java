package nc;

import java.util.Map;

/**
 * 流程的插件处理类
 * 
 * @author zhaojianc
 * 
 */
public class NCWorkFlowTaskAction implements ITaskAction {

	@Override
	public Object doAction(TaskInfo taskInfo) throws Exception {

		/*
		if (!taskInfo.getTaskType().equals(TaskInfo.TASKTYPE_NCWF))
			throw new BusinessException("此插件无法处理此任务类型！");

		// 处理启动操作
		if (taskInfo.getTaskOperCode().endsWith(TaskInfo.TASKOPER_NCWF_START)) {
			processWFStart(taskInfo);
			return null;
		}

		throw new BusinessException("此插件无法处理此任务类型的"
				+ taskInfo.getTaskOperCode() + "操作");
		*/
		return null;
	}

	private void processWFStart(TaskInfo taskInfo) throws Exception {
		/*
		Object oData = taskInfo.getTaskData();
		if (!(oData instanceof MetaDataBaseAggVO)) {
			throw new BusinessException("此插件无法处理此任务类型的数据对象");
		}

		MetaDataBaseAggVO aggVo = (MetaDataBaseAggVO) oData;
		aggVo.getParentVO().setStatus(2);
		aggVo.getParentVO().setAttributeValue("approvestatus", -1);

		ICpCommonObjectService commonObjSrv = ServiceLocator
				.getService(ICpCommonObjectService.class);
		commonObjSrv.insertAggVO((MetaDataBaseAggVO) aggVo);

		String billType = (String) aggVo.getParentVO().getAttributeValue(
				"billtype");
		// 启动共享服务的流程，用于处理共享服务任务
		LfwPfUtil.start(aggVo, billType, null, null, null);
		*/
	}

	@Override
	public TaskInfo wrap(Map<String, Object> objAttr) throws Exception {
		/*
		// 所有对于此工作流的都转为此元数据对象
		String metadataID = "4b6e9b36-b9bf-11e5-a69f-951adf44198b";
		IBean bean = PfxxUtils.getMetaDataBeanByID(metadataID);
		NCObject ncvoObj = NCObject.newInstance(bean, null);
		Object o = ncvoObj.getContainmentObject();
		MetaDataBaseAggVO aggVO = (MetaDataBaseAggVO) o;

		nc.jdbc.framework.generator.IdGenerator idGenerator = NCLocator
				.getInstance().lookup(IdGenerator.class);

		String pk_freeform = idGenerator.generate();
		// 预置值
		aggVO.getParentVO().setAttributeValue("pk_freeform", pk_freeform);
		aggVO.getParentVO().setAttributeValue("pk_group",
				"0001F2100000000005FQ");
		aggVO.getParentVO().setAttributeValue("pk_org", "0001F210000000001A9T");
		aggVO.getParentVO().setAttributeValue("pk_doc", "0001Z01000000000V3MH");
		aggVO.getParentVO().setAttributeValue("billid", pk_freeform);
		aggVO.getParentVO().setAttributeValue("billno", pk_freeform);
		aggVO.getParentVO().setAttributeValue("billmaker",
				"1001F21000000000024T");
		aggVO.getParentVO().setAttributeValue("approvestatus", -1);
		aggVO.getParentVO().setAttributeValue("transtype", "inetsysbill");
		aggVO.getParentVO().setAttributeValue("billtype", "inetsysbill");
		aggVO.getParentVO().setAttributeValue("billstate", 1);
		aggVO.getParentVO().setAttributeValue("bean", bean);

		if (objAttr != null) {
			if (objAttr.containsKey("uiurl"))
				aggVO.getParentVO().setAttributeValue("uiurl",
						objAttr.get("uiurl"));
			if (objAttr.containsKey("context"))
				aggVO.getParentVO().setAttributeValue("context",
						objAttr.get("context"));
		}

		aggVO.getParentVO()
				.setAttributeValue("creator", "1001F21000000000024T");
		*/
		TaskInfo ti = new TaskInfo();
//		ti.setTaskID(UUID.randomUUID().toString());
//		ti.setTaskType(TaskInfo.TASKTYPE_NCWF);
//		ti.setTaskOperCode(TaskInfo.TASKOPER_NCWF_START);
//		ti.setTaskStatus("待处理");
//		ti.setTaskProcess("nc.ssc.task.NCWorkFlowTaskAction");
//		ti.setTaskData(aggVO);
//		ti.setTaskDescript("派单任务");
		return ti;
	}

}
