package fptu.phuochg.library.controller.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import fptu.phuochg.library.domain.Book;
import fptu.phuochg.library.domain.Category;
import fptu.phuochg.library.domain.Customer;
import fptu.phuochg.library.model.BookDto;
import fptu.phuochg.library.model.CategoryDto;
import fptu.phuochg.library.model.CustomerDto;
import fptu.phuochg.library.service.BookService;
import fptu.phuochg.library.service.CategoryService;
import fptu.phuochg.library.service.CustomerService;
import fptu.phuochg.library.service.StorageService;


@Controller
@RequestMapping("admin/customers")
public class CustomerController {
	@Autowired
	CustomerService customerService;
	

	@GetMapping("add")
	public String add(Model model) {
		
		model.addAttribute("customer", new CustomerDto());
		
		return "admin/customers/addOrEdit";
	}
	
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("customer") CustomerDto dto,
			BindingResult result) {

		if (result.hasErrors()) {
			System.out.println(result);
			return new ModelAndView("admin/customers/addOrEdit");
		}
		Customer entity = new Customer();
		Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = formatter.format(date);
        dto.setRegisteredDate(strDate);
		BeanUtils.copyProperties(dto, entity);

		customerService.save(entity);

		model.addAttribute("message", "Customer is saved! ");
		return new ModelAndView("forward:/admin/customers", model);
	}	
	@RequestMapping("")
	public String list(ModelMap model) {

		List<Customer> list = customerService.findAll();

		model.addAttribute("customers", list);

		return "admin/customers/list";
	}
	
	@GetMapping("edit/{customerId}")
	public ModelAndView edit(ModelMap model, @PathVariable("customerId") Long customerId) {

		
		Optional<Customer> opt = customerService.findById(customerId);
		CustomerDto dto = new CustomerDto();
		if (opt.isPresent()) {
			Customer entity = opt.get();

			BeanUtils.copyProperties(entity, dto);
			dto.setIsEdit(true);

			model.addAttribute("customer", dto);
			
			customerService.save(entity);

			return new ModelAndView("admin/customers/addOrEdit", model);
		}
		model.addAttribute("message", "Customer is not existed");

		return new ModelAndView("forward:/admin/customers", model);

		
	}
	
	@GetMapping("search")
	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name) {

		List<Customer> list = null;

		if (StringUtils.hasText(name)) {
			list = customerService.findByNameContaining(name);
		} else {
			list = customerService.findAll();
		}

		model.addAttribute("customers", list);

		return "admin/customers/search";
	}
	
	@GetMapping("delete/{customerId}")
	public ModelAndView delete(ModelMap model, @PathVariable("customerId") Long customerId) {

		customerService.deleteById(customerId);

		model.addAttribute("message", "Customer is deleted!");

		return new ModelAndView("forward:/admin/customers/search", model);
	}
//	@GetMapping("/images/{filename:.+}")
//	@ResponseBody
//	public ResponseEntity<Resource> serveFile(@PathVariable  String filename){
//		Resource file = storageService.loadAsResource(filename);
//		
//		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
//				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
//	}

//		
//		
//	
//
//		return new ModelAndView("forward:/admin/books", model);
//	}
//
	
//
//	@GetMapping("searchpaginated")
//	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name,
//			@RequestParam("page") Optional<Integer> page, 
//			@RequestParam("size") Optional<Integer> size) {
//		
//		int currentPage = page.orElse(1);
//		int pageSize = size.orElse(5);
//
//		Pageable pageable = PageRequest.of(currentPage-1, pageSize, Sort.by("name"));
//		Page<Category> resultPage = null;
//
//		if (StringUtils.hasText(name)) {
//			resultPage = categoryService.findByNameContaining(name, pageable);
//			model.addAttribute("name", name);
//			
//		} else {
//			resultPage = categoryService.findAll(pageable);
//		}
//		int totalPages = resultPage.getTotalPages();
//
//		if (totalPages > 0) {
//			int start = Math.max(1, currentPage-2);
//			int end = Math.min(currentPage + 2, totalPages);
//			
//			if (totalPages > 5) {
//				if (end == totalPages) start = end - 5 ;
//				else if ( start == 1 ) end =start + 5; 
//			}
//			List <Integer> pageNumbers = IntStream.rangeClosed(start, end)
//				.boxed()
//				.collect(Collectors.toList());
//			
//			model.addAttribute("pageNumbers", pageNumbers);
//		}
//		model.addAttribute("categoryPage",resultPage);
//		
//		return "admin/books/searchpaginated";
//	}
//		
}
