package fptu.phuochg.library.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fptu.phuochg.library.domain.Customer;
import fptu.phuochg.library.model.CustomerDto;
import fptu.phuochg.library.service.CustomerService;

@Controller
public class CustomerLoginController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private HttpSession session;

	@GetMapping("login")
	public String login(ModelMap model) {
		model.addAttribute("customer", new CustomerDto());
		return "/customer-login";
	}

	@RequestMapping( value="/login")
	public ModelAndView login(ModelMap model, @Valid @ModelAttribute("customer") CustomerDto dto,
			BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("/customer-login", model);
		}
		Customer customer = customerService.login(dto.getName(), dto.getPassword());

		if (customer == null) {
			model.addAttribute("message", "Invalid username or password");
			return new ModelAndView("/customer-login", model);
		}

		session.setAttribute("name", customer.getName());
//		Object ruri = session.getAttribute("redirect-uri");
		return new ModelAndView("forward:/shopping-cart", model);
//		if (ruri != null) {
//			session.removeAttribute("redirect-uri");
//			return new ModelAndView("redirect:" + ruri);
//		}

		
	}
}
