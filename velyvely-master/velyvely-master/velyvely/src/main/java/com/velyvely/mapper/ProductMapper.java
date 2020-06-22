package com.velyvely.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.velyvely.vo.Product;
import com.velyvely.vo.ProductFile;

@Mapper
public interface ProductMapper {

	void insertProduct(Product product);
	
	void insertProductFileList(List<ProductFile> files);
	
	List<Product> selectProduct();
	
	List<Product> selectProductByClothes();

	List<Product> selectProductByAccessories();
	
	List<Product> selectProductByUnderclothes();
	
	Product selectProductByproductid(int productid);

	List<Product> selectProductByproductname(String productname);
	
}
