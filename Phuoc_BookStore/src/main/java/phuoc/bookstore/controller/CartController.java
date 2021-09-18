package phuoc.bookstore.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import phuoc.bookstore.entity.Acount;
import phuoc.bookstore.service.AcountService;

@Controller
public class CartController {
	@Autowired
	HttpSession session;
	@Autowired
	AcountService acService;
	@GetMapping("/cart")
	public String cart() {
		Optional<Acount> optionalUser=acService.findById(session.getAttribute("user").toString());
		System.out.println(optionalUser.get().getAdmin());
		if(optionalUser.get().getAdmin()==0) {
			session.setAttribute("present", session.getAttribute("user"));
		}
		return "cart";
	}
 
}
