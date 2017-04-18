package openframework.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

public class MyRealm1 implements Realm {

	@Override
	public String getName() {
		return "myrealm1";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		// 仅支持 UsernamePasswordToken类型的Token
		return token instanceof UsernamePasswordToken;
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 得到用户名
		String username = (String) token.getPrincipal();
		// 得到密码
		String password = new String((char[]) token.getCredentials());

		if (!"zhang".equals(username)) {
			throw new UnknownAccountException();// 如果用户名错误
		}

		if (!"123".equals(password)) {
			throw new UnknownAccountException();// 如果用户名错误
		}

		return new SimpleAuthenticationInfo(username, password, getName());
	}

}
