package fptu.phuochg.library.controller.admin;

import java.io.IOException;
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

import fptu.phuochg.library.domain.Order;
import fptu.phuochg.library.domain.Category;
import fptu.phuochg.library.domain.Customer;
import fptu.phuochg.library.domain.Order;
import fptu.phuochg.library.model.OrderDto;
import fptu.phuochg.library.model.CustomerDto;
import fptu.phuochg.library.model.OrderDto;
import fptu.phuochg.library.service.BookService;
import fptu.phuochg.library.service.CategoryService;
import fptu.phuochg.library.service.CustomerService;
import fptu.phuochg.library.service.OrderService;
import fptu.phuochg.library.service.StorageService;


@Controller
@RequestMapping("admin/orders")
public class OrderController {
	@Autowired
	CustomerService customerService;
	
	@Autowired
	OrderService orderService;
	
	
	public List<CustomerDto> getCustomers(){
		return customerService.findAll().stream().map(item->{
			CustomerDto dto = new CustomerDto();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).collect(Collectors.toList());
	}
	
	@ModelAttribute("customers")
	public Iterable<Customer> getAllCustomer(){
		return customerService.findAll();
	}

	
	@GetMapping("add")
	public String add(Model model) {
		OrderDto dto = new OrderDto();
		dto.setIsEdit(false);
		model.addAttribute("order", dto);
		
		return "admin/orders/addOrEdit";
	}
	
		
	@GetMapping("edit/{orderId}")
	public ModelAndView edit(ModelMap model, @PathVariable("orderId") Long orderId) {

		Optional<Order> opt = orderService.findById(orderId);
		OrderDto dto = new OrderDto();

		if (opt.isPresent()) {
			Order entity = opt.get();

			BeanUtils.copyProperties(entity, dto);
			
			dto.setCustomerId(entity.getCustomer().getCustomerId());
			dto.setIsEdit(true);

			model.addAttribute("order", dto);

			return new ModelAndView("admin/orders/addOrEdit", model);
		}
		model.addAttribute("message", "Order is not existed");

		return new ModelAndView("forward:/admin/orders", model);

	}

	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("order") OrderDto dto,
			BindingResult result) {

		if (result.hasErrors()) {

			return new ModelAndView("admin/orders/addOrEdit");
		}
		Order entity = new Order();
		Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = formatter.format(date);
        dto.setOrderDate(strDate);
		BeanUtils.copyProperties(dto, entity);
		
		Customer customer = new Customer();
		customer.setCustomerId(dto.getCustomerId());
		entity.setCustomer(customer);
			
		orderService.save(entity);

		
		
		model.addAttribute("message", "Order is saved! ");
		return new ModelAndView("forward:/admin/orders", model);
	}

	@RequestMapping("")
	public String list(ModelMap model) {

		List<Order> list = orderService.findAll();

		model.addAttribute("order", list);

		return "admin/orders/list";
	}

//	@GetMapping("search")
//	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name) {
//
//		List<Customer> list = null;
//
//		if (StringUtils.hasText(name)) {
//			list = customerService.findByNameContaining(name);
//		} else {
//			list = customerService.findAll();
//		}
//
//		model.addAttribute("order", list);
//
//		return "admin/orders/search";
//	}
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
//			resultPage = customerService.findByNameContaining(name, pageable);
//			model.addAttribute("name", name);
//			
//		} else {
//			resultPage = customerService.findAll(pageable);
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
//		return "admin/searchpaginated";
//	}
//		
}
