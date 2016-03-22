package spring;

import java.util.Map;

/**
 * ϵͳ����ʹ�õ�������
 * 
 * @author zhaojianc
 * 
 */
public class TaskConfigBean {

	/**
	 * ��ϵͳ����IP
	 */
	String sysWebSvrIp;

	/**
	 * ϵͳ��������������� ÿ���������Ͷ�Ӧһ�������� �Ա�ϵͳ
	 */
	Map<String, String> taskProcessMap;

	/**
	 * ��������ӳ�� ��������ϵͳ��������, ֵ����ϵͳ��������
	 */
	Map<String, String> billTypeMap;

	/**
	 * ��֯Ӱ�� ��������ϵͳ��֯������ֵ:��ϵͳ��֯����
	 */
	Map<String, String> orgMap;

	/**
	 * ϵͳ�ӿڶ�Ӧ
	 */
	Map<String, String> sysInterfaceMap;
	
	/**
	 * ϵͳ���ɵ�SSC���������û�
	 */
	String sscStartUser;	
	
	/**
	 * ��������������ת�ĵ������ͣ�����ʱ�õ������ͣ��Ժ������Ҫϸ�ֽ�������
	 */
	String sscWfBillType;


	/**
	 * ��ϵͳ����
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
