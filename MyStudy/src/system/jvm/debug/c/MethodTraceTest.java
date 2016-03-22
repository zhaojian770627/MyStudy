package system.jvm.debug.c;

/**
 * 此类的目的是测试JVM调试
 * 用c文件夹下文件建立dll文件
 * 用java执行代码如下，见c下面的图
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
