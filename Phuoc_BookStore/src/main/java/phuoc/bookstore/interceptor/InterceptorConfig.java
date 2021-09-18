package phuoc.bookstore.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	
	@Autowired
	LogInterCeptor LogInterCeptor;
	@Autowired
	AdminLoginterceptor Adminloginterceptor;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(LogInterCeptor)
	    .addPathPatterns("/shopping-cart/checkout/**")
	    .excludePathPatterns("/admin/login","/admin/register","/admin/logout");
	    
	    registry.addInterceptor(Adminloginterceptor)
	    .addPathPatterns("/admin/user/**","/admin/product/**","/admin/categories/**")
	    .excludePathPatterns("/admin/login","/admin/register","/admin/logout");
	}

}
