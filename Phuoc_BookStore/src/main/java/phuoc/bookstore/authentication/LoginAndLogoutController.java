package phuoc.bookstore.authentication;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import phuoc.bookstore.dao.AcountsDAO;
import phuoc.bookstore.entity.Acount;
import phuoc.bookstore.service.AcountService;

@Controller
@RequestMapping("/admin")
public class LoginAndLogoutController {
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired
	HttpSession session;
	@Autowired
	AcountsDAO acDAO;
	@Autowired
	AcountService acountservice;

	@PostMapping("login/get")
	public String checkLogin(Model model, @RequestParam("username") String username,
			@RequestParam("password") String password) {
		if (acountservice.checkLogin(username, password)) {
			session.setAttribute("user", username);
			return "redirect:/shop";
		} else if (acountservice.checkLogin1(username, password)) {
			session.setAttribute("user", username);
			return "redirect:/admin/user";
		} else {
			model.addAttribute("thongbao", "Tài khoản hoặc mật khẩu không chính xác !");
			System.out.println("Dang nhap that bai");
		}

		return "login";
	}
//	@GetMapping("admin/register")
//	public String register(Model model) {
//		Acount acount=new Acount();
//		model.addAttribute("acount",acount);
//		return "register";
//	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

//	@ResponseBody
//	@GetMapping("/data")
//	public List<Acount> list() {
//		List<Acount> list = acDAO.findAll();
//		for (Acount ac : list) {
//			System.out.println(ac.getUsername());
//		}
//		return list;
//	}
//
//	@ResponseBody
//	@GetMapping("/data1")
//	public String dangnhap(Model model, @ModelAttribute("account") Acount acount) {
//		List<Acount> list = this.acDAO.findAll();
//		System.out.println(list.toString());
//		model.addAttribute("account", acount);
//		return "";
//	}

	@GetMapping("/logout")
	public String logout() {
		session.removeAttribute("user");
		return "redirect:/shop";
	}

//	@GetMapping("/category")
//	public String categoryPage(Model model, @RequestParam("page") Optional<Integer> page,
//			@RequestParam(name = "sortby", required = false, defaultValue = "id") String sortby,
//			@RequestParam(name = "sort", required = false, defaultValue = "asc") String sort) {
//		Pageable pageable = null;
//		if (sort.equals("asc")) {
//			pageable = PageRequest.of(page.orElse(0), 5, Sort.by(sortby).ascending());
//		} else if (sort.equals("desc")) {
//			pageable = PageRequest.of(page.orElse(0)
}
