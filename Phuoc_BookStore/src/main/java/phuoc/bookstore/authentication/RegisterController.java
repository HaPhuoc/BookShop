package phuoc.bookstore.authentication;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import phuoc.bookstore.dao.AcountsDAO;
import phuoc.bookstore.entity.Acount;
import phuoc.bookstore.service.AcountService;

@Controller
@RequestMapping("/admin/register")
public class RegisterController {
	@Autowired
	AcountsDAO acDAO;
	@Autowired
	ServletContext application;
	@Autowired
	HttpServletRequest request;
	@Autowired
	AcountService acountService;
	@PostMapping("/add1")
	public String dangki(Model model,@Valid @ModelAttribute("acount")Acount ac,BindingResult bindingresult,@RequestParam("image-file")MultipartFile photo) throws IllegalStateException, IOException {
//		@RequestParam("username")String userName,@RequestParam("email") String email,@RequestParam("password")String password,@RequestParam("photo")MultipartFile photo,@RequestParam("fullname")String fullname
//		Acount ac=new Acount();
//		ac.setEmail(email);
//		ac.setFullname(fullname);
//		ac.setPhoto(photo.getOriginalFilename());
//		ac.setUsername(userName);
//		ac.setPassword(password);
//		ac.setActivated(1);
//		ac.setAdmin(0);
		if(bindingresult.hasErrors()) {
			System.out.println(bindingresult.toString());
			return "admin/categories/acount";
		}else{

			Date date = new Date();
	        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	        String strDate = formatter.format(date);
	        System.out.println(strDate);
			ac.setCreateDate(strDate);
			ac.setActivated(1);
			ac.setAdmin(0);
			ac.setPhoto(photo.getOriginalFilename());
			acDAO.save(ac);
			if(!photo.isEmpty()) {
				String fileName=photo.getOriginalFilename();
				String path=new File("src/main/resources/static/images").getAbsolutePath();
				File file=new File(path+"/"+fileName);				
				photo.transferTo(file);
			}
		}
		
		
		return "redirect:/admin/login"; 
	}
//	@PostMapping("/admin/register/add1")
//	public String dangki1(Model model,@RequestParam("username")String userName,@RequestParam("email") String email,@RequestParam("password")String password,@RequestParam("photo")MultipartFile photo,@RequestParam("fullname")String fullname) throws IllegalStateException, IOException {
//		Acount ac=new Acount();
//		ac.setEmail(email);
//		ac.setFullname(fullname);
//		ac.setPhoto(photo.getOriginalFilename());
//		ac.setUsername(userName);
//		ac.setPassword(password);
//		ac.setActivated(1);
//		ac.setAdmin(0);
//		
//		if(!photo.isEmpty()) {
//			String fileName=photo.getOriginalFilename();
//			String path=new File("src/main/resources/static/images").getAbsolutePath();
//			File file=new File(path+"/"+fileName);
//			
//			photo.transferTo(file);
//		}
//		acDAO.save(ac);
//		return "redirect:/admin/login"; 
//	}
	@GetMapping("")
	public String data(Model model) {
		Acount acount=new Acount();
		List<Acount>list=acDAO.findAll();
		model.addAttribute("acount",acount);
		return "admin/categories/acount";
	}
	

	

}
