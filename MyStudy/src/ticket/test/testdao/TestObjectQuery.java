package ticket.test.testdao;

import java.util.List;

public class TestObjectQuery {

	public static void main(String[] args) {
		TestDataSource ts = new TestDataSource();
		NCUserQuery usrQuery = new NCUserQuery(ts);
		List<NCUser> lstUser = usrQuery.findAllUser();
		for (NCUser usr : lstUser) {
			System.out.println(usr.getId() + "," + usr.getName());
		}
	}

}
