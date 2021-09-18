package phuoc.bookstore.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import phuoc.bookstore.dao.CategoriesDAO;
import phuoc.bookstore.dao.OrdersDAO;
import phuoc.bookstore.dao.ProductsDAO;
import phuoc.bookstore.entity.Category;
import phuoc.bookstore.entity.Product;
import phuoc.bookstore.model.ProductDto;
import phuoc.bookstore.service.ProductService;

@Controller
@RequestMapping("admin/product")
public class ProductController {
	@Autowired
	ProductsDAO prDAO;
	@Autowired
	CategoriesDAO caDAO;
	@Autowired
	OrdersDAO orDAO;
	@Autowired
	ProductService productService;
	@Autowired
	HttpSession session;

	@PostMapping("/add/save")
	public String addProduct(Model model, @Valid @ModelAttribute("products") Product product,
			BindingResult bindingResult, @RequestParam("image-file") MultipartFile photo)
			throws IllegalStateException, IOException {
		if (bindingResult.hasErrors()) {
//			System.out.println(bindingResult);
//			System.out.println("Loi");
			return "admin/products/addProduct";
		} else {

			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String strDate = formatter.format(date);
			System.out.println(strDate);
			product.setCreateDate(strDate);
			product.setAvailable(1);
			product.setSaled(0);
			if (photo.isEmpty()) {
				product.setImage(session.getAttribute("imageSP").toString());
			} else {
				product.setImage(photo.getOriginalFilename());
			}
			prDAO.save(product);
			if (!photo.isEmpty()) {
				String fileName = photo.getOriginalFilename();
				String path = new File("src/main/resources/static/images").getAbsolutePath();
				File file = new File(path + "/" + fileName);

				photo.transferTo(file);
			}
		}
		// Product product=new Product();
//		product.setAvailable(0);
//		product.setCategory(null);
//		product.setImage("");
//		product.setPrice(0);
//		product.setQuantity(0);
//		product.setName(idProduct);
//		product.setIdProduct(0);

		return "redirect:/admin/product";
	}

	@GetMapping("/edit/{id}")
	public ModelAndView edit(ModelMap model, @ModelAttribute("products") Product product,
			@PathVariable("id") int idProduct) {
		Optional<Product> otp = productService.findById(idProduct);
		ProductDto dto = new ProductDto();
		List<Product> list = prDAO.findByIdProduct(idProduct);
		model.addAttribute("list", list);
		System.out.println(otp.get());
		model.addAttribute("layId", idProduct);
		session.setAttribute("imageSP", otp.get().getImage());
		if (otp.isPresent()) {

			Product entity = otp.get();

			BeanUtils.copyProperties(otp, dto);

			dto.setIdProduct(idProduct);
			dto.setIsEdit(true);
			model.addAttribute("product", dto);

			productService.save(entity);

			return new ModelAndView("admin/products/addProduct", model);
		}

		model.addAttribute("Message", "Category is not exits");

		return new ModelAndView("redirect:/admin/product", model);

	}

	@GetMapping("/add")
	public String listCategory(Model model, @ModelAttribute("products") Product product) {

		return "admin/products/addProduct";
	}

	@GetMapping("")
	public String search(Model model, @RequestParam(name = "name", required = false) String name,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
		int curentPage = page.orElse(0);
		int pageSize = size.orElse(5);

		Pageable pageable = PageRequest.of(curentPage, pageSize, Sort.by("name"));
		Page<Product> resultPage = null;

		if (StringUtils.hasText(name)) {
			resultPage = prDAO.findByNameContainingAndAvailable(name, 1, pageable);
			model.addAttribute("name", name);
		} else {
			resultPage = prDAO.findByAvailable(1, pageable);
		}
		int totalPages = resultPage.getTotalPages();
		if (totalPages > 0) {
			int start = Math.max(1, curentPage - 2);
			int end = Math.min(curentPage + 2, totalPages);

			if (totalPages > 5) {
				if (end == totalPages)
					start = end - 5;
				else if (start == 1)
					end = start + 5;
			}
			List<Integer> pageNumber = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumber);
		}
		model.addAttribute("productPage", resultPage);
		System.out.println(prDAO.tongSanPham());
		session.setAttribute("tongSanPham", prDAO.tongSanPham());
		System.out.println(caDAO.soLuongDanhMuc());
		session.setAttribute("tongDanhMuc", caDAO.soLuongDanhMuc());
		System.out.println(prDAO.sanPhamDaBan());
		session.setAttribute("soLuongBanChayNhat", prDAO.sanPhamBanChayNhat());
		String sanPhamBanChayNhat = prDAO.findBySaled(Integer.parseInt(prDAO.sanPhamBanChayNhat()));
		session.setAttribute("tenSPbanChayNhat", sanPhamBanChayNhat);
		System.out.println(sanPhamBanChayNhat);
		return "admin/products/QuanLiSanPham";
	}

	@ModelAttribute("listCategory")
	public Iterable<Category> getAllCategory() {
		return caDAO.findByActivated(1);
	}

//	@GetMapping
//	@ResponseBody
//	public String danhSachCategory() {
//		
//		
//		return "";
//	}
//	

	@ModelAttribute("available")
	int[] getAvailable() {
		int[] availables = new int[] { 0, 1 };
		return availables;
	}

	@GetMapping("delete/{id}")
	public String delete(Model model, @PathVariable("id") Integer idProduct) {
		Optional<Product> prDuct = prDAO.findById(idProduct);

		prDuct.get().setAvailable(0);

		prDAO.save(prDuct.get());

		return "redirect:/admin/product";
	}

	@GetMapping("thongKe")
	public String thongKe() {
		System.out.println(prDAO.tongSanPham());
		session.setAttribute("tongSanPham", prDAO.tongSanPham());
		System.out.println(caDAO.soLuongDanhMuc());
		session.setAttribute("tongDanhMuc", caDAO.soLuongDanhMuc());
		System.out.println(prDAO.sanPhamDaBan());
		session.setAttribute("sanPhamDaBan", prDAO.sanPhamDaBan());
		session.setAttribute("soLuongBanChayNhat", prDAO.sanPhamBanChayNhat());
		String sanPhamBanChayNhat = prDAO.findBySaled(Integer.parseInt(prDAO.sanPhamBanChayNhat()));
		session.setAttribute("tenSPbanChayNhat", sanPhamBanChayNhat);
		System.out.println(sanPhamBanChayNhat);
		session.setAttribute("soLuongHoaDon", orDAO.soLuongHoaDon());
		session.setAttribute("doanhThu", orDAO.doanhThu());
		session.setAttribute("tongVon", orDAO.soVonNhapHang());
		return "admin/products/ThongKe";
	}
//	Map<String, String> getCategory(){
//		Map<String, String> categories=new HashMap<String, String>();
//		List<Category> list=new ArrayList<>();
//		list=caDAO.findAll();
//		for (Category category : list) {
//			String a=category.getIdCategory();
//			System.out.println(a);
//			categories.put("list", a);
//		}
//		return categories;
//	}
//	List<Category> list(){
//		List<Category> list=caDAO.findAll();
//		return list;
//	};
}
