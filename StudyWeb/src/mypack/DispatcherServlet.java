package mypack;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class DispatcherServlet extends GenericServlet {
	private String target = "/helloresult.jsp";

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		request.setAttribute("USER", username);
		request.setAttribute("PASSWORD", password);
		
		HelloBean hb=new HelloBean();
		hb.setId("zj");
		hb.setName("zhaojian");

		request.setAttribute("helloBean", hb);
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher(target);
		dispatcher.forward(request, response);
	}
}
