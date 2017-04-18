package openframework.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import junit.framework.Assert;

public class TestShiro {
	@Test
	public void testIni() {
		String filepath="E:\\GitWorkspace\\MyStudy\\MyStudy\\target\\classes\\openframework\\shiro\\shiro.ini";
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(filepath);
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

		try {
			subject.login(token);
		} catch (AuthenticationException e) {

		}

		Assert.assertTrue(subject.isAuthenticated());
		subject.logout();
	}
	
	@Test
    public void testJDBCRealm() {
        //1����ȡSecurityManager�������˴�ʹ��Ini�����ļ���ʼ��SecurityManager
        Factory<org.apache.shiro.mgt.SecurityManager> factory =
                new IniSecurityManagerFactory("E:\\GitWorkspace\\MyStudy\\MyStudy\\target\\classes\\openframework\\shiro\\shiro-jdbc-realm.ini");

        //2���õ�SecurityManagerʵ�� ���󶨸�SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3���õ�Subject�������û���/���������֤Token�����û����/ƾ֤��
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

        try {
            //4����¼���������֤
            subject.login(token);
        } catch (AuthenticationException e) {
            //5�������֤ʧ��
            e.printStackTrace();
        }

        Assert.assertEquals(true, subject.isAuthenticated()); //�����û��Ѿ���¼

        //6���˳�
        subject.logout();
    }
}
