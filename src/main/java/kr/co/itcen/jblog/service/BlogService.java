package kr.co.itcen.jblog.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import kr.co.itcen.jblog.exception.FileuploadException;
import kr.co.itcen.jblog.repository.BlogDao;
import kr.co.itcen.jblog.vo.BlogVo;

@Service
public class BlogService {

	@Autowired // 의존성주입
	private BlogDao blogDao;
	
	public void update(BlogVo vo) {
		blogDao.update(vo);
	}
	
	public BlogVo list(String blogId) {
		return blogDao.getList(blogId);
	}
	
	private static final String SAVE_PATH = "/uploads";
	private static final String URL_PREFIX = "/images";
	
	public String restore(MultipartFile multipartFile) {
		String url = "";
		try {
			if(multipartFile == null) {
				return url;
			}
			
			String originalFilename = multipartFile.getOriginalFilename();
			String saveFileName = generateSaveFilename(originalFilename.substring(originalFilename.lastIndexOf('.')+1));
			long fileSize = multipartFile.getSize();
			
			System.out.println("###########################"+originalFilename);
			System.out.println("###########################"+saveFileName);
			System.out.println("###########################"+fileSize);
		
			byte[] fileData = multipartFile.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
			os.write(fileData);
			os.close();
			
			url = URL_PREFIX + "/" + saveFileName;
		} catch (IOException e) {
			throw new FileuploadException();
		}
				
		return url;
	}

	private String generateSaveFilename(String extName) {
		String filename="";
		Calendar calendar = Calendar.getInstance();
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);
		return filename;
	}

	public ModelMap getAll(String id, Long categoryNo, Long postNo) {
		ModelMap modelMap = new ModelMap();
		modelMap.put("blogVo", list(id));

		modelMap.addAllAttributes(blogDao.getAll(id, categoryNo, postNo));
		
		return modelMap;
	}

	
}
