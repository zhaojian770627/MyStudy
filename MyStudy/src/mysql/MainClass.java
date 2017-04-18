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
				"wfb0deol", "x8z0s8lv", "xayk3ux7", "xx9i3hpm", "z8tfmb1j", "zpqtux0l", "ysdzxt76" };
		// String[] dbs = new String[] { "jbpm3" };
		Class.forName(driver).newInstance();
		Connection conn = null;
		Statement stmt = null;

		File file = new File("D:\\Project\\ybz\\upcoderule.sql");
		PrintWriter pwerror = new PrintWriter(System.err);
		PrintWriter pwlog = new PrintWriter(System.out);

		try {
			conn = DriverManager.getConnection(url, username, password);
			for (String db : dbs) {
				FileReader fileReader = new FileReader(file);

				System.out.println("Execute " + db);
				stmt = conn.createStatement();
				ScriptRunner runner = new ScriptRunner(conn);

				runner.setErrorLogWriter(pwerror);
				runner.setLogWriter(pwlog);

				conn.setCatalog(db);
				// runner.runScript(Resources.getResourceAsReader("test.sql"));
				Resources.setCharset(Charset.forName("utf-8"));
				runner.runScript(fileReader);
				fileReader.close();
				try {
					if (stmt != null) {
						stmt.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
