package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.derby.iapi.error.StandardException;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.impl.jdbc.EmbedConnection30;
import org.junit.Test;

import junit.framework.TestCase;

public class TestEmbeddedDerby extends TestCase {

	public static void main(String[] args) throws SQLException {
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		String dbName = "EmbeddedDB";
		String dbURL = "jdbc:derby:d:\\derby\\" + dbName + ";create=true";

		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(dbURL); // 启动嵌入式数据库
			Statement st = conn.createStatement();
			st.execute("create table foo (FOOID INT NOT NULL,FOONAME VARCHAR(30) NOT NULL)"); // 创建foo表
			st.executeUpdate("insert into foo(FOOID,FOONAME) values (1,'chinajash')"); // 插入一条数据
			ResultSet rs = st.executeQuery("select * from foo"); // 读取刚插入的数据
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				System.out.println("id=" + id + ";name=" + name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadData() throws ClassNotFoundException, SQLException {
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		String dbName = "EmbeddedDB";
		String dbURL = "jdbc:derby:d:\\derby\\" + dbName + ";create=true";

		Class.forName(driver);
		int i = DriverManager.getDriver(dbURL).getMajorVersion();
		Connection conn = DriverManager.getConnection(dbURL); // 启动嵌入式数据库
		Statement st = conn.createStatement();
		// st.execute("create table foo (FOOID INT NOT NULL,FOONAME
		// VARCHAR(30) NOT NULL)"); // 创建foo表
		// st.executeUpdate("insert into foo(FOOID,FOONAME) values
		// (1,'chinajash')"); // 插入一条数据
		ResultSet rs = st.executeQuery("select * from foo"); // 读取刚插入的数据
		while (rs.next()) {
			int id = rs.getInt(1);
			String name = rs.getString(2);
			System.out.println("id=" + id + ";name=" + name);
		}
		rs.close();
		st.close();
		conn.close();
	}

	@Test
	public void testGetDictionary() throws ClassNotFoundException, SQLException, StandardException {
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		String dbName = "EmbeddedDB";
		String dbURL = "jdbc:derby:d:\\derby\\" + dbName + ";create=true";

		Class.forName(driver);
		int i = DriverManager.getDriver(dbURL).getMajorVersion();
		Connection conn = DriverManager.getConnection(dbURL); // 启动嵌入式数据库
		if (conn instanceof EmbedConnection30) {
			EmbedConnection30 conn30 = (EmbedConnection30) conn;
			DataDictionary dict = conn30.getLanguageConnection().getDataDictionary();
			SchemaDescriptor sdCatalog = dict.getSchemaDescriptor("APP",
					conn30.getLanguageConnection().getTransactionCompile(), true);
			SchemaDescriptor sd = dict.getSystemSchemaDescriptor();
			TableDescriptor td = dict.getTableDescriptor("FOO", sdCatalog,conn30.getLanguageConnection().getTransactionCompile());
			System.out.println(sd.getUUID());
			i = 0;
		}
		conn.close();
	}
	
	@Test
	public void testOthers()  {
		System.out.println(Long.SIZE / Byte.SIZE);
	}

}
