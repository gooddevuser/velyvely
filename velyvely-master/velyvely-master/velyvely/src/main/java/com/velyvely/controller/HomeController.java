package com.velyvely.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.velyvely.common.Util;
import com.velyvely.service.MemberService;
import com.velyvely.service.ProductService;
import com.velyvely.vo.Member;
import com.velyvely.vo.MemberFile;
import com.velyvely.vo.Product;
import com.velyvely.vo.ProductFile;

@Controller
public class HomeController {	
	
	@Autowired
	@Qualifier("memberService")
	MemberService memberService;
	
	@Autowired
	@Qualifier("productService")
	ProductService productService;
	
	@GetMapping(path = { "/", "/home" })
	public String showHome() {
		
		return "home";
		
	}
	
	@GetMapping(path = { "cart" })
	public String showCart() {
		
		return "cart";
		
	}
	
	@GetMapping(path = { "categories" })
	public String showCategories() {
		
		return "categories";
		
	}
	
	@GetMapping(path = { "checkout" })
	public String showCheckout() {
		
		return "checkout";
		
	}
	
	@GetMapping(path = { "contact" })
	public String showContact() {
		
		return "contact";
		
	}
		
	@GetMapping(path = { "adminBanner" })
	public String showAdminbanner() {
		
		return "adminBanner";
		
	}
	
	@GetMapping(path = { "productdetail" })
	public String showProductdetail(int productid,Model model) {
		
		Product product = productService.selectProductByproductid(productid);
		model.addAttribute("product",product);
		
		return "productdetail";
	}
	
	@GetMapping(path = { "login" })
	public String showLogin() {
		
		return "login";
		
	}
	
	@GetMapping(path = { "clothes" })
	public String showClothes(Model model) {
		List<Product> products = productService.selectProductByClothes();
		model.addAttribute("products",products);
		return "clothes";
		
	}
	
	
	@GetMapping(path = { "accessories" })
	public String showAccessories(Model model) {
		List<Product> products = productService.selectProductByAccessories();
		model.addAttribute("products",products);
		return "accessories";
		
	}
	
	@GetMapping(path = { "underclothes" })
	public String showUnderclothes(Model model) {
		List<Product> products = productService.selectProductByUnderclothes();
		model.addAttribute("products",products);
		return "underclothes";
		
	}
	
	@GetMapping(path = { "product" })
	public String showProduct() {
		
		return "product";
		
	}
	
	@PostMapping(path = { "product" })
	public String doSearch(Model model,String productname) {
		
		List<Product> products = productService.selectProductByproductname(productname);
		if (products == null) {
			System.out.println("등록실패");
		} else {
			model.addAttribute("products", products);
		}
		return "product";		
	}
	
	
	
	
	@PostMapping(path = { "login" })
	public String doLogin(Member member,HttpSession session) {		
		Member member2 = memberService.selectMemberBymemberidAndpasswd(member);
		
		if (member2 == null) {
			return "login";
		}
		session.setAttribute("loginuser", member2);
		return "home";		
	}
	
	@GetMapping(path = { "logout" })
	public String showLogout(HttpSession session) {
		session.removeAttribute("loginuser");
		return "home";
		
	}
	
	@GetMapping(path = { "register" })
	public String showRegister() {
		
		return "register";
		
	}
	
	@PostMapping(path = { "register" })
	public String doRegister(Member member, MultipartHttpServletRequest req) {
		List<MemberFile> files = parseAndSaveUploadMemberFiles(req);
		member.setFileList(files);
		
		try {
			memberService.insertMember(member);
			
		} catch (Exception ex) {
			System.out.println("등록 실패");
			ex.printStackTrace();			
		}
				
		return "home";
		
	}
	
	@GetMapping(path = { "adminMemberList", "admin" })
	public String showAdmin(Model model) {
		List<Member> members = memberService.selectMembers();
		model.addAttribute("members",members);
		return "adminMemberList";
		
	}
	
	@GetMapping(path = { "adminProduct" })
	public String showAdminProduct() {
		return "adminProduct";
		
	}
	
	@GetMapping(path = { "adminProductList" })
	public String showAdminProductList(Model model) {
		List<Product> products = productService.selectProduct();
		model.addAttribute("products",products);
		return "adminProductList";
		
	}
	
	@PostMapping(path = { "adminProduct" })
	public String doAdminProduct(Product product, MultipartHttpServletRequest req) {		
		List<ProductFile> files = parseAndSaveUploadProductFiles(req);
		product.setFileList(files);
		
		try {
			productService.insertProduct(product);
			
		} catch (Exception ex) {
			System.out.println("등록 실패");
			ex.printStackTrace();			
		}
				
		return "home";
		
	}
	
	//////////////////////////////////////////////////////
	
	List<ProductFile> parseAndSaveUploadProductFiles(MultipartHttpServletRequest req) {
	
	ArrayList<ProductFile> productFiles = new ArrayList<>();
	
	if (!ObjectUtils.isEmpty(req)) {
	
	String dirPath = req.getServletContext().getRealPath("/upload-files/");
	
	System.out.println(dirPath);
	
	Iterator<String> iter = req.getFileNames();
	while(iter.hasNext()) { // 다음 항목이 있는지 확인
	String name = iter.next(); // 다음 항목 반환
	List<MultipartFile> files = req.getFiles(name); // 파일 들의 이름을 리스트 꼴로 여러가지 담음.
	
	
	for(MultipartFile file : files) {
	String originalFileName = file.getOriginalFilename();
	String uniqueFileName = Util.makeUniqueFileName(originalFileName);
	try {
	
	//파일을 Disk에 저장
	file.transferTo(new File(dirPath, uniqueFileName));
	
	//파일 정보를 VO에 저장하고 목록에 추가 ( -> DB에 저장 )
	ProductFile productFile = new ProductFile();
	productFile.setUserfilename(originalFileName);
	productFile.setSavedfilename(uniqueFileName);
	productFile.setFilesize(file.getSize());
	productFile.setCreateddatetime(new Date());
	productFile.setCreatorid("");
	productFiles.add(productFile);
	
	} catch (Exception e) {
	e.printStackTrace();
	}
	}
	}
	}
	
	return productFiles;
	}


	//////////////////////////////////////////////////////
	
	List<MemberFile> parseAndSaveUploadMemberFiles(MultipartHttpServletRequest req) {
	
	ArrayList<MemberFile> memberFiles = new ArrayList<>();
	
	if (!ObjectUtils.isEmpty(req)) {
	
	String dirPath = req.getServletContext().getRealPath("/upload-files/");
	
	System.out.println(dirPath);
	
	Iterator<String> iter = req.getFileNames();
	while(iter.hasNext()) { // 다음 항목이 있는지 확인
	String name = iter.next(); // 다음 항목 반환
	List<MultipartFile> files = req.getFiles(name); // 파일 들의 이름을 리스트 꼴로 여러가지 담음.
	
	
	for(MultipartFile file : files) {
	String originalFileName = file.getOriginalFilename();
	String uniqueFileName = Util.makeUniqueFileName(originalFileName);
	try {
	
	//파일을 Disk에 저장
	file.transferTo(new File(dirPath, uniqueFileName));
	
	//파일 정보를 VO에 저장하고 목록에 추가 ( -> DB에 저장 )
	MemberFile memberFile = new MemberFile();
	memberFile.setUserfilename(originalFileName);
	memberFile.setSavedfilename(uniqueFileName);
	memberFile.setFilesize(file.getSize());
	memberFile.setCreateddatetime(new Date());
	memberFile.setCreatorid("");
	memberFiles.add(memberFile);
	
	} catch (Exception e) {
	e.printStackTrace();
	}
	}
	}
	}
	
	return memberFiles;
	}
}
