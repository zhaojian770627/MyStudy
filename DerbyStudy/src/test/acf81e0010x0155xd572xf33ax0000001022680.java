package test;

import java.util.Vector;

import org.apache.derby.iapi.error.StandardException;
import org.apache.derby.iapi.sql.ResultSet;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.impl.sql.execute.BaseActivation;
import org.apache.derby.impl.sql.execute.CursorActivation;

public final class acf81e0010x0155xd572xf33ax0000001022680 extends CursorActivation {
	private ExecRow e0;
	private Qualifier[][] e1;
	private static int getExecutionCount;
	private static Vector getRowCountCheckVector;
	private static int getStalePlanCheckInterval;

	public void postConstructor()
    throws StandardException
  {
    this.e0 = super.getExecutionFactory().getValueRow(2);
    this.row = new ExecRow[2];
  }

	public ResultSet execute() throws StandardException {
		super.throwIfClosed("execute");
		super.startExecution();
		BaseActivation.reinitializeQualifiers(this.e1);
		ResultSet tmp41_38 = ((this.resultSet == null) ? (this.resultSet = fillResultSet()) : this.resultSet);
		((NoPutResultSet) tmp41_38).markAsTopResultSet();
		return tmp41_38;
	}

	private ResultSet fillResultSet() throws StandardException {
		super.getLanguageConnectionContext().getAuthorizer().authorize(this, 1);
		return super.getResultSetFactory().getScrollInsensitiveResultSet(
				super.getResultSetFactory().getBulkTableScanResultSet(this, 1024, 1, super.getMethod("e0"), 1, null, -1,
						null, -1, true, this.e1, "FOO", null, null, false, false, -1, -1, 7, false, 0, 16, false, 7.0D,
						59.564D),
				this, 0, 2, super.getScrollable(), 7.0D, 59.564D);
	}

	public Object e0() throws StandardException {
		this.e0.setColumn(1, super.getDataValueFactory().getNullInteger(null));
		this.e0.setColumn(2, super.getDataValueFactory().getNullVarchar(null));
		return this.e0;
	}

	protected final int getExecutionCount() {
		return getExecutionCount;
	}

	protected final void setExecutionCount(int paramInt) {
		getExecutionCount = paramInt;
	}

	protected final Vector getRowCountCheckVector() {
		return getRowCountCheckVector;
	}

	protected final void setRowCountCheckVector(Vector paramVector) {
		getRowCountCheckVector = paramVector;
	}

	protected final int getStalePlanCheckInterval() {
		return getStalePlanCheckInterval;
	}

	protected final void setStalePlanCheckInterval(int paramInt) {
		getStalePlanCheckInterval = paramInt;
	}
}