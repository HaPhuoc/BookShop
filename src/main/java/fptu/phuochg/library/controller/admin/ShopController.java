package fptu.phuochg.library.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fptu.phuochg.library.domain.Book;
import fptu.phuochg.library.repository.BookRepository;

@Controller
@RequestMapping
public class ShopController {
	@Autowired
	BookRepository bookRepository;
	@GetMapping("/shopping-cart")
	public String shop(Model model) {
		
		List<Book> list=bookRepository.findAll();
		model.addAttribute("listBook",list);
		return "index";
	}
}
