package ejb;

import javax.ejb.EJBLocalObject;

/**
 * Hello EJB组件的本地接口
 * 
 * 当本地客户通EJB本地对象交互时
 * 需要使用这一接口
 * 容器厂商会实现这一接口
 * 而相应的实现对象就是EJB本地对象
 * EJB本地对象会将客户请求委派给实际的EJB Bean类
 * 
 * @author zhaojian
 *
 */
public interface HelloLocal extends EJBLocalObject {
	public String hello();
}
