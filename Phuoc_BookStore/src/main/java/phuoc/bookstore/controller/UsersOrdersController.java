package phuoc.bookstore.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import phuoc.bookstore.dao.OrdersDAO;
import phuoc.bookstore.dao.OrdersDetailsDAO;
import phuoc.bookstore.entity.Order;
import phuoc.bookstore.entity.OrderDetail;

@Controller
@RequestMapping("/orders")
public class UsersOrdersController {
	@Autowired 
	OrdersDAO orDAO;
	@Autowired
	HttpSession session;
	@Autowired
	OrdersDetailsDAO orDetailDAO;
//	@GetMapping("/orders")
//	public String listOrder(Model model) {
//		try {
//			List<Order> listOrder=orDAO.findByUsername(session.getAttribute("user").toString());
//			model.addAttribute("listOrder",listOrder);
//		} catch (Exception e) {
//			return "login";
//		}
//
//
//		return "order";
//	}
	
	@GetMapping("")
	public String search(Model model,@RequestParam(name="username",required=false)String username,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size,
			@RequestParam(name = "sort",required = false,defaultValue = "createDate")String sort) {
		int curentPage=page.orElse(0);
		int pageSize=size.orElse(5);
		model.addAttribute("sort",sort);
		Pageable pageable=PageRequest.of(curentPage, pageSize,Sort.by(Direction.ASC,sort));
		Page<Order> resultPage=null;
		if(StringUtils.hasText(username)) {
			resultPage=orDAO.findByUsername(username, pageable);
			System.out.println("Day la 2:"+resultPage);
			model.addAttribute("total",username);
		}else {
			try {
				resultPage=orDAO.findByUsername(session.getAttribute("user").toString(), pageable);
			System.out.println("Day la:"+ pageable);
			} catch (Exception e) {
				return "login";
			}
			
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
		return "order";
	}
	
	@GetMapping("billDetails/{id}")
	public String billDetails(Model model,@PathVariable("id")int idOrder) {
		List<OrderDetail> listOrDetail=orDetailDAO.findByIdOrder(idOrder);
		System.out.println(listOrDetail.toString());
		model.addAttribute("listOrDel",listOrDetail);
		return "orderDetailsUser";
	}
}
