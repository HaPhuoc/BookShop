package phuoc.bookstore.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
import org.springframework.web.servlet.ModelAndView;

import phuoc.bookstore.dao.CategoriesDAO;
import phuoc.bookstore.entity.Category;
import phuoc.bookstore.model.CategoryDto;
import phuoc.bookstore.service.CategoryService;

@Controller
@RequestMapping("admin/categories")
public class CategoryController {

	@Autowired
	CategoriesDAO caDAO;
	@Autowired
	CategoryService categoryService;
	@GetMapping("add")
	public String save(Model model,@ModelAttribute("categories")Category category) {
		return "admin/categories/addOrEdit";
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(ModelMap model,@ModelAttribute("categories")Category ctg  ,@PathVariable("id") int idCategory ) {
		Optional<Category> otp=categoryService.findById(idCategory);
		CategoryDto dto=new CategoryDto();
		
		model.addAttribute("layId",idCategory);
		
		if(otp.isPresent()) {
			
			Category entity=otp.get();
				
			BeanUtils.copyProperties(otp, dto);
			
			dto.setIdCategory(idCategory);
			dto.setIsEdit(true);
			
			model.addAttribute("category",dto);

			categoryService.save(entity);
			
			return new ModelAndView("admin/categories/addOrEdit",model);
		}
		
		
		
		model.addAttribute("Message","Category is not exits");
		
		return new ModelAndView("redirect:/admin/categories",model);
		
	}
	
	@PostMapping("saveOrUpdate")
	public String saveOrUpdate(Model model,@Valid @ModelAttribute("categories")CategoryDto dto,BindingResult bindingresult) throws IllegalStateException, IOException {
		Category entity=new Category();
		
		if (bindingresult.hasErrors()) {
			return "admin/categories/addOrEdit";
		}else {
			dto.setActivated(1);
			BeanUtils.copyProperties(dto, entity);
			categoryService.save(entity);
		}

		return "redirect:/admin/categories";		
	}
	
	@GetMapping("delete/{id}")
	public String delete(Model model,@PathVariable("id") Integer idCategory) {
		Optional<Category> ctg=caDAO.findById(idCategory);
		
		ctg.get().setActivated(0);
		
		caDAO.save(ctg.get());

		return "redirect:/admin/categories";
	}
	
//	@GetMapping("delete/{idCategory}/{name}")
//	public String delete(Model model,@PathVariable("idCategory")int idCategory,@PathVariable("name")String name) {
//		Category ctg=new Category();
//		ctg.setIdCategory(idCategory);
//		ctg.setName(name);
//		ctg.setActivated(0);
//		caDAO.save(ctg);
//		return "redirect:/admin/categories";
//	}
	
	@GetMapping("")
	public String search(Model model,@RequestParam(name="name",required=false)String name,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		int curentPage=page.orElse(0);
		int pageSize=size.orElse(5);
		
		Pageable pageable=PageRequest.of(curentPage, pageSize,Sort.by(Direction.DESC,"name"));
		Page<Category> resultPage=null;
		if(StringUtils.hasText(name)) {
			resultPage=caDAO.findByNameContainingAndActivated(name, 1, pageable);
			System.out.println("Day la 2:"+resultPage);
			model.addAttribute("name",name);
		}else {
			resultPage=caDAO.findByActivated(1, pageable);
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
		model.addAttribute("categoryPage",resultPage);
		return "admin/categories/searchpaginated";
	}
}
