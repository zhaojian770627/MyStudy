package ticket.web.zjtest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticket.web.WebConstants;
import ticket.web.servlet.ModelAndView;
import ticket.web.servlet.mvc.multiaction.MultiActionController;

public class ZJTestMuliActionController extends MultiActionController implements WebConstants {
	public ModelAndView doTicket(HttpServletRequest request, HttpServletResponse response) {
		ZJView zv = new ZJView();
		return new ModelAndView(zv);
	}
}
