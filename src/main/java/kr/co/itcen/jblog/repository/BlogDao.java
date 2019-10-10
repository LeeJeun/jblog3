package kr.co.itcen.jblog.repository;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.BlogVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;
	
	public Boolean update(BlogVo vo) {
		int count = sqlSession.insert("blog.update", vo);
		return count == 1;
	}

	public BlogVo getList(String blogId) {
		BlogVo result = sqlSession.selectOne("blog.getList", blogId);
		return result;
	}
}
