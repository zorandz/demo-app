package com.zoran.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.zoran.demo.entities.ProductCategory;

@RepositoryRestResource(collectionResourceRel = "productCategory", path="product-category")
@CrossOrigin(origins = "http://localhost:4200")
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

}
