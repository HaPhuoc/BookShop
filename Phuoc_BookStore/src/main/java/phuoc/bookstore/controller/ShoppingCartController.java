package phuoc.bookstore.controller;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import phuoc.bookstore.dao.AcountsDAO;
import phuoc.bookstore.dao.OrdersDAO;
import phuoc.bookstore.dao.OrdersDetailsDAO;
import phuoc.bookstore.dao.ProductsDAO;
import phuoc.bookstore.entity.Acount;
import phuoc.bookstore.entity.CartItem;
import phuoc.bookstore.entity.Order;
import phuoc.bookstore.entity.OrderDetail;
import phuoc.bookstore.entity.Product;
import phuoc.bookstore.service.AcountService;
import phuoc.bookstore.service.OrdersService;
import phuoc.bookstore.service.ProductService;
import phuoc.bookstore.service.ShoppingCartService;

@Controller
@RequestMapping("shopping-cart")
public class ShoppingCartController {
	@Autowired
	ProductsDAO prDAO;
	@Autowired
	AcountService acountService;
	@Autowired
	AcountsDAO acDAO;
	@Autowired
	ProductService productservice;
	@Autowired
	ShoppingCartService shoppingcartService;
	@Autowired
	OrdersDAO orDAO;
	@Autowired
	OrdersService ordersService;
	@Autowired
	HttpSession session;
	@Autowired
	OrdersDetailsDAO ordelDAO;

	@GetMapping("views")
	public String viewCart(Model model) {
		session.setAttribute("amount", shoppingcartService.getAmount());
		session.setAttribute("soluong", shoppingcartService.getCount());
		try {
			session.setAttribute("present", session.getAttribute("user").toString());
		} catch (Exception e) {
			return "cart";
		}

		return "cart";
	}

	@GetMapping("single-product/{id}")
	public String single(Model model, @PathVariable("id") int idProduct) {
		session.setAttribute("amount", shoppingcartService.getAmount());
		session.setAttribute("soluong", shoppingcartService.getCount());
		List<Product> list = prDAO.findByIdProduct(idProduct);
		model.addAttribute("listProduct", list);
		return "single-product";
	}

	@GetMapping("add/{id}")
	public String addCart(@PathVariable("id") Integer id) {
		Product product = productservice.findByIdProduct(id);
		if (product != null) {
			CartItem carItem = new CartItem();
			carItem.setIdProduct(product.getIdProduct());
			carItem.setName(product.getName());
			carItem.setPrice(product.getPrice());
			carItem.setImage(product.getImage());
			carItem.setQuantity(1);
			shoppingcartService.add(carItem);
		}
		session.removeAttribute("messageQuantity");
		session.setAttribute("cartItems", shoppingcartService.getAlCartItems());
		return "redirect:/shopping-cart/views";
	}

	@GetMapping("checkout")
	public String checkout() {
		try {
			List<Acount> list = acDAO.findByUsername(session.getAttribute("user").toString());
			Order entityOrder = new Order();

			for (Acount acount : list) {
				Collection<CartItem> listCart = shoppingcartService.getAlCartItems();
				
				for (CartItem cartItem : listCart) {
					Product idProduct = productservice.findByIdProduct(cartItem.getIdProduct());
					int quantity=idProduct.getQuantity();
					try {
						idProduct.setQuantity((quantity-cartItem.getQuantity()));
						Date date = new Date();
						SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
						String strDate = formatter.format(date);
						entityOrder.setAcount(acount);
						entityOrder.setCreateDate(strDate);
						entityOrder.setAddress(acount.getAddress());
						entityOrder.setTotal(Float.parseFloat(session.getAttribute("amount").toString()));
						entityOrder.setStatus(0);
						orDAO.save(entityOrder);
					} catch (Exception e) {			
						session.setAttribute("messageQuantity",
								"Sản phẩm trong kho không đủ so với yêu cầu của quý khách!" + "Trong kho chỉ còn "
										+ quantity + " Sản phẩm !");
						}
					}							
			}
			Order idOrder = ordersService.findByIdOrder(Integer.parseInt(orDAO.myQuery()));
			Object objCart = session.getAttribute("cartItems");

			if (objCart != null) {
				Collection<CartItem> listCart = shoppingcartService.getAlCartItems();
				for (CartItem cartItem : listCart) {
					OrderDetail entityOrderDetails = new OrderDetail();
					entityOrderDetails.setIdOrder(idOrder);
					Product idProduct = productservice.findByIdProduct(cartItem.getIdProduct());
					entityOrderDetails.setProduct(idProduct);
					entityOrderDetails.setQuantity(cartItem.getQuantity());
					entityOrderDetails.setPrice(cartItem.getPrice());
					int quantity=idProduct.getQuantity();
					int countSaled=idProduct.getSaled();
					try {
						idProduct.setQuantity((idProduct.getQuantity())-cartItem.getQuantity());
						idProduct.setSaled(countSaled+(cartItem.getQuantity()));
						session.setAttribute("messageQuantity",
								"Đặt Hàng Thành Công !");
						prDAO.save(idProduct);
					} catch (Exception e) {
						session.setAttribute("messageQuantity",
								"Sản phẩm trong kho không đủ so với yêu cầu của quý khách!" + "Trong kho chỉ còn "
										+ quantity + " Sản phẩm !");
					}
					ordelDAO.save(entityOrderDetails);
					System.out.println(cartItem);
				}
			}

		} catch (Exception e) {
			return "login";
		}
		session.removeAttribute("cartItems");
		session.removeAttribute("amount");
		session.removeAttribute("soluong");
		return "redirect:/shopping-cart/views";
	}

//	@GetMapping("checkout/{id}")
//	public String checkOut(@PathVariable("id") Integer id) {
//		try {
//			List<Acount> list = acDAO.findByUsername(session.getAttribute("user").toString());
//			List<Product> listPrd = prDAO.findByIdProduct(id);
//			Optional<Product> listOptional = prDAO.findById(id);
//			Order entityOrder = new Order();
//			OrderDetail entityOrderDetails = new OrderDetail();
//
//				System.out.println(entityOrder.toString());
//				for (Product product : listPrd) {
//					entityOrderDetails.setProduct(product);
//					entityOrderDetails.setIdOrder(Integer.valueOf(orDAO.myQuery()));
//					entityOrderDetails.setPrice(Double.parseDouble(session.getAttribute("amount") + ""));
//					entityOrderDetails.setQuantity(Integer.parseInt(session.getAttribute("quantity") + ""));
//					System.out.println(shoppingcartService.getAmount() / product.getPrice());
//					System.out.println();
//					int quantity = product.getQuantity();
//					ordelDAO.save(entityOrderDetails);
//					try {
//						listOptional.get().setQuantity(
//								product.getQuantity() - Integer.parseInt(session.getAttribute("quantity") + ""));
//						session.setAttribute("messageQuantity", "Đặt Hàng Thành Công !");
//
//						prDAO.save(listOptional.get());
//					} catch (Exception e) {
//						session.setAttribute("messageQuantity",
//								"Sản phẩm trong kho không đủ so với yêu cầu của quý khách!" + "Trong kho chỉ còn "
//										+ quantity + " Sản phẩm !");
//						System.out.println();
//					}
//
//				}
//
//			}
//
//		} catch (Exception e) {
//			return "/login";
//		}
//		session.removeAttribute("cartItems");
//		System.out.println("Con lai:" + session.getAttribute("cartItems"));
//		return "redirect:/shopping-cart/views";
//
//	}

	@GetMapping("delete/{id}")
	public String delete(Model model, @PathVariable("id") int id) {
		shoppingcartService.remove(id);
		return "redirect:/shopping-cart/views";
	}

	@PostMapping("update")
	public String update(Model model, @RequestParam("id-product") int idProduct,
			@RequestParam("quantity") int quantity) {
		shoppingcartService.update(idProduct, quantity);
		session.setAttribute("quantity", quantity);
		return "redirect:/shopping-cart/views";
	}
}
