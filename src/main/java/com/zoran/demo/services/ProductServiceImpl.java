package com.zoran.demo.services;
import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoran.demo.entities.Product;
import com.zoran.demo.entities.ProductCategory;
import com.zoran.demo.repositories.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	ProductRepository productRepo;
	
	@Autowired
	public ProductServiceImpl (ProductRepository productRepo) {
		this.productRepo = productRepo;
	}

	@Override
	public void addNewProduct(ProductCategory productCategory, String sku, String name, String description,
			BigDecimal unitPrice, String imageUrl, boolean active, Integer unitsInStock) {
		Product product = new Product();
		System.out.println("Kategorija: " + productCategory.getCategoryName() + " ID: " + productCategory.getId());
		productCategory.addProduct(product);
		product.setCategory(productCategory);
        product.setSku(sku);
        product.setName(name);
        product.setDescription(description);
        product.setUnitPrice(unitPrice);
        product.setImageUrl(imageUrl);
        product.setActive(active);
        product.setUnitsInStock(unitsInStock);
        this.productRepo.save(product);
        return;
	}

	@Override
	public void deleteProduct(Long id) {
		this.productRepo.deleteById(id);		
	}
	
	
	
	
	

}
