//package classloader.a;
//
//import classloader.b.ICal;
//
//public class CalImp implements ICal {
//
//	@Override
//	public int add() {
//		System.out.println("---------------CalImp------------------");
//		System.out.println(getClass().getClassLoader().getClass().getName());
//		System.out.println(Thread.currentThread().getContextClassLoader().getClass().getName());
//		A1 a1 = new A1();
//		return a1.add();
//	}
//
//}
