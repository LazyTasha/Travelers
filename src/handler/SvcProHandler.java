package handler;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	
	/////////////////로그인
	@RequestMapping( "/member/inputPro" )
	public ModelAndView UserInputProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		try {
			request.setCharacterEncoding( "utf-8" );
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		UserDataBean UserDto = new UserDataBean();
		UserDto.setId( request.getParameter( "id" ) );
		UserDto.setPasswd( request.getParameter( "passwd" ) );
		UserDto.setN_name( request.getParameter( "n_name" ) );
	
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
				// 직접입력
				email = email1;
			} else {
				// 선택입력
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
	
	@RequestMapping( "/member/modifyPro" )
	public ModelAndView UserModifyprocess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		
		UserDataBean memberDto = new UserDataBean();
		memberDto.setPasswd( request.getParameter( "passwd" ) );
		memberDto.setN_name( request.getParameter( "n_name" ) );
		
	

		// 이메일
		/*String email = null;
		String email1 = request.getParameter( "email1" );
		String email2 = request.getParameter( "email2" );
		if( ! email1.equals( "" ) && ! email2.equals( "" ) ) {
			email = email1 + "@" + email2;
		}
		memberDto.setEmail( email );*/
	
		memberDto.setId( (String) request.getSession().getAttribute( "memid" ) ); 
	
		int result = userDao.modifyMember( memberDto );

		request.setAttribute( "result", result );	
		
		return new ModelAndView( "svc/modifyPro" );
	}
	
	@RequestMapping( "/member/loginPro" )
	public ModelAndView Loginprocess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {

		String id = request.getParameter( "id" );
		String passwd = request.getParameter( "passwd" );
		
		int result = userDao.check( id, passwd );

		request.setAttribute( "result", result );
		request.setAttribute( "id", id );
		
		return new ModelAndView( "svc/loginPro" );
	}
	
	@RequestMapping( "/member/deletePro" )
	public ModelAndView DeleteProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {

		String id = (String) request.getSession().getAttribute( "memid" );
		String passwd = request.getParameter( "passwd" );

		int resultCheck = userDao.check( id, passwd );

		request.setAttribute( "resultCheck", resultCheck );
		
		if( resultCheck == 1 ) {
			int result = userDao.deleteMember( id );
			request.setAttribute( "result", result );
		}
		
		return new ModelAndView( "svc/deletePro" );
	}
	
	@RequestMapping( "/member/logout" )	//logout 임
	public ModelAndView LogoutProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		request.getSession().removeAttribute( "memid" );		
		return new ModelAndView( "svc/main" );
	}	
	////////////로그인
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
}
