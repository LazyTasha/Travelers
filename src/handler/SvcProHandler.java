package handler;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.SessionScope;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import db.AlbumDBBean;
import db.AlbumDataBean;
import db.CmtDBBean;
import db.CmtDataBean;
import db.LocDBBean;
import db.TagDBBean;
import db.TbDBBean;
import db.TbDataBean;
import db.TripDBBean;
import db.UserDBBean;
import db.UserDataBean;

@Controller
public class SvcProHandler {
	private static final int ADMIN = 9;
	private static final int EXTENSION_ERROR = -1;
	private static final int SIZE_ERROR = -2;
	private static final int SUCCESS = 1;
	private static final int LIMIT_SIZE = 5 * 1024 * 1024;// image max size=5M;

	@Resource
	private TripDBBean tripDao;
	@Resource
	private AlbumDBBean albumDao;
	@Resource
	private CmtDBBean cmtDao;
	@Resource
	private LocDBBean locDao;
	@Resource
	private TagDBBean tagDao;
	@Resource
	private UserDBBean userDao;
	@Resource
	private TbDBBean tbDao;

	///////////////////////////////// user pages/////////////////////////////////

	@RequestMapping("/regPro")
	public ModelAndView UserInputProcess(HttpServletRequest request, HttpServletResponse response)
			throws HandlerException {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		UserDataBean userDto = new UserDataBean();
		userDto.setUser_id(request.getParameter("user_id"));
		userDto.setPasswd(request.getParameter("passwd"));
		userDto.setUser_name(request.getParameter("user_name"));
		userDto.setEmail(request.getParameter("email1"));
		// user_level

		// gender
		int gender = Integer.parseInt(request.getParameter("gender"));

		userDto.setGender(gender);
		userDto.setReg_date(new Timestamp(System.currentTimeMillis()));

		int result = userDao.insertUser(userDto);

		request.setAttribute("result", result);
		request.setAttribute("userDto", userDto);
		request.setAttribute("user_id", userDto.getUser_id());

		return new ModelAndView("svc/regPro");
	}

	@RequestMapping("/userModPro")
	public ModelAndView UserModifyprocess(HttpServletRequest request, HttpServletResponse response)
			throws HandlerException {

		UserDataBean userDto = new UserDataBean();
		userDto.setPasswd(request.getParameter("passwd"));
		userDto.setUser_name(request.getParameter("user_name"));

		userDto.setUser_id((String) request.getSession().getAttribute("user_id"));

		int result = userDao.modifyUser(userDto);
		request.setAttribute("result", result);

		return new ModelAndView("svc/userModPro");
	}

	@RequestMapping("/loginPro")
	public ModelAndView Loginprocess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		int userType = 0;
		String id = request.getParameter("user_id");
		String passwd = request.getParameter("passwd");
		UserDataBean userDto = userDao.getUser(id);

		int result = userDao.check(id, passwd);
		request.setAttribute("result", result);
		request.setAttribute("id", id);

		if (result == 1) {
			int user_level = userDao.getUserLevel(id);
			if (user_level == ADMIN) {
				userType = 1;
				request.setAttribute("user_level", user_level);
			}
			request.setAttribute("userType", userType);
		}
		if (result != -1) {
			request.setAttribute("userDto", userDto);
		}

		return new ModelAndView("svc/loginPro");
	}

	@RequestMapping("/logout") // logout �엫
	public ModelAndView LogoutProcess(HttpServletRequest request, HttpServletResponse response)
			throws HandlerException {
		request.getSession().removeAttribute("user_id");
		// send user to main page
		// but we don't have a main page yet, so send him to board list, temporary
		return new ModelAndView("svc/login");
	}

	@RequestMapping("/userLeavePro")
	public ModelAndView DeleteProcess(HttpServletRequest request, HttpServletResponse response)
			throws HandlerException {
		String id = (String) request.getSession().getAttribute("user_id");
		String passwd = request.getParameter("passwd");
		int resultCheck = userDao.check(id, passwd);
		request.setAttribute("resultCheck", resultCheck);

		if (resultCheck == 1) {
			int result = userDao.deleteUser(id);
			request.setAttribute("result", result);
		}

		return new ModelAndView("svc/userLeavePro");
	}
	
	////Email
	@RequestMapping("/emailCheck")
	public ModelAndView EmailCheckProcess(HttpServletRequest request, String email1) {
		String to1=email1; // 인증위해 사용자가 입력한 이메일주소
		String host="smtp.gmail.com"; // smtp 서버
		String subject="EmailCheck"; // 보내는 제목 설정
		String fromName="Admin"; // 보내는 이름 설정
		String from="dlagurgur@gmail.com"; // 보내는 사람(구글계정)
		String authNum=SvcProHandler.authNum(); // 인증번호 위한 난수 발생부분
		String content="Number ["+authNum+"]"; // 이메일 내용 설정
		
		String email = request.getParameter("email1");
		int result = userDao.EmailCheck( email );
		
		request.setAttribute("authNum", authNum);
		request.setAttribute("email", email);
		request.setAttribute("result",result);
		
		try{
		Properties props=new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", host);
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.port","465");
		props.put("mail.smtp.user",from);
		props.put("mail.smtp.auth","true");
		
		Session mailSession 
           = Session.getInstance(props,new javax.mail.Authenticator(){
			    protected PasswordAuthentication getPasswordAuthentication(){
				    return new PasswordAuthentication("dlagurgur@gmail.com","asd75311");
			}
		});
		
		Message msg = new MimeMessage(mailSession);
		InternetAddress []address = {new InternetAddress(to1)};
		msg.setFrom(new InternetAddress
                      (from, MimeUtility.encodeText(fromName,"utf-8","B")));
		msg.setRecipients(Message.RecipientType.TO, address);
		msg.setSubject(subject);
		msg.setSentDate(new java.util.Date());
		msg.setContent(content,"text/html; charset=utf-8");
		
		Transport.send(msg);
		}catch(MessagingException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return new ModelAndView("svc/emailCheck");
	}
	
	public static String authNum(){	//난수발생부분
		StringBuffer buffer=new StringBuffer();
		for(int i=0;i<=4;i++){
			int num=(int)(Math.random()*9+1);
			buffer.append(num);
		}
		return buffer.toString();
	}

	///////////////////////////////// board pages/////////////////////////////////

	@RequestMapping("/tripWritePro")
	public ModelAndView svcTripWriteProProcess(HttpServletRequest request, HttpServletResponse response)
			throws HandlerException {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//gg_coordinata&location
		double lat=Double.parseDouble(request.getParameter("lat"));
		double lng=Double.parseDouble(request.getParameter("lng"));
		
		String country_name=request.getParameter("country_name");//한글로 받아지는 상태

		
		// tbDto will be used for DB update
		// #{user_id}, #{tb_title}, #{tb_content}, sysdate, 0,
		// #{tb_m_num}, 0, #{tb_talk}
		TbDataBean tbDto = new TbDataBean();

		tbDto.setUser_id(userDao.getUserId(request.getParameter("user_name")));
		tbDto.setTb_title(request.getParameter("trip_title"));
		tbDto.setTb_content(request.getParameter("content"));
		tbDto.setTb_m_num(Integer.parseInt(request.getParameter("trip_m_num")));
		tbDto.setTb_talk(request.getParameter("tb_talk"));
		tbDto.setLocs(request.getParameterValues("trip_location"));
		tbDto.setTags(request.getParameterValues("trip_tags"));

		tbDao.writeTb(tbDto);

		return new ModelAndView("svc/tripWritePro");
	}

	@RequestMapping("/tripModPro")
	public ModelAndView svcTrpModProProcess(HttpServletRequest request, HttpServletResponse response)
			throws HandlerException {
		return new ModelAndView("svc/tripModPro");
	}

	// if fail to delete, we should show user an alert
	// so we need this result parameter
	@RequestMapping("/tripDelPro")
	public ModelAndView svcTripDelProProcess(HttpServletRequest request, HttpServletResponse response)
			throws HandlerException {
		int tb_no = Integer.parseInt(request.getParameter("tb_no"));
		int result = tbDao.deleteTrip(tb_no);
		request.setAttribute("result", result);
		return new ModelAndView("svc/tripDel");
	}

	///////////////////////////////// album pages/////////////////////////////////

	@RequestMapping("/boardAlbumPro")
	public ModelAndView svcAlbumProProcess(HttpServletRequest request, MultipartHttpServletRequest mtrequest)
			throws HandlerException {
		int tb_no = Integer.parseInt(request.getParameter("tb_no"));
		request.setAttribute("tb_no", tb_no);

		String uploadPath = request.getServletContext().getRealPath("/");
		System.out.println(uploadPath);
		String path = uploadPath + "save/";
		String DBpath = request.getContextPath() + "/save/";

		new File(path).mkdir();

		List<MultipartFile> fileList = mtrequest.getFiles("files");
		// 모든 파일 선택 가능->추후 사진 파일만 선택 할 필요 있음
		AlbumDataBean albumDto;

		int fileResult = SUCCESS;// 파일 입출력 결과
		long imgSize;// 이미지 파일 크기
		for (MultipartFile mf : fileList) {
			String originFileName = mf.getOriginalFilename(); // 원본 파일 명

			if (!isValidExtension(originFileName)) {
				fileResult = EXTENSION_ERROR;
				break;
			}

			imgSize = mf.getSize();
			if (imgSize >= LIMIT_SIZE) {
				fileResult = SIZE_ERROR;
				break;
			}
			originFileName = System.currentTimeMillis() + getRandomString()
					+ originFileName.substring(originFileName.lastIndexOf(".")).toLowerCase();
			String safeFile = path + originFileName;
			String safeDBFile = DBpath + originFileName;
			try {
				mf.transferTo(new File(safeFile));
				// db insert
				albumDto = new AlbumDataBean();
				albumDto.setPhoto_url(safeDBFile);
				albumDto.setTb_no(tb_no);
				int result = albumDao.addPhoto(albumDto);

			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("fileResult", fileResult);
		return new ModelAndView("/svc/boardAlbumPro");
	}

	@RequestMapping("/downloadAlbum.go")
	public void downloadAlbumProcess(HttpServletRequest request, HttpServletResponse response)
			throws HandlerException, IOException {
		int tb_no = Integer.parseInt(request.getParameter("tb_no"));
		List<String> photo_urls = albumDao.getPhoto_urls(tb_no);

		String realFolder = request.getServletContext().getRealPath("/") + "save/";
		int bufferSize = LIMIT_SIZE;
		ZipOutputStream zos = null;
		String zipName = "Travelers_Album" + tb_no;

		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=" + zipName + ".zip" + ";");

		response.setHeader("Content-Transfer-Encoding", "binary");
		OutputStream os = response.getOutputStream();
		zos = new ZipOutputStream(os); // ZipOutputStream
		zos.setLevel(8); // 압축 레벨 - 최대 압축률은 9, 디폴트 8
		BufferedInputStream bis = null;

		for (String photo_url : photo_urls) {
			String path[] = photo_url.split("/");
			String fileName = path[path.length - 1];
			String filePath = realFolder + fileName;

			File sourceFile = new File(filePath);

			bis = new BufferedInputStream(new FileInputStream(sourceFile));
			ZipEntry zentry = new ZipEntry(fileName);
			zentry.setTime(sourceFile.lastModified());
			zos.putNextEntry(zentry);

			byte[] buffer = new byte[bufferSize];

			int cnt = 0;
			while ((cnt = bis.read(buffer, 0, bufferSize)) != -1) {
				zos.write(buffer, 0, cnt);
			}
			zos.closeEntry();
		}
		zos.close();
		bis.close();
	}

	@RequestMapping("/download.go")
	public void downloadProcess(HttpServletRequest request, HttpServletResponse response)
			throws HandlerException, IOException {
		int n = Integer.parseInt(request.getParameter("num"));
		response.reset();
		if (n == 1) {
			downloadImgProcess(request, response);
		} else {
			String realFolder = request.getServletContext().getRealPath("/") + "save/";
			int bufferSize = LIMIT_SIZE;
			ZipOutputStream zos = null;
			String zipName = "Travelers_Photos";
			response.setHeader("Content-Disposition", "attachment; filename=" + zipName + ".zip" + ";");

			response.setHeader("Content-Transfer-Encoding", "binary");
			OutputStream os = response.getOutputStream();
			zos = new ZipOutputStream(os); // ZipOutputStream
			zos.setLevel(8); // 압축 레벨 - 최대 압축률은 9, 디폴트 8
			BufferedInputStream bis = null;
			for (int i = 0; i < n; i++) {
				String path[] = request.getParameter("photo" + i).split("/");
				String fileName = path[path.length - 1];
				String filePath = realFolder + fileName;

				File sourceFile = new File(filePath);

				bis = new BufferedInputStream(new FileInputStream(sourceFile));
				ZipEntry zentry = new ZipEntry(fileName);
				zentry.setTime(sourceFile.lastModified());
				zos.putNextEntry(zentry);

				byte[] buffer = new byte[bufferSize];

				int cnt = 0;

				while ((cnt = bis.read(buffer, 0, bufferSize)) != -1) {
					zos.write(buffer, 0, cnt);
				}
				zos.closeEntry();
			}
			zos.close();
			bis.close();
		}
	}

	// download할 img가 한개인 경우-download one image
	public void downloadImgProcess(HttpServletRequest request, HttpServletResponse response)
			throws HandlerException, IOException {
		String realFolder = request.getServletContext().getRealPath("/") + "save/";
		String path[] = request.getParameter("photo0").split("/");
		String fileName = path[path.length - 1];
		String filePath = realFolder + fileName;

		byte fileByte[] = FileUtils.readFileToByteArray(new File(filePath));

		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.getOutputStream().write(fileByte);

		response.getOutputStream().flush();
		response.getOutputStream().close();
	}

	///////////////////////////////// ajax list/////////////////////////////////
	
	@RequestMapping(value = "/idCheck.go", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<Object, Object> idCheck(@RequestBody String user_id) {
		user_id = user_id.split("=")[0];
		int countId = 0;
		Map<Object, Object> map = new HashMap<Object, Object>();

		countId = userDao.idCheck(user_id);
		map.put("countId", countId);

		return map;
	}

	@RequestMapping(value = "/nameCheck.go", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<Object, Object> nameCheck(@RequestBody String user_name) {
		user_name = user_name.split("=")[0];
		int countName = 0;
		Map<Object, Object> map = new HashMap<Object, Object>();

		countName = userDao.nameCheck(user_name);
		map.put("countName", countName);

		return map;
	}

	@RequestMapping(value = "/commentInsert.go", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public void commentInsertProcess(HttpServletRequest request, HttpSession session) throws HandlerException {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String user_id = (String)session.getAttribute("user_id");
		CmtDataBean cmtDto = new CmtDataBean();

		cmtDto.setUser_id(user_id); // jsp에서 히든으로 가져오면됨
		cmtDto.setTb_no(Integer.parseInt(request.getParameter("tb_no")));
		cmtDto.setC_content(request.getParameter("c_content"));

		cmtDao.insertComment(cmtDto);
	}

	@RequestMapping(value = "/commentSelect.go", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<CmtDataBean> commentSelectProcess(HttpServletRequest request, HttpServletResponse response)
			throws HandlerException {
		int tb_no = Integer.parseInt(request.getParameter("tb_no"));
		List<CmtDataBean>comment= cmtDao.getComment(tb_no);
		request.setAttribute("comment", comment);
		// don't set the name of variable like this!
		return comment;
	}

	@RequestMapping(value = "/commentUpdate.go", method = RequestMethod.POST, produces = "application/json") // 댓글 수정
	@ResponseBody
	private void commentUpdateProcess(HttpServletRequest request, HttpServletResponse response)
			throws HandlerException {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		CmtDataBean cmtDto = new CmtDataBean();
		cmtDto.setC_id(Integer.parseInt(request.getParameter("c_id")));
		cmtDto.setC_content(request.getParameter("c_content"));
		cmtDao.updateComment(cmtDto);
	}

	@RequestMapping(value = "/commentDelete.go", method = RequestMethod.POST) // 댓글 삭제
	@ResponseBody
	private void commentDeleteProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int c_id = Integer.parseInt(request.getParameter("c_id"));
		cmtDao.deleteComment(c_id);
	}

	///////////////////////////////// etc/////////////////////////////////
	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	private boolean isValidExtension(String originFileName) {
		String fileExtension = originFileName.substring(originFileName.lastIndexOf(".") + 1).toLowerCase();
		switch (fileExtension) {
		case "jpg":
		case "png":
		case "gif":
			return true;
		}
		return false;
	}

}