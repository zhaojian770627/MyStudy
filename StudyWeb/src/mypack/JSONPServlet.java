package mypack;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class JSONPServlet extends GenericServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {

		String func = request.getParameter("jsonp");
		String javaString = "aaa";
		response.getWriter().println(func + "('" + javaString + "')");
	}
}
