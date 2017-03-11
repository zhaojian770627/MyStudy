package studyweb.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myweb")
public class MainController {

	@RequestMapping(method = GET)
	public String showMain() {
		return "main";
	}
}
