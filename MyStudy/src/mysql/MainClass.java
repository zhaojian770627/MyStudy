package mysql;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;

public class MainClass {

	public static void main(String[] args)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		String username = "root";
		String password = "1234";
		String url = "jdbc:mysql://10.10.71.101:3306/chat?useUnicode=true&characterEncoding=utf-8";
		String driver = "com.mysql.jdbc.Driver";

		String[] dbs = new String[] { "chat", "jan0226q", "kv44m66g", "q46yu5wz", "q4v4r2ki", "qk59sr36", "r5gumlrc",
				"rxjqedzo", "sqoy16xa", "sxvhovwv", "t1ex71tt", "trp1wo0y", "upvy65zj", "v77svmci", "vw9xwznp",
				"wfb0deol", "x8z0s8lv", "xayk3ux7", "xx9i3hpm", "z8tfmb1j", "zpqtux0l" };
		// String[] dbs = new String[] { "jbpm3" };
		Class.forName(driver).newInstance();
		Connection conn = null;
		Statement stmt = null;

		PrintWriter pw = new PrintWriter(System.err);

		
		
		for (String db : dbs) {
			try {
				File file = new File("D:\\Project\\ybz\\upcoderule.sql");
				FileReader fileReader = new FileReader(file);
				
				System.out.println("Execute " + db);
				conn = DriverManager.getConnection(url, username, password);
				stmt = conn.createStatement();
				ScriptRunner runner = new ScriptRunner(conn);

				runner.setErrorLogWriter(pw);
				runner.setLogWriter(pw);

				conn.setCatalog(db);
				// runner.runScript(Resources.getResourceAsReader("test.sql"));
				Resources.setCharset(Charset.forName("utf-8"));
				runner.runScript(fileReader);
				fileReader.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (stmt != null) {
						stmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
