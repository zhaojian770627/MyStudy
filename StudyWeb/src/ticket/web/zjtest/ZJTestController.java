package ticket.web.zjtest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticket.web.servlet.ModelAndView;
import ticket.web.servlet.mvc.Controller;

public class ZJTestController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ZJView zv=new ZJView();
		return new ModelAndView(zv);
	}

}
