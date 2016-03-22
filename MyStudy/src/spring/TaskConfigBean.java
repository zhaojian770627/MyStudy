package spring;

import java.util.Map;

/**
 * 系统集成使用的配置类
 * 
 * @author zhaojianc
 * 
 */
public class TaskConfigBean {

	/**
	 * 外系统服务IP
	 */
	String sysWebSvrIp;

	/**
	 * 系统集成任务处理类对照 每个单据类型对应一个处理类 以本系统
	 */
	Map<String, String> taskProcessMap;

	/**
	 * 单据类型映射 主键：外系统单据类型, 值：本系统单据类型
	 */
	Map<String, String> billTypeMap;

	/**
	 * 组织影像 主键：外系统组织主键，值:本系统组织主键
	 */
	Map<String, String> orgMap;

	/**
	 * 系统接口对应
	 */
	Map<String, String> sysInterfaceMap;
	
	/**
	 * 系统集成的SSC启动流程用户
	 */
	String sscStartUser;	
	
	/**
	 * 用于驱动流程流转的单据类型，先暂时用单据类型，以后可能需要细分交易类型
	 */
	String sscWfBillType;


	/**
	 * 本系统账套
	 */
	String userDataSource;

	public Map<String, String> getOrgMap() {
		return orgMap;
	}

	public void setOrgMap(Map<String, String> orgMap) {
		this.orgMap = orgMap;
	}

	public String getSysWebSvrIp() {
		return sysWebSvrIp;
	}

	public void setSysWebSvrIp(String sysWebSvrIp) {
		this.sysWebSvrIp = sysWebSvrIp;
	}

	public Map<String, String> getTaskProcessMap() {
		return taskProcessMap;
	}

	public void setTaskProcessMap(Map<String, String> taskProcessMap) {
		this.taskProcessMap = taskProcessMap;
	}

	public Map<String, String> getBillTypeMap() {
		return billTypeMap;
	}

	public void setBillTypeMap(Map<String, String> billTypeMap) {
		this.billTypeMap = billTypeMap;
	}

	public String getUserDataSource() {
		return userDataSource;
	}

	public void setUserDataSource(String userDataSource) {
		this.userDataSource = userDataSource;
	}
	
	public Map<String, String> getSysInterfaceMap() {
		return sysInterfaceMap;
	}

	public void setSysInterfaceMap(Map<String, String> sysInterfaceMap) {
		this.sysInterfaceMap = sysInterfaceMap;
	}

	public String getSscStartUser() {
		return sscStartUser;
	}

	public void setSscStartUser(String sscStartUser) {
		this.sscStartUser = sscStartUser;
	}

	public String getSscWfBillType() {
		return sscWfBillType;
	}

	public void setSscWfBillType(String sscWfBillType) {
		this.sscWfBillType = sscWfBillType;
	}
}
