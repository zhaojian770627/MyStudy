package ticket.test.testdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ticket.framework.interface21.jdbc.core.JdbcTemplate;
import ticket.framework.interface21.jdbc.core.PreparedStatementCreator;
import ticket.framework.interface21.jdbc.core.RowCallbackHandler;

public class TestJDBCTemplate {

	public static void main(String[] args) {
		TestDataSource ts = new TestDataSource();
		JdbcTemplate jt = new JdbcTemplate(ts);
		final List<String> l = new LinkedList<String>();

		PreparedStatementCreator psc = new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement ps = conn.prepareStatement("select cuserid from sm_user");
				return ps;
			}
		};

		RowCallbackHandler rch = new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				String userid = rs.getString(1);
				l.add(userid);
			}
		};

		jt.query(psc, rch);
		System.out.println(l.size());
	}

}
