package springweb.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	
	@RequestMapping(value="/spittles",method=GET)
	public String listSpittlesForSpitter(@RequestParam("spitter") String username,Model model){
		Spitter spitter=spitterService.getSpitter(username);
		model.addAttribute(spitter);
		model.addAttribute(spitterService.getSpittlesForSpitter(username));
		return "spittles/list";
	}
	
	@RequestMapping(method=RequestMethod.GET,params="new")
	public String createSpitterProfile(Model model){
		model.addAttribute(new Spitter());
		return "spitters/edit";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String addSpitterFromForm(@Valid Spitter spitter,BindingResult bindingResult,@RequestParam(value="image",required=false)MultipartFile image){
		if(bindingResult.hasErrors()){
			return "spitters/edit";
		}
		
		spitterService.saveSpitter(spitter);
		
		try{
			if(!image.isEmpty()){
				validateImage(image);
				saveImage(spitter.getId()+".jpg",image);
			}
		}catch(ImageUploadException e){
			bindingResult.reject(e.getMessage());
			return "spitters/edit";
		}
		return "redirect:/spitters/"+spitter.getUsername();
	}
	
	private void validateImage(MultipartFile image){
		if(!image.getContentType().equals("image/jpeg")){
			throw new ImageUploadException("Only JPG images accepted");
		}
	}
	
	private void saveImage(String filename,MultipartFile image)
	{
//		File file=new File(webRootPath+"/resources/"+filename);
//		FileUtils.writeByteArrayToFile(file,image.getBytes());
	}
	
	@RequestMapping(value="/{username}",method=RequestMethod.GET)
	public String showSpitterProfile(@PathVariable String username,Model model){
		model.addAttribute(spitterService.getSpitter(username));
		return "spitters/view";
	}
}
