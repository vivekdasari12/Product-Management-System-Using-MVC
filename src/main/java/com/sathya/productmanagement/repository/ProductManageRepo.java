package com.sathya.productmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sathya.productmanagement.entity.ProductEntity;
import com.sathya.productmanagement.models.Product;

@Repository
public interface ProductManageRepo extends JpaRepository<ProductEntity, Long>{

	@Query("SELECT p FROM ProductEntity p WHERE " +
	           "CAST(p.id AS string) LIKE %:keyword% OR " +   // ✅ Search by ID
	           "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +  // ✅ Search by Name
	           "LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%'))")      // ✅ Search by Brand
	    List<ProductEntity> searchByIdOrNameOrBrand(@Param("keyword") String keyword);

}
