package kr.co.itcen.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.PostVo;

@Repository
public class PostDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public Boolean insert(PostVo vo) {
		int count = sqlSession.insert("post.insert", vo);
		return count == 1;
	}
	
	public List<PostVo> getList() {
		List<PostVo> result = sqlSession.selectList("post.getList");
		return result;
	}
	
	public void delete(Long no) {
		sqlSession.delete("post.delete", no);
	}
}
