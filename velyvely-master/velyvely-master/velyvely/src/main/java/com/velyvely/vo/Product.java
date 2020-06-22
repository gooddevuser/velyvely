package com.velyvely.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Product {

	public int productid;
	public String categoryA;
	public String categoryB;
	public String productname;
	public String contents;
	public String color;
	public boolean ssize;
	public boolean msize;
	public boolean lsize;
	public boolean elsize;
	public int amount;
	public int price;
	public int saleprice;
	public boolean shipping;
	private Date createddatetime;
	
	private List<ProductFile> fileList;
}