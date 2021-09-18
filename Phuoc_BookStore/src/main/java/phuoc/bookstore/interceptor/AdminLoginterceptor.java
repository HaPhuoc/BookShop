package phuoc.bookstore.interceptor;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import phuoc.bookstore.dao.AcountsDAO;
import phuoc.bookstore.entity.Acount;

@Component
public class AdminLoginterceptor implements HandlerInterceptor {
	@Autowired
	HttpSession session;
	@Autowired
	AcountsDAO acDAO;
	@Override
	public boolean preHandle(
	  HttpServletRequest request,
	  HttpServletResponse response, 
	  Object handler) throws Exception {
		session=request.getSession();
		try {
			Optional<Acount> optionnalAuth=acDAO.findByActivatedAndUsername(1, session.getAttribute("user").toString());
			if(session.getAttribute("user")==null||optionnalAuth.get().getAdmin()==0) {
				session.setAttribute("error", "Vui lòng đăng nhập");
				response.sendRedirect(request.getContextPath()+"/admin/login");
				return false;
			}
		} catch (Exception e) {
			session.setAttribute("error", "Vui lòng đăng nhập");
			response.sendRedirect(request.getContextPath()+"/admin/login");
		}
		
	    System.out.println("preHandle");
	    return true;
	}
	
//	@Override
//	public void postHandle(
//	  HttpServletRequest request, 
//	  HttpServletResponse response,
//	  Object handler, 
//	  ModelAndView modelAndView) throws Exception {
//		System.out.println("postHandle");
//	}
	
//	@Override
//	public void afterCompletion(
//	  HttpServletRequest request, 
//	  HttpServletResponse response,
//	  Object handler, Exception ex) {
//		System.out.println("afterCompletion");
//	}
}
