package phuoc.bookstore.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import phuoc.bookstore.dao.AcountsDAO;
import phuoc.bookstore.entity.Acount;
import phuoc.bookstore.model.AcountDto;
import phuoc.bookstore.service.AcountService;

@Controller
@RequestMapping("/admin/user")
public class UserController {

	@Autowired
	AcountsDAO acDAO;
	@Autowired
	HttpServletRequest request;
	@Autowired 
	AcountService acountService;
	@Autowired
	HttpSession session;
//	@GetMapping("")
//	public String QLUser(Model model) {
//		List<Acount> list=this.acDAO.findAll();
//		request.setAttribute("list", list);
//		return "user/QuanLiUser";
//	}
	
	@PostMapping("/add1")
	public String add(Model model,@Valid @ModelAttribute("acount")Acount ac,BindingResult bindingresult,@RequestParam("image-file")MultipartFile photo) throws IllegalStateException, IOException {
		AcountDto dto=new AcountDto();
		dto.setIsEdit(dto.getIsEdit());
		model.addAttribute("acount",dto);
		if(bindingresult.hasErrors()) {
			System.out.println(bindingresult.toString());
			session.setAttribute("validation", "Vui lòng nhập đầy đủ thông tin!");
			return "user/edit";
		}else{

			Date date = new Date();
	        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	        String strDate = formatter.format(date);
	        System.out.println(strDate);
	        ac.setCreateDate(strDate);
	        ac.setActivated(1);
	        if (photo.isEmpty()) {
				ac.setPhoto(session.getAttribute("imageAcount").toString());
			}else {
				ac.setPhoto(photo.getOriginalFilename());
			}
	        List<Acount> list=acDAO.findAll();
	        
	        String username=request.getParameter("username");
	        for(Acount acount : list) {
				if(acount.getUsername().equals(username)) {
					model.addAttribute("tinNhan","Tai khoan nay da ton tai!");
					break;
				}else {
					acDAO.save(ac);
				}
			}
		}
		
		
		return "redirect:/admin/user"; 
	}
	
	@GetMapping("")
	public String search(Model model,@RequestParam(name="username",required=false)String name,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		int curentPage=page.orElse(0);
		int pageSize=size.orElse(5);
		session.removeAttribute("validation");
		Pageable pageable=PageRequest.of(curentPage, pageSize,Sort.by(Direction.DESC,"username"));
		Page<Acount> resultPage=null;
		if(StringUtils.hasText(name)) {
			resultPage=acDAO.findByUsernameContainingAndActivated(name,1,pageable);
			System.out.println("Day la 2:"+resultPage);
			model.addAttribute("username",name);
		}else {
			resultPage=acDAO.findAllByActivated(1,pageable);
			System.out.println("Day la:"+ pageable);
		}
		int totalPages=resultPage.getTotalPages();
		if(totalPages>0) {
			int start=Math.max(1, curentPage-2);
			int end=Math.min(curentPage+2, totalPages);
			
			if(totalPages>5) {
				if(end==totalPages) start=end-5;
				else if(start==1)end=start+5;
			}
			List<Integer> pageNumber=IntStream.rangeClosed(start, end)
					.boxed()
					.collect(Collectors.toList());
			model.addAttribute("pageNumbers",pageNumber);
		}
		model.addAttribute("acountsPage",resultPage);
		return "user/QuanLiUser";
	}
	@GetMapping("/delete/{username}")
	public String delete(@PathVariable("username")String username) {
		
		if(!session.getAttribute("user").toString().equals(username)) {
			acDAO.deleteById(username);
		}else {
			session.setAttribute("message", "Ban khong the xoa chinh minh");
		}
		
		return "redirect:/admin/user";
	}
	@GetMapping("edit/{id}")
	public ModelAndView edit(ModelMap model,@ModelAttribute("acount")Acount acount,@PathVariable("id") String username ) {
		Optional<Acount> otp=acountService.findById(username);		
		AcountDto dto=new AcountDto();
		session.removeAttribute("validation");
		session.setAttribute("imageAcount", otp.get().getPhoto());
		List<Acount> list=acDAO.findByUsernameAndActivated(username, 1);
		model.addAttribute("list",list);
		System.out.println(otp.get());
		model.addAttribute("layId",username);
		
		if(otp.isPresent()) {
			
			Acount entity=otp.get();
				
			BeanUtils.copyProperties(otp, dto);
			dto.setUsername(username);
			dto.setIsEdit(true);
			model.addAttribute("acount",dto);

			
			
			acountService.save(entity);
			
			return new ModelAndView("user/edit",model);
		}	
		
		model.addAttribute("Message","Category is not exits");
		
		return new ModelAndView("redirect:/admin/user",model);
		
	}
	
	@GetMapping("/add")
	public String add(Model model,@ModelAttribute("acount")Acount acount) {
		AcountDto dto=new AcountDto();
		dto.setIsEdit(false);
		model.addAttribute("acount",dto);
		return "user/edit";
	}
	
	@ModelAttribute("admin")
	public int[] getAdmin() {
		int[] admin= {
				1,0
		};
		return admin;
	}
	
	
}
