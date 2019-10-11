package kr.co.itcen.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.CategoryDao;
import kr.co.itcen.jblog.vo.CategoryVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	public void insert(CategoryVo vo) {
		categoryDao.insert(vo);
	}
	
	public Boolean add(CategoryVo vo) {
		return categoryDao.insert(vo);
	}
	
	public List<CategoryVo> list(String blogId) {
		return categoryDao.getList(blogId);
	}

	public void delete(Long no) {
		categoryDao.delete(no);
	}
}
