package fptu.phuochg.library.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AdminAuthenticationInterceptor implements HandlerInterceptor{
	@Autowired
	private HttpSession session;
	@Override
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(session.getAttribute("username") == null){
			response.sendRedirect(request.getContextPath()+"/alogin");
			return false;
		}
		return true;
	}
}
