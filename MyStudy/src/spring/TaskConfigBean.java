package spring;

import java.util.Map;

public class TaskConfigBean {
	String sysWebSvrIp;
	Map<String,String> processMap;
	
	public Map<String, String> getProcessMap() {
		return processMap;
	}

	public void setProcessMap(Map<String, String> processMap) {
		this.processMap = processMap;
	}

	public String getSysWebSvrIp() {
		return sysWebSvrIp;
	}

	public void setSysWebSvrIp(String sysWebSvrIp) {
		this.sysWebSvrIp = sysWebSvrIp;
	}
}
