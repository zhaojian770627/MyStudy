package CodeDesigner.ui.model;


public class Data implements IDataItem {
	Object data;
	DataType dataType;
	@Override
	public void setDataType(DataType dataType) {		
		this.dataType=dataType;
	}

	@Override
	public void setValue(Object value) {
		this.data=value;
	}

	@Override
	public Object getValue() {
		return data;
	}

	@Override
	public DataType getDataType() {
		return dataType;
	}
	
	

}
