package ejb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * 演示无状态会话Bean
 * 
 * @author zhaojian
 *
 */
public class HelloBean implements SessionBean {
	private SessionContext ctx;

	public void ejbCreate() {
		System.out.println("ejbCreate");
	}

	@Override
	public void ejbActivate() {
		System.out.println("ejbActivate");
	}

	@Override
	public void ejbPassivate() {
		System.out.println("ejbPassivate");

	}

	@Override
	public void ejbRemove() {
		System.out.println("ejbRemove");

	}

	@Override
	public void setSessionContext(SessionContext ctx) {
		this.ctx = ctx;
	}

	/**
	 * 业务方法
	 * 
	 * @return
	 */
	public String hello() {
		System.out.println("hello()");
		try {
			Context ctx = new InitialContext();
			DataSource datasource = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDS");
			Connection con = datasource.getConnection();
			Statement stat = con.createStatement();
			String count = "0";
			ResultSet rs = stat.executeQuery("select count(1) from sm_user");
			if (rs.next())
				count = rs.getString(1);
			rs.close();
			stat.close();
			con.close();
			return count;
		} catch (NamingException e) {
			return e.getMessage();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "Hello,World";
	}
}
