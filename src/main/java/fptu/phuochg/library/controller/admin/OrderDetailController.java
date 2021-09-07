package fptu.phuochg.library.controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import fptu.phuochg.library.domain.Book;
import fptu.phuochg.library.domain.Category;
import fptu.phuochg.library.domain.Order;
import fptu.phuochg.library.domain.OrderDetail;
import fptu.phuochg.library.model.BookDto;
import fptu.phuochg.library.model.CategoryDto;
import fptu.phuochg.library.model.OrderDetailDto;
import fptu.phuochg.library.model.OrderDto;
import fptu.phuochg.library.repository.OrderDetailRepository;
import fptu.phuochg.library.repository.OrderRepository;
import fptu.phuochg.library.service.BookService;
import fptu.phuochg.library.service.CategoryService;
import fptu.phuochg.library.service.OrderDetailService;
import fptu.phuochg.library.service.OrderService;


@Controller
@RequestMapping("admin/orderdetails")
public class OrderDetailController {
	@Autowired
	BookService bookService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderDetailService orderDetailService;
	
	public List<BookDto> getBooks(){
		return bookService.findAll().stream().map(item->{
			BookDto dto = new BookDto();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).collect(Collectors.toList());
	}
	@ModelAttribute("books")
	public Iterable<Book> getAllBook(){
		return bookService.findAll();
	}
	
	public List<OrderDto> get(){
		return orderService.findAll().stream().map(item->{
			OrderDto dto = new OrderDto();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).collect(Collectors.toList());
	}
	@ModelAttribute("orders")
	public Iterable<Order> getAllOrder(){
		return orderService.findAll();
	}

	@GetMapping("add")
	public String add(Model model) {
		OrderDetailDto dto = new OrderDetailDto();
		dto.setIsEdit(false);
		model.addAttribute("orderdetail", dto);
		
		return "admin/orderdetails/addOrEdit";
	}

	@GetMapping("edit/{orderDetailId}")
	public ModelAndView edit(ModelMap model, @PathVariable("orderDetailId") Integer orderDetailId) {

		Optional<OrderDetail> opt = orderDetailService.findById(orderDetailId);
		OrderDetailDto dto = new OrderDetailDto();

		if (opt.isPresent()) {
			OrderDetail entity = opt.get();

			BeanUtils.copyProperties(entity, dto);
			
			dto.setBookId(entity.getBook().getBookId());
			dto.setIsEdit(true);
			dto.setOrderId(entity.getOrder().getOrderId());
			dto.setIsEdit(true);

			model.addAttribute("orderdetail", dto);

			return new ModelAndView("admin/orderdetails/addOrEdit", model);
		}
		model.addAttribute("message", "Orderdetail is not existed");

		return new ModelAndView("forward:/admin/orderdetails", model);

	}
	@RequestMapping("")
	public String list(ModelMap model) {

		List<OrderDetail> list = orderDetailService.findAll();

		model.addAttribute("orderdetails", list);

		return "admin/orderdetails/list";
	}
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("orderdetail") OrderDetailDto dto,
			BindingResult result) {

		if (result.hasErrors()) {

			return new ModelAndView("admin/orderdetails/addOrEdit");
		}
		OrderDetail entity = new OrderDetail();
		BeanUtils.copyProperties(dto, entity);

		orderDetailService.save(entity);

		model.addAttribute("message", "OrderDetail is saved! ");
		return new ModelAndView("forward:/admin/orderdetails", model);
	}
	@GetMapping("delete/{orderDetailId}")
	public ModelAndView delete(ModelMap model, @PathVariable("orderDetailId") Integer orderDetailId) {

		orderDetailService.deleteById(orderDetailId);

		model.addAttribute("message", "Orderdetail is deleted!");

		return new ModelAndView("forward:/admin/orderdetails/search", model);
	}
	@GetMapping("search")
	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name) {

		List<OrderDetail> list = null;

		if (StringUtils.hasText(name)) {
			list = orderDetailService.findByNameContaining(name);
		} else {
			list = orderDetailService.findAll();
		}

		model.addAttribute("orderdetails", list);

		return "admin/orderdetails/search";
	}

}
