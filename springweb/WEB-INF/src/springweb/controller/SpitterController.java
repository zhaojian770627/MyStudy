package springweb.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springweb.model.Spitter;
import springweb.service.SpitterService;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping("/spitter")	// ��URL·��
public class SpitterController {
	
	private final SpitterService spitterService;
	
	@Inject
	public SpitterController(SpitterService spitterService){
		this.spitterService=spitterService;
	}
	
	@RequestMapping(value="/spittles",method=GET)
	public String listSpittlesForSpitter(@RequestParam("spitter") String username,Model model){
		Spitter spitter=spitterService.getSpitter(username);
		model.addAttribute(spitter);
		model.addAttribute(spitterService.getSpittlesForSpitter(username));
		return "spittles/list";
	}
}
