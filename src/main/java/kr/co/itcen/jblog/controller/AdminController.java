package kr.co.itcen.jblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.itcen.jblog.service.BlogService;
import kr.co.itcen.jblog.service.CategoryService;
import kr.co.itcen.jblog.service.PostService;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.PostVo;

@Controller
@RequestMapping( "/{id:(?!assets).*}/admin" )
public class AdminController {
	
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;
	
	//----------------------------------기본설정-----------------------------------
	@RequestMapping( "/basic" )
	public String basic( @PathVariable("id") String id, Model model) {
		BlogVo vo = blogService.list(id);
		model.addAttribute("vo", vo);
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping(value="/basic/update", method=RequestMethod.POST)
	public String blogUpdate( @PathVariable("id") String id, 
			@RequestParam(value="title", required = true, defaultValue = "") String title,
			@RequestParam(value="logo-file", required = false) MultipartFile multipartFile,
			BlogVo vo) {
		String logo = blogService.restore(multipartFile);
		vo.setBlogId(id);
		vo.setTitle(title);
		vo.setLogo(logo);
		blogService.update(vo);
		return "redirect:/"+id;
	}
	
	//----------------------------------카테고리-----------------------------------
	@RequestMapping("/category")
	public String category( @PathVariable("id") String id, Model model) {
		List<CategoryVo> vo = categoryService.list();
		model.addAttribute("list", vo);
		return "blog/blog-admin-category";
	}
	
	@RequestMapping(value="/category/add", method=RequestMethod.POST)
	public String catAdd(@PathVariable("id") String id, CategoryVo vo) {
		vo.setUserId(id);
		categoryService.insert(vo);
		return "redirect:/"+id+"/admin/category";
	}
	
	@RequestMapping(value="/category/delete/{no}", method = RequestMethod.GET)
	public String catDelete(@PathVariable("id") String id, @PathVariable("no") Long no) {
		categoryService.delete(no);
		return "redirect:/"+id+"/admin/category";
	}
	
	//----------------------------------글작성-----------------------------------
	@RequestMapping("/write")
	public String write( @PathVariable("id") String id, Model model ) {
		List<CategoryVo> vo = categoryService.list();
		model.addAttribute("list", vo);
		return "blog/blog-admin-write";
	}
	
	@RequestMapping(value = "/write/add", method=RequestMethod.POST)
	public String postAdd( @PathVariable("id") String id, PostVo vo, @RequestParam("category")Long no) {
		vo.setCategoryNo(no);
		postService.insert(vo);
		return "redirect:/"+id;
	}
	
	
}
