package com.sathya.productmanagement.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sathya.productmanagement.entity.ProductEntity;
import com.sathya.productmanagement.models.Product;
import com.sathya.productmanagement.service.ProductManageService;

@Controller
public class ProductManageController {
	@Autowired
	ProductManageService productManageService;
	
	@GetMapping("/productform")
	public String getProductForm(Model model) {
		Product product = new Product();
		model.addAttribute(product);
		return 	"product-manage";
	}
	
	
	@GetMapping("/about")
	public String getAbout(Model model) {
		Product product = new Product();
		model.addAttribute(product);
		return 	"about";
	}
	
	@GetMapping("/addProduct")
	public String getForm(Model model) {
		Product product = new Product();
		model.addAttribute(product);
		return "add-product" ;
	}
	
	@GetMapping("/products")
	public String showAllProducts(
	        @RequestParam(value = "keyword", required = false) String keyword,
	        Model model) {

	    List<ProductEntity> products;

	    if (keyword != null && !keyword.trim().isEmpty()) {
	        // ✅ Search logic
	        products = productManageService.searchProducts(keyword.trim());
	        model.addAttribute("keyword", keyword);
	    } else {
	        // ✅ Show all products
	        products = productManageService.getAllProducts();
	    }

	    model.addAttribute("getproducts", products);
	    return "product-list";
	}

	
	@PostMapping("/saveProduct")
	public String saveProduct(@ModelAttribute Product product,
	                          @RequestParam("imageFile") MultipartFile imageFile,
	                          RedirectAttributes redirectAttributes) {

	    try {
	        if (!imageFile.isEmpty()) {

	            // ✅ Folder name as 'uploads/'
	            String folderName = "uploads/";

	            // ✅ Create folder if it doesn't exist
	            File uploadDir = new File(folderName);
	            if (!uploadDir.exists()) {
	                uploadDir.mkdir();
	                System.out.println("📁 Upload folder created successfully!");
	            }

	            // ✅ Get original file name
	            String originalFileName = imageFile.getOriginalFilename();

	            // ✅ Create file path using Path API
	            Path destinationPath = Path.of(uploadDir.getAbsolutePath(), originalFileName);

	            // ✅ Save the file (replace if already exists)
	            Files.copy(imageFile.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

	            // ✅ Set relative image URL for DB
	            product.setImageurl(folderName + originalFileName);
	        }

	        // ✅ Save product details via service
	        boolean isSaved = productManageService.addProduct(product);

	        // ✅ Flash message based on success
	        if (isSaved) {
	            redirectAttributes.addFlashAttribute("successMsg", "✅ Product saved successfully!");
	        } else {
	            redirectAttributes.addFlashAttribute("errorMsg", "❌ Product saving failed!");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        redirectAttributes.addFlashAttribute("errorMsg", "⚠️ Error saving product: " + e.getMessage());
	    }

	    // ✅ Redirect to product list
	    return "redirect:/products";
	}

	
	@GetMapping("/editProduct/{id}")
	public String editProduct(@PathVariable("id") Long id, Model model) {
	    ProductEntity product = productManageService.getProductById(id);
	    model.addAttribute("product", product);
	    return "edit-product"; 
	}
	
	


	@PostMapping("/updateProduct/{id}")
	public String updateProduct(@PathVariable("id") Long id,
	                            @ModelAttribute Product product,
	                            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
	                            RedirectAttributes redirectAttributes) {

	    try {
	        productManageService.updateProduct(id, product, imageFile);
	        redirectAttributes.addFlashAttribute("successMsg", "✅ Product updated successfully!");
	    } catch (Exception e) {
	        e.printStackTrace();
	        redirectAttributes.addFlashAttribute("errorMsg", "⚠️ Error updating product: " + e.getMessage());
	    }

	    return "redirect:/products";
	}


	@GetMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
	    try {
	        productManageService.deleteProduct(id);
	        redirectAttributes.addFlashAttribute("successMsg", "🗑️ Product deleted successfully!");
	    } catch (Exception e) {
	        redirectAttributes.addFlashAttribute("errorMsg", "⚠️ Error deleting product: " + e.getMessage());
	    }
	    return "redirect:/products";
	}

	@GetMapping("/contact")
	public String contactPage() {
	    return "contact";
	}
	
}
