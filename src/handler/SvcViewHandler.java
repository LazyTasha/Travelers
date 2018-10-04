package handler;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import db.AlbumDBBean;
import db.AlbumDataBean;
import db.CmtDBBean;
import db.LocDBBean;
import db.TagDBBean;
import db.TbDBBean;
import db.TbDataBean;
import db.TripDBBean;
import db.UserDBBean;
import db.UserDataBean;

@Controller
public class SvcViewHandler {
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
	
	@RequestMapping( "/userMain" )
	public ModelAndView UserMainProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView( "svc/main" );
	}
	
	@RequestMapping( "/userModifyView" )
	public ModelAndView MemberProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		
		String id = (String) request.getSession().getAttribute( "memid" );
		String passwd = request.getParameter( "passwd" );
	
		int result = userDao.check( id, passwd );
	
		request.setAttribute( "result", result );
		
		if( result == 1 ) {
			UserDataBean UserDto = userDao.getUser( id );
			request.setAttribute( "UserDto", UserDto );
		}
		
		return new ModelAndView( "svc/modifyView" );
	}
	@RequestMapping("/svc/*")
	public ModelAndView svcDefaultProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("svc/default");
	}
	@RequestMapping("/svc/main")
	public ModelAndView svcMainProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("svc/main");
	}
	@RequestMapping("/svc/list")
	public ModelAndView svcListProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("svc/list");
	}
	@RequestMapping("/album")
	public ModelAndView svcAlbumProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		//int tb_no=Integer.parseInt(request.getParameter("tb_no"));
		int tb_no=1;
		request.setAttribute("tb_no", tb_no);
		
		String user_id=(String) request.getSession().getAttribute( "memid" );
		if(user_id==null)user_id="";
		
		int count=albumDao.getBoardCount(tb_no);
		request.setAttribute("count", count);
		if(count>0) {
			//select board album
			List<AlbumDataBean>album=albumDao.getBoardAlbum(tb_no);
			request.setAttribute("album", album);
			
			//check user whether user is member or not
			TbDataBean tbDto=new TbDataBean();
			tbDto.setUser_id(user_id);
			tbDto.setTb_no(tb_no);
			boolean isMember=tbDao.isMember(tbDto);
			request.setAttribute("isMember", isMember);
		}
		return new ModelAndView("svc/album");
	}
	@RequestMapping("/svc/reg")
	public ModelAndView svcRegProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("svc/reg");
	}
	@RequestMapping("/svc/myPage")
	public ModelAndView svcMyPageProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("svc/myPage");
	}
	@RequestMapping("/svc/myTrip")
	public ModelAndView SvcMyTripProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("svc/myTrip");
	}
	@RequestMapping("/trip")
	public ModelAndView svcTripProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		//get tb_no of the post
		int tb_no=Integer.parseInt(request.getParameter("tb_no"));
		//get the content of the post
		//TripDataBean has a part of contents of board
		//TbDataBean has every content of board
		TbDataBean tbDto=tbDao.getTb(tb_no);
		request.setAttribute("tbDto", tbDto);
		return new ModelAndView("svc/trip");
	}
}
