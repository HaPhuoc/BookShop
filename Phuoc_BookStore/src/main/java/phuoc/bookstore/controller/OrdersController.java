package phuoc.bookstore.controller;

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
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import phuoc.bookstore.dao.OrdersDAO;
import phuoc.bookstore.dao.OrdersDetailsDAO;
import phuoc.bookstore.entity.Order;
import phuoc.bookstore.entity.OrderDetail;
import phuoc.bookstore.model.OrderDto;
import phuoc.bookstore.service.OrdersService;

@Controller
@RequestMapping("admin/orders/bills")
public class OrdersController {
	@Autowired
	OrdersDAO orDAO;
	@Autowired
	OrdersService orDerService;
	@Autowired
	OrdersDetailsDAO orDetailDAO;
//	@GetMapping("")
//	public String bills(Model model) {
//		List<Order> list=orDAO.findAll();
//		model.addAttribute("listOrders",list);
//		return "orders/listOrder";
//	}
	
	@GetMapping("")
	public String search(Model model,@RequestParam(name="address",required=false)String address,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size,
			@RequestParam(name = "sort",required = false,defaultValue = "createDate")String sort) {
		int curentPage=page.orElse(0);
		int pageSize=size.orElse(5);
		model.addAttribute("sort",sort);
		Pageable pageable=PageRequest.of(curentPage, pageSize,Sort.by(Direction.ASC,sort));
		Page<Order> resultPage=null;
		if(StringUtils.hasText(address)) {
			resultPage=orDAO.findByAddressContaining(address, pageable);
			System.out.println("Day la 2:"+resultPage);
			model.addAttribute("total",address);
		}else {
			resultPage=orDAO.findAll(pageable);
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
		model.addAttribute("orderPage",resultPage);
		return "orders/listOrder";
	}
	
	@PostMapping("edit")
	public String update(@ModelAttribute("orders")Order order,BindingResult bindingResult) {
		
		orDAO.save(order);
		return "redirect:/admin/orders/bills";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(Model model,@Valid@ModelAttribute("orders")Order order,BindingResult bindingResult,@PathVariable("id")int idOrder) {
		Optional<Order> otp=orDerService.findById(idOrder);
		List<Order> listOrder=orDAO.findByIdOrder(idOrder);
		model.addAttribute("listOrder",listOrder);
		OrderDto dto=new OrderDto();
		if(otp.isPresent()) {
			Order entity=otp.get();
			BeanUtils.copyProperties(otp, dto);
			orDerService.save(entity);		
		}
		
		return "orders/editOrder";
	}
	
	@GetMapping("billDetails/{id}")
	public String billDetails(Model model,@PathVariable("id")int idOrder) {
		List<OrderDetail> listOrDetail=orDetailDAO.findByIdOrder(idOrder);
		System.out.println(listOrDetail.toString());
		model.addAttribute("listOrDel",listOrDetail);
		return "orders/orderDetails";
	}

}
