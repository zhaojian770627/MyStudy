package system.jvm.debug.c;

/**
 * �����Ŀ���ǲ���JVM����
 * ��c�ļ������ļ�����dll�ļ�
 * ��javaִ�д������£���c�����ͼ
 * 
 * 
 * @author zhaojianc
 *
 */
public class MethodTraceTest{

	public static void main(String[] args){
		MethodTraceTest test = new MethodTraceTest();
		test.first();
		test.second();
	}
	
	public void first(){
		System.out.println("=> Call first()");
	}
	
	public void second(){
		System.out.println("=> Call second()");
	}
}
