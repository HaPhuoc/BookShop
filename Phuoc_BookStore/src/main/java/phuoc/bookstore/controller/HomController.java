package phuoc.bookstore.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import phuoc.bookstore.dao.ProductsDAO;
import phuoc.bookstore.entity.Product;
import phuoc.bookstore.service.ShoppingCartService;

@Controller
public class HomController {
	@Autowired
	ShoppingCartService shoppingcartService;
	@Autowired
	HttpSession session;
	@Autowired
	ProductsDAO prDAO;
	@GetMapping("/index")
	public String home(Model model) {
		
		List<Product> list=this.prDAO.findByAvailable(1);
		model.addAttribute("listSP",list);
		session.setAttribute("present", session.getAttribute("user"));
		return "index";
	}
}
