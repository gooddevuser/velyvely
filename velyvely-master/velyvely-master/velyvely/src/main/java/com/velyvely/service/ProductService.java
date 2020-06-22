package com.velyvely.service;

import java.util.List;

import com.velyvely.vo.Product;

public interface ProductService {

	void insertProduct(Product product);
	
	List<Product> selectProduct();
	
	List<Product> selectProductByClothes();	

	List<Product> selectProductByAccessories();
	
	List<Product> selectProductByUnderclothes();

	Product selectProductByproductid(int productid);

	List<Product> selectProductByproductname(String productname);
	
}
