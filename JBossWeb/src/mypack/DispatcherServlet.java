package mypack;

import java.io.IOException;

import javax.ejb.CreateException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import ejb.HelloLocal;
import ejb.HelloLocalHome;

public class DispatcherServlet extends GenericServlet {
	private String target = "/helloresult.jsp";

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		request.setAttribute("USER", username);
		request.setAttribute("PASSWORD", password);

		HelloBean hb = new HelloBean();
		hb.setId("zj");
		hb.setName("zhaojian");

		try {
			InitialContext cts = new InitialContext();
			// HelloLocalHome home = (HelloLocalHome) cts.lookup("Hello");
			Object home = cts.lookup("java:comp/env/ejb/HelloLocal");
			HelloLocalHome LocalHome = (HelloLocalHome) home;
			HelloLocal hello = LocalHome.create();
			hb.setName(hello.hello());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CreateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("helloBean", hb);
		ServletContext context = getServletContext();

		RequestDispatcher dispatcher = context.getRequestDispatcher(target);
		dispatcher.forward(request, response);
	}
}
