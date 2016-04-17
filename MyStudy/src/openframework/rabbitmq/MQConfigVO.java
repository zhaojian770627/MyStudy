package openframework.rabbitmq;

/**
 * MQµƒ≈‰÷√–≈œ¢bean
 * @author chenzmb
 *
 */
public class MQConfigVO {
	private String host;
	private int port;
	private String user;
	private String pwd;
	private int timeout;
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	} 
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	
	
	
}
