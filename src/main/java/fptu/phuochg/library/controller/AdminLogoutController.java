package fptu.phuochg.library.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminLogoutController {

	@Autowired
	HttpSession session;
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@GetMapping("logout")
	public String logout() {
		session=request.getSession();
		session.removeAttribute("username");
		return "redirect:/admin/categories";
	}
}
