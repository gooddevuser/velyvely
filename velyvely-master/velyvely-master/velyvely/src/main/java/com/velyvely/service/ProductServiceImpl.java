package com.velyvely.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.velyvely.mapper.ProductMapper;
import com.velyvely.vo.Member;
import com.velyvely.vo.Product;
import com.velyvely.vo.ProductFile;

@Service("productService")
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductMapper productMapper;

	@Override
	public void insertProduct(Product product) {
		productMapper.insertProduct(product);		
		
		for (ProductFile f : product.getFileList()) {
			//자동 증가로 만들어진 글번호를 파일 VO에 적용
			f.setProductid(product.getProductid());
		}
		productMapper.insertProductFileList(product.getFileList());
	}

	@Override
	public List<Product> selectProductByClothes() {
		List<Product> products = productMapper.selectProductByClothes();
		return products;
	}
	
	@Override
	public List<Product> selectProduct() {
		List<Product> products = productMapper.selectProduct();
		return products;
	}

	@Override
	public List<Product> selectProductByAccessories() {
		List<Product> products = productMapper.selectProductByAccessories();
		return products;
	}
	
	@Override
	public List<Product> selectProductByUnderclothes() {
		List<Product> products = productMapper.selectProductByUnderclothes();
		return products;
	}
	
	@Override
	public Product selectProductByproductid(int productid) {
		Product product  = productMapper.selectProductByproductid(productid);
		return product;
	}

	@Override
	public List<Product> selectProductByproductname(String productname) {
		List<Product> products = productMapper.selectProductByproductname(productname);
		return products;
	}

}
