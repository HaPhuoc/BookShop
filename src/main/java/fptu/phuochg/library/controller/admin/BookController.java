package fptu.phuochg.library.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import fptu.phuochg.library.domain.Book;
import fptu.phuochg.library.domain.Category;
import fptu.phuochg.library.model.BookDto;
import fptu.phuochg.library.model.CategoryDto;
import fptu.phuochg.library.service.BookService;
import fptu.phuochg.library.service.CategoryService;
import fptu.phuochg.library.service.StorageService;


@Controller
@RequestMapping("admin/books")
public class BookController {
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	BookService bookService;
	
	@Autowired
	StorageService storageService;
	
	public List<CategoryDto> getCategories(){
		return categoryService.findAll().stream().map(item->{
			CategoryDto dto = new CategoryDto();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).collect(Collectors.toList());
	}
	@ModelAttribute("categories")
	public Iterable<Category> getAllCategory(){
		return categoryService.findAll();
	}

	@GetMapping("add")
	public String add(Model model) {
		BookDto dto = new BookDto();
		dto.setIsEdit(false);
		model.addAttribute("book", dto);
		
		return "admin/books/addOrEdit";
	}
		
	@GetMapping("edit/{bookId}")
	public ModelAndView edit(ModelMap model, @PathVariable("bookId") Long bookId) {

		Optional<Book> opt = bookService.findById(bookId);
		BookDto dto = new BookDto();

		if (opt.isPresent()) {
			Book entity = opt.get();

			BeanUtils.copyProperties(entity, dto);
			
			dto.setCategoryId(entity.getCategory().getCategoryId());
			dto.setIsEdit(true);

			model.addAttribute("book", dto);

			return new ModelAndView("admin/books/addOrEdit", model);
		}
		model.addAttribute("message", "Book is not existed");

		return new ModelAndView("forward:/admin/books", model);

	}

	@GetMapping("/images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable  String filename){
		Resource file = storageService.loadAsResource(filename);
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
	@GetMapping("delete/{bookId}")
	public ModelAndView delete(ModelMap model, @PathVariable("bookId") Long bookId) throws IOException {
		
		Optional<Book> opt = bookService.findById(bookId);
		
		if(opt.isPresent()) {
			if(!StringUtils.isEmpty(opt.get().getImage())) {
				storageService.delete(opt.get().getImage());
			}
			bookService.deleteById(bookId);
			
			model.addAttribute("message", "Book is deleted!");
		}else {
			model.addAttribute("message", "Book is not Found!");
		}
		
		
	

		return new ModelAndView("forward:/admin/books", model);
	}

	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("book") BookDto dto,@RequestParam("imageFile")MultipartFile photo,
			BindingResult result) throws IllegalStateException, IOException {

		if (result.hasErrors()) {

			return new ModelAndView("admin/books/addOrEdit");
		}
		Book entity = new Book();
		BeanUtils.copyProperties(dto, entity);
		
		Category category = new Category();
		category.setCategoryId(dto.getCategoryId());
		entity.setCategory(category);
		
		if(!dto.getImageFile().isEmpty()) {
			UUID uuid = UUID.randomUUID();
			String  uuString = uuid.toString();
			
			entity.setImage(photo.getOriginalFilename());
			storageService.store(dto.getImageFile(), entity.getImage());
			System.out.println(photo.getOriginalFilename());
		}
		
		if (!photo.isEmpty()) {
			String fileName = photo.getOriginalFilename();
			String path = new File("src/main/resources/static/images").getAbsolutePath();
			File file = new File(path + "/" + fileName);

			photo.transferTo(file);
		}
		
		bookService.save(entity);

		model.addAttribute("message", "Book is saved! ");
		return new ModelAndView("forward:/admin/books", model);
	}

	@RequestMapping("")
	public String list(ModelMap model) {

		List<Book> list = bookService.findAll();

		model.addAttribute("books", list);

		return "admin/books/list";
	}

	@GetMapping("search")
	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name) {

		List<Category> list = null;

		if (StringUtils.hasText(name)) {
			list = categoryService.findByNameContaining(name);
		} else {
			list = categoryService.findAll();
		}

		model.addAttribute("books", list);

		return "admin/books/search";
	}

	@GetMapping("searchpaginated")
	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name,
			@RequestParam("page") Optional<Integer> page, 
			@RequestParam("size") Optional<Integer> size) {
		
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);

		Pageable pageable = PageRequest.of(currentPage-1, pageSize, Sort.by("name"));
		Page<Category> resultPage = null;

		if (StringUtils.hasText(name)) {
			resultPage = categoryService.findByNameContaining(name, pageable);
			model.addAttribute("name", name);
			
		} else {
			resultPage = categoryService.findAll(pageable);
		}
		int totalPages = resultPage.getTotalPages();

		if (totalPages > 0) {
			int start = Math.max(1, currentPage-2);
			int end = Math.min(currentPage + 2, totalPages);
			
			if (totalPages > 5) {
				if (end == totalPages) start = end - 5 ;
				else if ( start == 1 ) end =start + 5; 
			}
			List <Integer> pageNumbers = IntStream.rangeClosed(start, end)
				.boxed()
				.collect(Collectors.toList());
			
			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("categoryPage",resultPage);
		
		return "admin/books/searchpaginated";
	}
		
}
