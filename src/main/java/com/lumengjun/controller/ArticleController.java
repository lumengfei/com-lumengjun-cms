package com.lumengjun.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;



























import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;






import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;




import com.github.pagehelper.PageInfo;
import com.lumengjun.cms.utils.FileUtils;
import com.lumengjun.cms.utils.HtmlUtils;
import com.lumengjun.common.FileResult;
import com.lumengjun.entity.Article;
import com.lumengjun.entity.Category;
import com.lumengjun.entity.Channel;
import com.lumengjun.entity.User;
import com.lumengjun.momme.Cms;
import com.lumengjun.service.ArticleService;

/*
 * 
 */
@RequestMapping("/article")
@Controller
public class ArticleController {

	@Value("${upload.path}")
	String picRootPath;
	
	@Value("${pic.path}")
	String picUrl;
	
	
	private static Logger log = Logger.getLogger(ArticleController.class);
	
	@Autowired
	ArticleService articleService;
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/articles")
	public String articles(HttpServletRequest request,@RequestParam(defaultValue="1") int page) {
		
		User loginUser = (User)request.getSession().getAttribute(Cms.USER);
		
		PageInfo<Article> articlePage = articleService.listByUser(loginUser.getId(),page);
		
		request.setAttribute("articlePage", articlePage);
		
		return "/user/articles/list";
	}
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/comments")
	public String comments() {
		
		
		return "/user/comments/list";
	}
	
	/**
	 * 个人设置
	 * @return
	 */
	@RequestMapping(value="postArticle",method=RequestMethod.GET)
	public String personal(Model m) {
		//m.addAttribute(Cms.ARTICLE, new Article());
		List<Channel> list = articleService.channelList();
		m.addAttribute(Cms.CHANNEL, list);
		return "/user/articles/add";
	}
	
	@RequestMapping(value="postArticle",method=RequestMethod.POST)
	@ResponseBody
	public boolean postArticle(HttpServletRequest request, Article article, 
			MultipartFile file
			) throws IOException {
		
		
		String picUrl;
		try {
			// 处理上传文件
			picUrl = processFile(file);
			article.setPicture(picUrl);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//当前用户是文章的作者
		User loginUser = (User)request.getSession().getAttribute(Cms.USER);
		article.setUserId(loginUser.getId());
		
		
		return articleService.add(article)>0;
		
		
		
	}
	
	
	private String processFile(MultipartFile file) {
		// 判断目标目录时间否存在
				//picRootPath + ""
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String subPath = sdf.format(new Date());
				//图片存放的路径
				File path= new File(picRootPath+"/" + subPath);
				//路径不存在则创建
				if(!path.exists())
					path.mkdirs();
				
				//计算新的文件名称
				String suffixName = FileUtils.getSuffixName(file.getOriginalFilename());
				
				//随机生成文件名
				String fileName = UUID.randomUUID().toString() + suffixName;
				//文件另存
				try {
					file.transferTo(new File(picRootPath+"/" + subPath + "/" + fileName));
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return  subPath + "/" + fileName;
	}
	@RequestMapping("/deletearticle")
	@ResponseBody
	public Object deletearticle(Integer id){
		int i =articleService.deletearticle(id);
		if(i<0){
			return false;
		}
		return true;
	}
	
	@RequestMapping("/getCategoris")
	@ResponseBody
	public Object getCategoris(Integer cid){
		List<Category> list = articleService.categoryList(cid);
		return list;
		
	}
	
	
	
	@RequestMapping(value="update",method=RequestMethod.GET)
	public String update(Integer id,HttpServletRequest request){
		System.out.println(1111);
		Article article=articleService.getArticleId(id);
		User user = (User)request.getSession().getAttribute(Cms.USER);
		System.out.println(article.getUserId());
		System.out.println(user.getId());
		if(article.getUserId()!=user.getId()){
			request.getSession().removeAttribute(Cms.USER);
			request.setAttribute(Cms.USER,new User());
			request.setAttribute("eror", "未知错误");
			return "/user/login";
		}
		
		List<Channel> list = articleService.channelList();
		request.setAttribute(Cms.CHANNEL, list);
		request.setAttribute("article", article);
		request.setAttribute("content1",  HtmlUtils.htmlspecialchars(article.getContent()));
		return "/user/articles/update";
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	@ResponseBody
	public boolean update(HttpServletRequest request, Article article, 
			MultipartFile file
			) throws IOException {
		Article articl=articleService.getArticleId(article.getId());
		User user = (User)request.getSession().getAttribute(Cms.USER);
		System.out.println(articl.getUserId());
		System.out.println(user.getId());
		if(articl.getUserId()!=user.getId()){
			
			return false;
		}
		if(file.getOriginalFilename()!=null&&file.getOriginalFilename()!=""){
		String picUrl;
		try {
			// 处理上传文件
			picUrl = processFile(file);
			article.setPicture(picUrl);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		//当前用户是文章的作者
		User loginUser = (User)request.getSession().getAttribute(Cms.USER);
		article.setUserId(loginUser.getId());
		
		
		return articleService.updateArticle(article)>0;
		
		
		
	}
	/**
	 * 批量上传文件
	 * @param request
	 * @param imgFiles
	 * @return
	 * @throws FileUploadException
	 */
	@RequestMapping("uploads.do")
	@ResponseBody
	public String uploads(HttpServletRequest request ,@RequestParam(value = "imgFile") MultipartFile  imgFiles[]) throws FileUploadException {
	
		log.info("开始上传文件啊");
	//文件保存目录路径  todo
	//String savePath = pageContext.getServletContext().getRealPath("/") + "pic/";
	
		StringBuilder sb = new StringBuilder();
	//文件保存目录URL
	String saveUrl  = request.getContextPath() + "/pic/";
	//定义允许上传的文件扩展名
	HashMap<String, String> extMap = new HashMap<String, String>();
	extMap.put("image", "gif,jpg,jpeg,png,bmp");
	extMap.put("flash", "swf,flv");
	extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
	extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

	//最大文件大小
	long maxSize = 1000000;

	//response.setContentType("text/html; charset=UTF-8");

	if(!ServletFileUpload.isMultipartContent(request)){
		log.info(getError("请选择文件。"));
		return sb.toString();
	}
	//检查目录
	File uploadDir = new File(picRootPath);

	if(!uploadDir.isDirectory()){
		log.info(getError("上传目录不存在。"));
		return sb.toString();
	}
	//检查目录写权限
	if(!uploadDir.canWrite()){
		log.info(getError("上传目录没有写权限。"));
		return sb.toString();
	}

	String dirName = request.getParameter("dir");
	if (dirName == null) {
		dirName = "image";
	}
	if(!extMap.containsKey(dirName)){
		log.info(getError("目录名不正确。"));
		return sb.toString();
	}
	//创建文件夹
	String savePath =picRootPath + "/" +  dirName + "/";
	saveUrl += dirName + "/";
	File saveDirFile = new File(savePath);
	if (!saveDirFile.exists()) {
		saveDirFile.mkdirs();
	}
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	String ymd = sdf.format(new Date());
	savePath += ymd + "/";
	saveUrl += ymd + "/";
	File dirFile = new File(savePath);
	log.info("1");
	if (!dirFile.exists()) {
		dirFile.mkdirs();
	}

	FileItemFactory factory = new DiskFileItemFactory();
	//ServletFileUpload upload = new ServletFileUpload(factory);
	//upload.setHeaderEncoding("UTF-8");
	
	//List items = upload.parseRequest(request);
	
	List<FileResult> fileList =  new ArrayList();
	
	//Iterator itr = imgFiles.iterator();
	log.info("2");
	
		
		log.info("循环");
		//FileItem item = (FileItem) itr.next();
		for (int i = 0; i < imgFiles.length; i++) {
			MultipartFile imgFile = imgFiles[i]; 
		
		String fileName = imgFile.getOriginalFilename();
		long fileSize = imgFile.getSize();
		
			//检查文件大小
			if(imgFile.getSize() > maxSize){
				log.info(getError("上传文件大小超过限制。"));
				return sb.toString();
			}
			//检查扩展名
			log.info("fileName is " + fileName);
			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
				log.info(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
				return sb.toString();			}

			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
			try{
				log.info("savePath, newFileName :" + savePath + " -- "+ newFileName);
				File uploadedFile = new File(savePath, newFileName);
				//item.write(uploadedFile);
				imgFile.transferTo(uploadedFile);
			}catch(Exception e){
				log.info(getError("上传文件失败。"));
				return sb.toString();
			}

			//return new FileResult(0,saveUrl + newFileName);
			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			obj.put("url", saveUrl + newFileName);
			sb.append(obj.toJSONString());
		}
		return sb.toString();
	
}
	
	private String getError(String message) {
		log.info("error " + message	);
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}
	
	
}







class NameComparator implements Comparator {
	public int compare(Object a, Object b) {
		Hashtable hashA = (Hashtable) a;
		Hashtable hashB = (Hashtable) b;
		if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
			return -1;
		} else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
			return 1;
		} else {
			return ((String) hashA.get("filename")).compareTo((String) hashB.get("filename"));
		}
	}
}
 
 
 

 class SizeComparator implements Comparator {
	public SizeComparator() {
		// TODO Auto-generated constructor stub
	}

	public int compare(Object a, Object b) {
		Hashtable hashA = (Hashtable) a;
		Hashtable hashB = (Hashtable) b;
		if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
			return -1;
		} else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
			return 1;
		} else {
			if (((Long) hashA.get("filesize")) > ((Long) hashB.get("filesize"))) {
				return 1;
			} else if (((Long) hashA.get("filesize")) < ((Long) hashB.get("filesize"))) {
				return -1;
			} else {
				return 0;
			}
		}
	}
}

 class TypeComparator implements Comparator {
	public int compare(Object a, Object b) {
		Hashtable hashA = (Hashtable) a;
		Hashtable hashB = (Hashtable) b;
		if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
			return -1;
		} else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
			return 1;
		} else {
			return ((String) hashA.get("filetype")).compareTo((String) hashB.get("filetype"));
		}
	}
}

	

