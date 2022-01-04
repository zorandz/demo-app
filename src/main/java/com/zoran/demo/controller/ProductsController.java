package com.zoran.demo.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zoran.demo.domain.HttpResponse;
import com.zoran.demo.entities.ProductCategory;
import com.zoran.demo.services.ProductService;

@RestController
@RequestMapping("/products")
//@CrossOrigin(origins={"http://3.22.211.164", "http://zoran-dzoic.com"})
public class ProductsController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping("/add-product")
	//@PreAuthorize("hasAnyAuthority('user:read')")
    public ResponseEntity<HttpResponse> addNewProduct(@RequestParam("categoryId") Long categoryId,
    									   @RequestParam("categoryName") String categoryName,
                                           @RequestParam("sku") String sku,
                                           @RequestParam("name") String name,
                                           @RequestParam("description") String description,
                                           @RequestParam("unitPrice") BigDecimal unitPrice,
                                           @RequestParam("imageUrl") String imageUrl,
                                           @RequestParam("active") String active,
                                           @RequestParam("unitsInStock") Integer unitsInStock) 
                                        		   												{
		ProductCategory productCategory = new ProductCategory(categoryId, categoryName);
        productService.addNewProduct(productCategory, sku, name, description, unitPrice, imageUrl, 
        										Boolean.parseBoolean(active), unitsInStock);
        return response(HttpStatus.OK, "Product successfully added!");
    }
	
	@DeleteMapping(path="/delete/{id}")
	public ResponseEntity<HttpResponse> deleteProduct(@PathVariable("id") Long id) {
		this.productService.deleteProduct(id);
		return response(HttpStatus.NO_CONTENT, "Product successfully deleted!");
	}

	private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }
}
