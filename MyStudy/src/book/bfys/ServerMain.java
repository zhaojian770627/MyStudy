package book.bfys;

public class ServerMain {

	public static void main(String[] args) throws Exception {
		SimpleHttpServer server=new SimpleHttpServer();
		server.setBasePath("D:\\zj\\git\\MyStudy\\MyStudy\\src\\book\\bfys");
		System.out.println("HttpServer start");
		server.start();
	}

}
