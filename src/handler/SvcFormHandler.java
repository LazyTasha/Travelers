package handler;

import java.util.Map;

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
import db.TbDBBean;
import db.TbDataBean;
import db.TripDBBean;
import db.UserDBBean;
import db.UserDataBean;

@Controller
public class SvcFormHandler {
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
	private TbDBBean tbDao;
	@Resource
	private UserDBBean userDao;
	
	/////////////////////////////////user pages/////////////////////////////////
	
	@RequestMapping("/registration")
	public ModelAndView svcRegProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("svc/registration");
	}
	
	@RequestMapping( "/login" )
	public ModelAndView LoginProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView( "svc/login" );
	}
	
	@RequestMapping( "/userModPassCheck" )
	public ModelAndView userModPassCheckProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		String user_id = (String) request.getSession().getAttribute( "user_id" );
		String passwd = request.getParameter( "passwd" );
		
		int result = userDao.check( user_id, passwd );
		request.setAttribute( "result", result );
		
		if( result == 1 ) {
			UserDataBean userDto = userDao.getUser( user_id );
			request.setAttribute( "userDto", userDto );
		}
		
		return new ModelAndView( "svc/userModPassCheck" );
	}
	
	@RequestMapping( "/userModify" )
	public ModelAndView userModifyProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		String user_id=(String)request.getSession().getAttribute("user_id");
		UserDataBean userDto=userDao.getUser(user_id);
		request.setAttribute("userDto", userDto);
		
		return new ModelAndView( "svc/userModify" );
	}
	
	@RequestMapping( "/userLeave" )
	public ModelAndView DeleteProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView( "svc/userLeave" );
	}
	
	/////////////////////////////////board pages/////////////////////////////////
	
	@RequestMapping("/tripWrite")
	public ModelAndView svcTripWriteFormProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		//need to know who is writing
		String writer_id=((UserDataBean)request.getAttribute("userDto")).getUser_id();
		//String writer_id=(String) request.getSession().getAttribute("user_id");
		String writer_name=((UserDataBean)request.getAttribute("userDto")).getUser_name();
		//get tag list too so that user choose it
		//but I don't know why should I put a map there...
		Map<Integer, String> tags=tagDao.getStyleTags();
		//send them to set User Name on the form
		request.setAttribute("writer_id", writer_id);
		request.setAttribute("writer_name", writer_name);
		request.setAttribute("tags", tags);
		return new ModelAndView("svc/tripWrite");
	}
	
	@RequestMapping("/tripMod")
	public ModelAndView svcTripModFormProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		//get the origin, it will also bring its tags and locs
		int tb_no=Integer.parseInt(request.getParameter("tb_no"));
		TbDataBean tbDto=tbDao.getTb(tb_no);
		//set the origin to spread out
		request.setAttribute("tbDto", tbDto);
		return new ModelAndView("svc/tripMod");
	}
}
