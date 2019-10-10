package kr.co.itcen.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.CategoryVo;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;
	
	public Boolean insert(CategoryVo vo) {
		int count = sqlSession.insert("category.insert", vo);
		return count == 1;
	}
	
	public List<CategoryVo> getList() {
		List<CategoryVo> result = sqlSession.selectList("category.getList");
		return result;
	}
	
	public void delete(Long no) {
		sqlSession.delete("category.delete", no);
	}
}
