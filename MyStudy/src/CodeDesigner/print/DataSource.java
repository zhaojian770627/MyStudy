package CodeDesigner.print;

/**
 * ���������Դ
 * 
 * @author zhaojianc
 * 
 */
public class DataSource {
	String[] tile = { "name", "age" };
	// ��ά�����ݣ�����ģ��
	String[][] data = { { "����", "20" }, { "����", "25" }, { "����", "30" } };
	
	// �õ�������
	public int getRecordCount(){
		return data.length;
	}
}
