package CodeDesigner.print;

/**
 * 虚拟的数据源
 * 
 * @author zhaojianc
 * 
 */
public class DataSource {
	String[] tile = { "name", "age" };
	// 二维的数据，用于模拟
	String[][] data = { { "张三", "20" }, { "李四", "25" }, { "王五", "30" } };
	
	// 得到数据数
	public int getRecordCount(){
		return data.length;
	}
}
