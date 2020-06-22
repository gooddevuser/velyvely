package com.velyvely.vo;

import java.util.Date;

import lombok.Data;

@Data
public class ProductFile {
	
	private int idx;
	private int productid;
	private String userfilename;
	private String savedfilename;
	private long filesize;
	private String creatorid;
	private Date createddatetime;
}
