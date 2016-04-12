package springweb.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springweb.model.Spitter;
import springweb.service.SpitterService;

@Controller
@RequestMapping("/spitter")	// ¸úURLÂ·¾¶
public class SpitterController {
	
	private final SpitterService spitterService;
	
	@Inject
	public SpitterController(SpitterService spitterService){
		this.spitterService=spitterService;
	}
	
	public String listSpittlesForSpitter(@RequestParam("spitter") String username,Model model){
		Spitter spitter=spitterService.getSpitter(username);
		model.addAttribute(spitter);
		model.addAttribute(spitterService.getSpittlesForSpitter(username));
		return "spittles/list";
	}
}
