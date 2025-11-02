package com.sathya.productmanagement.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sathya.productmanagement.entity.ProductEntity;
import com.sathya.productmanagement.models.Product;
import com.sathya.productmanagement.repository.ProductManageRepo;

@Service
public class ProductManageService {

	@Autowired
	ProductManageRepo productManageRepo;

	public List<ProductEntity> getAllProducts() {
		return productManageRepo.findAll();
		
	}
	
	
	public boolean addProduct(Product product) {
	    try {
	        // ✅ 1️ Create new Entity object
	        ProductEntity entity = new ProductEntity();

	        // ✅ 2️ Copy fields from Model → Entity
	        entity.setName(product.getName());
	        entity.setPrice(product.getPrice());
	        entity.setBrand(product.getBrand());
	        entity.setQuantity(product.getQuantity());
	        entity.setImageurl(product.getImageurl());

	        // ✅ 3️ Auto-set backend fields
	        entity.setOwner(System.getProperty("user.name"));  // System user name
	        entity.setCreatedAt(LocalDateTime.now());          // Current timestamp

	        // ✅ 4️ Apply discount & total price logic
	        double price = product.getPrice() != null ? product.getPrice() : 0.0;
	        double discount = 0.0;

	        if (price > 50000) {
	            discount = price * 0.15; // 15% discount
	        } else if (price > 10000) {
	            discount = price * 0.10; // 10% discount
	        } else {
	            discount = price * 0.05; // 5% discount
	        }

	        double total = price - discount;

	        entity.setDiscountPrice(discount);
	        entity.setTotalPrice(total);

	        // ✅ 5️ Save product into database
	        productManageRepo.save(entity);

	        return true;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	
	public void updateProduct(Long id, Product updatedProduct, MultipartFile imageFile) throws IOException {
	    ProductEntity existing = productManageRepo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));

	    existing.setName(updatedProduct.getName());
	    existing.setPrice(updatedProduct.getPrice());
	    existing.setBrand(updatedProduct.getBrand());
	    existing.setQuantity(updatedProduct.getQuantity());

	    if (imageFile != null && !imageFile.isEmpty()) {
	        String folderName = "uploads/";
	        File uploadDir = new File(folderName);
	        if (!uploadDir.exists()) uploadDir.mkdir();

	        String originalFileName = imageFile.getOriginalFilename();
	        Path destinationPath = Path.of(uploadDir.getAbsolutePath(), originalFileName);
	        Files.copy(imageFile.getInputStream(), destinationPath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
	        existing.setImageurl(folderName + originalFileName);
	    }

	    productManageRepo.save(existing);
	}


	public ProductEntity getProductById(Long id) {
    return productManageRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
}


	public void deleteProduct(Long id) {
	    ProductEntity product = productManageRepo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));

	    productManageRepo.delete(product);
	}


	public List<ProductEntity> searchProducts(String keyword) {
	    keyword = keyword.trim();
	    return productManageRepo.searchByIdOrNameOrBrand(keyword);
	}





}
