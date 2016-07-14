package springweb.controller;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import springweb.service.SpitterService;

@Controller
@ManagedResource(objectName="spitter:name=HomeController")
public class HomeController {
	public static final int DEFAULT_SPITTLES_PER_PAGE = 25;

	private SpitterService spitterService;
	
	private int spittlesPerpage=DEFAULT_SPITTLES_PER_PAGE;

	@ManagedOperation
	public int getSpittlesPerpage() {
		return spittlesPerpage;
	}

	@ManagedOperation
	public void setSpittlesPerpage(int spittlesPerpage) {
		this.spittlesPerpage = spittlesPerpage;
	}

	@Inject
	public HomeController(SpitterService spitterService) {
		this.spitterService = spitterService;
	}

	@RequestMapping({"/","/home"})
	public String showHomePage(Map<String, Object> model) {
		model.put("spittles", spitterService.getRecentSpittles(DEFAULT_SPITTLES_PER_PAGE));
		return "home";
	}
}
