package handler;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import db.AlbumDBBean;
import db.CmtDBBean;
import db.LocDBBean;
import db.TagDBBean;
import db.TripDBBean;
import db.UserDBBean;
import db.UserDataBean;

@Controller
public class SvcProHandler {
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
	
	@RequestMapping("/svc/loginPro")
	public ModelAndView svcLoginProProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("svc/loginPro");
	}
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
	@RequestMapping( "/memberinputPro" )
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
		String email = null;
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
		UserDto.setEmail( email );
 		// reg_date 
		UserDto.setReg_date( new Timestamp( System.currentTimeMillis() ) );
		
		int result = userDao.insertMember( UserDto );
		
		request.setAttribute( "result", result );
		
		return new ModelAndView( "svc/inputPro" );
	}
	
	@RequestMapping( "/membermodifyPro" )
	public ModelAndView UserModifyprocess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		
		UserDataBean memberDto = new UserDataBean();
		memberDto.setPasswd( request.getParameter( "passwd" ) );
		memberDto.setUser_name( request.getParameter( "user_name" ) );
	
		memberDto.setUser_id( (String) request.getSession().getAttribute( "memid" ) ); 
	
		int result = userDao.modifyMember( memberDto );
 		request.setAttribute( "result", result );	
		
		return new ModelAndView( "svc/modifyPro" );
	}
	
	@RequestMapping( "/memberloginPro" )
	public ModelAndView Loginprocess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
 		String id = request.getParameter( "user_id" );
		String passwd = request.getParameter( "passwd" );
		
		int result = userDao.check( id, passwd );
 		request.setAttribute( "result", result );
		request.setAttribute( "id", id );
		
		return new ModelAndView( "svc/loginPro" );
	}
	
	@RequestMapping( "/memberlogout" )	//logout �엫
	public ModelAndView LogoutProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		request.getSession().removeAttribute( "memid" );		
		return new ModelAndView( "svc/main" );
	}	
		
	//以묐났�솗�씤
	 @RequestMapping(value="/idcheck.go",produces = "application/json")
	 @ResponseBody
	 public Map<Object, Object> idcheck(@RequestBody String user_id) {
		 	user_id = user_id.split("=")[0];
	        int count = 0;
	        Map<Object, Object> map = new HashMap<Object, Object>();
	 
	        count = userDao.idcheck( user_id );
	        map.put("cnt", count);
	        
	        return map;
	    }
	 
	 @RequestMapping(value="/namecheck.go",method = RequestMethod.POST,produces = "application/json")
	 @ResponseBody
	 public Map<Object, Object> namecheck(@RequestBody String user_name) {
		 	user_name = user_name.split("=")[0];
	        int countt = 0;
	        Map<Object, Object> map = new HashMap<Object, Object>();
	 
	        countt = userDao.namecheck( user_name );
	        map.put("cnt", countt);
	        
	        return map;
	    }
}