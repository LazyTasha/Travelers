package handler;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import db.AlbumDBBean;
import db.AlbumDataBean;
import db.CmtDBBean;
import db.LocDBBean;
import db.TagDBBean;
import db.TbDBBean;
import db.TripDBBean;
import db.UserDBBean;
import db.UserDataBean;

@Controller
public class SvcProHandler {  
	private static final int ADMIN=9;
	private static final int EXTENSION_ERROR=-1;
	private static final int SIZE_ERROR=-2;
	private static final int SUCCESS=1;
	private static final long LIMIT_SIZE = 5* 1024 * 1024;//image max size=5M;
	
	
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
	
	@RequestMapping("/svc/regPro")
	public ModelAndView svcRegProProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("svc/regPro");
	}
	@RequestMapping("/svc/tripWritePro")
	public ModelAndView svcTripWriteProProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("svc/tripWritePro");
	}
	@RequestMapping("/svc/tripModPro")
	public ModelAndView svcTrpModProProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("svc/tripModPro");
	}
	
	//if fail to delete, we should show user an alert
	//so we need this result parameter
	@RequestMapping("/svc/tripDelPro")
	public ModelAndView svcTripDelProProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		int tb_no=Integer.parseInt(request.getParameter("tb_no"));
		int result=tbDao.deleteTrip(tb_no);
		request.setAttribute("result", result);
		return new ModelAndView("svc/tripDel");
	}
		
	@RequestMapping( "/userInputPro" )
	public ModelAndView UserInputProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		try {
			request.setCharacterEncoding( "utf-8" );
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		UserDataBean UserDto = new UserDataBean();
		UserDto.setUser_id( request.getParameter( "user_id" ) );
		UserDto.setPasswd( request.getParameter( "passwd" ) );
		UserDto.setUser_name( request.getParameter( "user_name" ) );
		UserDto.setEmail(request.getParameter( "email1" ));
		//user_level
		
		//gender
		int gender1 = 0;
		int gender = Integer.parseInt(request.getParameter("gender"));
		
		if( gender == 1) {
			gender1 = 1;
		}else {
			gender1 = 2;
		}
		UserDto.setGender( gender1 );
		
		// email
		/*String email = null;
		String email1 = request.getParameter( "email1" );
		String email2 = request.getParameter( "email2" );
		if( ! email1.equals( "" ) ) {
			if( email2.equals( "0" ) ) {
				// 吏곸젒�엯�젰
				email = email1;
			} else {
				// �꽑�깮�엯�젰
				email = email1 + "@" + email2; 
			}
		}
		UserDto.setEmail( email );*/
 		// reg_date 
		UserDto.setReg_date( new Timestamp( System.currentTimeMillis() ) );
		
		int result = userDao.insertUser( UserDto );
		
		request.setAttribute( "result", result );
		
		return new ModelAndView( "svc/regPro" );
	}
	
	@RequestMapping( "/userModifyPro" )
	public ModelAndView UserModifyprocess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		
		UserDataBean memberDto = new UserDataBean();
		memberDto.setPasswd( request.getParameter( "passwd" ) );
		memberDto.setUser_name( request.getParameter( "user_name" ) );
	
		memberDto.setUser_id( (String) request.getSession().getAttribute( "memid" ) ); 
	
		int result = userDao.modifyUser( memberDto );
 		request.setAttribute( "result", result );	
		
		return new ModelAndView( "svc/modifyPro" );
	}
	
	@RequestMapping( "/userLoginPro" )
	public ModelAndView Loginprocess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
 		int userType=0;
		String id = request.getParameter( "user_id" );
		String passwd = request.getParameter( "passwd" );
		
		int result = userDao.check( id, passwd );
		System.out.println(result);
 		request.setAttribute( "result", result );
		request.setAttribute( "id", id );
		
		if(result==1) {
			int user_level=userDao.getUserLevel(id);
			if(user_level==ADMIN) {
				userType=1;
			}
			request.setAttribute("userType", userType);
		}
		return new ModelAndView( "svc/loginPro" );
	}
	
	@RequestMapping( "/userLogout" )	//logout �엫
	public ModelAndView LogoutProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		request.getSession().removeAttribute( "memid" );		
		return new ModelAndView( "svc/main" );
	}	
		
	//以묐났�솗�씤
	 @RequestMapping(value="/idCheck.go",produces = "application/json")
	 @ResponseBody
	 public Map<Object, Object> idCheck(@RequestBody String user_id) {
		 	user_id = user_id.split("=")[0];
	        int count = 0;
	        Map<Object, Object> map = new HashMap<Object, Object>();
	 
	        count = userDao.idCheck( user_id );
	        map.put("cnt", count);
	        
	        return map;
	    }
	 
	 @RequestMapping(value="/nameCheck.go",method = RequestMethod.POST,produces = "application/json")
	 @ResponseBody
	 public Map<Object, Object> nameCheck(@RequestBody String user_name) {
		 	user_name = user_name.split("=")[0];
	        int countt = 0;
	        Map<Object, Object> map = new HashMap<Object, Object>();
	 
	        countt = userDao.nameCheck( user_name );
	        map.put("cnt", countt);
	        
	        return map;
	    }
	 @RequestMapping("/albumPro")
		public ModelAndView svcAlbumProProcess(HttpServletRequest request, MultipartHttpServletRequest mtrequest) throws HandlerException {
		 	int tb_no=Integer.parseInt(request.getParameter("tb_no"));
		 	
			String uploadPath = request.getServletContext().getRealPath("/");
			System.out.println(uploadPath);
			String path=uploadPath+"save/";
			String DBpath=request.getContextPath()+"/save/";

			new File(path).mkdir();
			
			List<MultipartFile> fileList = mtrequest.getFiles("files");
			//모든 파일 선택 가능->추후 사진 파일만 선택 할 필요 있음
			AlbumDataBean albumDto;
			
			int fileResult=SUCCESS;//파일 입출력 결과
			long imgSize;//이미지 파일 크기
			for (MultipartFile mf : fileList) {
	            String originFileName = mf.getOriginalFilename(); // 원본 파일 명
	            
	            if(!isValidExtension(originFileName)) {
	            	fileResult=EXTENSION_ERROR;
	            	break;
	            }
	            
	            imgSize=mf.getSize();
	            if(imgSize>=LIMIT_SIZE) {
	            	fileResult=SIZE_ERROR;break;
	            }
	            String safeFile = path + System.currentTimeMillis() + originFileName;
	            String safeDBFile=DBpath+ System.currentTimeMillis() + originFileName;
	            try {
	                mf.transferTo(new File(safeFile));
	                //db insert
	                albumDto=new AlbumDataBean();
	            	albumDto.setPhoto_url(safeDBFile);
	            	albumDto.setTb_no(tb_no);
	            	int result=albumDao.addPhoto(albumDto);
	               
	            } catch (IllegalStateException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }
			request.setAttribute("fileResult",fileResult);
			return new ModelAndView("/svc/albumPro");
		}
		 private boolean isValidExtension(String originFileName) {
		        String fileExtension = originFileName.substring(originFileName.lastIndexOf(".") + 1).toLowerCase();
		        switch(fileExtension) {
			        case "jpg":
			        case "png":
			        case "gif":
		            return true;
		        }
		        return false;
		    }
}