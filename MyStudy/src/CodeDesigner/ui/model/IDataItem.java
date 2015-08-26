package CodeDesigner.ui.model;

/**
 * КэѕЭПо
 * @author zhaojianc
 *
 */
public interface IDataItem {
	void setValue(Object value);
	Object getValue();
	void setDataType(DataType dataType);
	DataType getDataType();
}
