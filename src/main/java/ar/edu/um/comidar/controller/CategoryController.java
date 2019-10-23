package ar.edu.um.comidar.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.UploadErrorException;

import ar.edu.um.comidar.entity.Category;
import ar.edu.um.comidar.services.CategoryService;
import ar.edu.um.comidar.services.DocumentService;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {

	private final static Logger logger = LoggerFactory.getLogger(RestaurantController.class);
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private DocumentService imageService;

	public CategoryController() {
		super();
	}

	@GetMapping("/list")
	public String categories(Model model) {

		model.addAttribute("categoryList", categoryService.findAll());
		
		return "page/category/index";
	}
	
	@GetMapping("/new")
	public String newCategory(Model model){
		Category category = new Category();
		model.addAttribute("category", category);
		
		return "page/category/new";
	}
	
	@PostMapping("/new")
	public String newCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model, final RedirectAttributes redirectAttributes) throws UploadErrorException, DbxException, IOException {
	
		if(!result.hasErrors()) {
			categoryService.create(category);
			category.setImageUrl("/categories/" + category.getCategoryId() + "." + category.getCategoryImage().getExtension());
			imageService.uploadDocument(category.getCategoryImage().getFile().getInputStream(), category.getImageUrl());
			categoryService.update(category);
			redirectAttributes.addFlashAttribute("message","Actualizacion realizada");
			redirectAttributes.addFlashAttribute("css","alert-success");
			return "redirect:/admin/category/list";
		} else {
			for(ObjectError error : result.getAllErrors()) {
				logger.error("Validacion {}",error.getDefaultMessage());
			}
		}		
		return "page/category/new";
	}
	
	@GetMapping("/update")
	public String updateCategory(@RequestParam(value="id",required=true) Long id, Model model){
		model.addAttribute("category",categoryService.findById(id));
		
		return "page/category/update";
	}
	
	@PostMapping("/update")
	public String updateCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model, final RedirectAttributes redirectAttributes) throws UploadErrorException, DbxException, IOException {

		if(!result.hasErrors()) {
			categoryService.update(category);
			category.setImageUrl("/categories/" + category.getCategoryId() + "." + category.getCategoryImage().getExtension());
			if(!category.getCategoryImage().getFile().isEmpty()) {
				imageService.deleteDocument(category.getImageUrl());
				imageService.uploadDocument(category.getCategoryImage().getFile().getInputStream(), category.getImageUrl());
			}
			redirectAttributes.addFlashAttribute("message","Actualizacion realizada");
			redirectAttributes.addFlashAttribute("css","alert-success");
			return "redirect:/admin/category/list";
		} else {
			for(ObjectError error : result.getAllErrors()) {
				logger.error("Validacion {}",error.getDefaultMessage());
			}
		}
		
		return "page/category/update";
	}
	
	@GetMapping("/delete")
	public String deleteCategory(@RequestParam(value="id",required=true) Long id, final RedirectAttributes redirectAttributes) throws UploadErrorException, DbxException, IOException{
		Category category = categoryService.findById(id);
		categoryService.remove(category);
		redirectAttributes.addFlashAttribute("message","Se ha eliminado el usuario exitosamente");
		redirectAttributes.addFlashAttribute("css","alert-success");
		
		return "redirect:/admin/category/list";
	}

	@GetMapping("/list/all")
	public ResponseEntity<List<Category>> sendCategoryList() throws UploadErrorException, DbxException, IOException{
		List<Category> categoryList = categoryService.findAll();
		for (Category category : categoryList) {
			category.setImageTemporaryUrl(imageService.getDocumentURL(category.getImageUrl()));
		}
		
		return new ResponseEntity<>(categoryList,HttpStatus.OK);
	}
}
