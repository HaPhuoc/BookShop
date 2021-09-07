package fptu.phuochg.library.controller.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fptu.phuochg.library.domain.Book;
import fptu.phuochg.library.domain.Customer;
import fptu.phuochg.library.domain.Order;
import fptu.phuochg.library.model.BookDto;
import fptu.phuochg.library.model.CartItemDto;
import fptu.phuochg.library.repository.BookRepository;
import fptu.phuochg.library.repository.CustomerRepository;
import fptu.phuochg.library.repository.OrderRepository;
import fptu.phuochg.library.service.BookService;
import fptu.phuochg.library.service.CustomerService;
import fptu.phuochg.library.service.ShoppingCartService;

@Controller
@RequestMapping("shopping-cart")
public class ShoppingCartController {
	@Autowired
	BookService bookService;
	@Autowired
	HttpSession session;
	@Autowired
	ShoppingCartService shoppingcarService;
	@Autowired
	CustomerRepository customerRepository; 
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	BookRepository bookRespository;
	@GetMapping("view")
	public String viewCarts(Model model) {
		model.addAttribute("cartItems", shoppingcarService.getAllItems());
		System.out.println(session.getAttribute("amount"));
		System.out.println(session.getAttribute("cartItems"));
		return "cart";
	}
	@GetMapping("add/{bookId}")
	public String addCart(Model model,@PathVariable("bookId") Long bookId) {
		System.out.println("BookId " + bookId);
		Book book = bookService.findByIdBookId(bookId);		
		Optional<Book> book1=bookRespository.findById(bookId);
		System.out.println(book1);
		System.out.println("Co null hay khong ?" +book);
		if(	book1 != null) {
			CartItemDto cartItem = new CartItemDto();
			cartItem.setImage(book1.get().getImage());
			cartItem.setBookId(book1.get().getBookId());
			cartItem.setName(book1.get().getName());
			cartItem.setPrice(book1.get().getPrice());
			cartItem.setQuantity(1);
			shoppingcarService.add(cartItem);		
		}
		model.addAttribute("amount",shoppingcarService.getAmount());
		return "redirect:/shopping-cart/view";
	}
	
	@GetMapping("/checkout")
	public String checkOut() {
		
		try {
			List<Customer> list=customerRepository.findByNameContaining(session.getAttribute("username").toString());
			Order entityOrder=new Order();
			for (Customer customer : list) {
				entityOrder.setCustomer(customer);
				Date date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				String strDate = formatter.format(date);
				entityOrder.setOrderDate(strDate);
				String sort="1";
				entityOrder.setStatus(Short.parseShort(sort));
				orderRepository.save(entityOrder);
			}	
			
		} catch (Exception e) {
			System.out.println("Loi Thanh Toan");
		}
		return "redirect:/shopping-cart/view";
	}
}
