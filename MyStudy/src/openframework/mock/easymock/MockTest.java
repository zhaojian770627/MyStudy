package openframework.mock.easymock;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.easymock.EasyMock;
import org.easymock.internal.MocksControl;

public class MockTest {

	public static void main(String[] args) throws SQLException {
		ResultSet mockResultSet = EasyMock.createMock(ResultSet.class);
		EasyMock.expect(mockResultSet.getString(1)).andReturn("abcd");
	}

}
