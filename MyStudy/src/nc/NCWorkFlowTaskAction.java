package nc;

import java.util.Map;

/**
 * ���̵Ĳ��������
 * 
 * @author zhaojianc
 * 
 */
public class NCWorkFlowTaskAction implements ITaskAction {

	@Override
	public Object doAction(TaskInfo taskInfo) throws Exception {

		/*
		if (!taskInfo.getTaskType().equals(TaskInfo.TASKTYPE_NCWF))
			throw new BusinessException("�˲���޷�������������ͣ�");

		// ������������
		if (taskInfo.getTaskOperCode().endsWith(TaskInfo.TASKOPER_NCWF_START)) {
			processWFStart(taskInfo);
			return null;
		}

		throw new BusinessException("�˲���޷�������������͵�"
				+ taskInfo.getTaskOperCode() + "����");
		*/
		return null;
	}

	private void processWFStart(TaskInfo taskInfo) throws Exception {
		/*
		Object oData = taskInfo.getTaskData();
		if (!(oData instanceof MetaDataBaseAggVO)) {
			throw new BusinessException("�˲���޷�������������͵����ݶ���");
		}

		MetaDataBaseAggVO aggVo = (MetaDataBaseAggVO) oData;
		aggVo.getParentVO().setStatus(2);
		aggVo.getParentVO().setAttributeValue("approvestatus", -1);

		ICpCommonObjectService commonObjSrv = ServiceLocator
				.getService(ICpCommonObjectService.class);
		commonObjSrv.insertAggVO((MetaDataBaseAggVO) aggVo);

		String billType = (String) aggVo.getParentVO().getAttributeValue(
				"billtype");
		// ���������������̣����ڴ������������
		LfwPfUtil.start(aggVo, billType, null, null, null);
		*/
	}

	@Override
	public TaskInfo wrap(Map<String, Object> objAttr) throws Exception {
		/*
		// ���ж��ڴ˹������Ķ�תΪ��Ԫ���ݶ���
		String metadataID = "4b6e9b36-b9bf-11e5-a69f-951adf44198b";
		IBean bean = PfxxUtils.getMetaDataBeanByID(metadataID);
		NCObject ncvoObj = NCObject.newInstance(bean, null);
		Object o = ncvoObj.getContainmentObject();
		MetaDataBaseAggVO aggVO = (MetaDataBaseAggVO) o;

		nc.jdbc.framework.generator.IdGenerator idGenerator = NCLocator
				.getInstance().lookup(IdGenerator.class);

		String pk_freeform = idGenerator.generate();
		// Ԥ��ֵ
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
//		ti.setTaskStatus("������");
//		ti.setTaskProcess("nc.ssc.task.NCWorkFlowTaskAction");
//		ti.setTaskData(aggVO);
//		ti.setTaskDescript("�ɵ�����");
		return ti;
	}

}
