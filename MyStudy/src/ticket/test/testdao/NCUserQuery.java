package ticket.test.testdao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import ticket.framework.interface21.jdbc.object.ManualExtractionSqlQuery;

public class NCUserQuery extends ManualExtractionSqlQuery {

	public NCUserQuery(DataSource ds) {
		super(ds, "select cuserid as id,user_name as name from sm_user");
		compile();
	}

	@Override
	protected Object extract(ResultSet rs, int rownum) throws SQLException {
		NCUser user = new NCUser();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		return user;
	}
	
	public List findAllUser(){
		return execute();
	}

}
