package kr.co.itcen.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.BlogDao;

@Service
public class BlogService {

	@Autowired // 의존성주입
	private BlogDao blogDao;
}
