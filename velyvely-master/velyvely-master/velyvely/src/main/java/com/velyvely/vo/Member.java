package com.velyvely.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Member {

	public int memberid;
	public String usertype;
	public String id;
	public String passwd;
	public String name;
	public String email;	
	public String gender;
	public int birthday;
	public String phone;
	public String address;
	private Date createddatetime;
	
	private List<MemberFile> fileList;
}
