package handler;

import java.util.List;

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
import db.TripDataBean;
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
	@RequestMapping("/*")
	public ModelAndView svcDefaultProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("svc/default");
	}
	@RequestMapping("/main")
	public ModelAndView svcMainProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("svc/main");
	}
	@RequestMapping("/list")
	public ModelAndView svcListProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		UserDataBean userDto=(UserDataBean)request.getAttribute("userDto");
		List<TbDataBean> tripList=tbDao.getTripList();
		int count=tbDao.getCount();
		request.setAttribute("userDto", userDto);
		request.setAttribute("tripList", tripList);
		request.setAttribute("count", count);
		return new ModelAndView("svc/list");
	}
	@RequestMapping("/album")
	public ModelAndView svcAlbumProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		String tb_no=request.getParameter("tb_no");
		tb_no="1";
		request.setAttribute("tb_no", tb_no);
		return new ModelAndView("svc/album");
	}
	@RequestMapping("/reg")
	public ModelAndView svcRegProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("svc/reg");
	}
	@RequestMapping("/myPage")
	public ModelAndView svcMyPageProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		UserDataBean userDto=(UserDataBean)request.getAttribute("userDto");
		List<String> userTagList=tagDao.getUserTags(userDto.getUser_id());
		String[] userTags=new String[userTagList.size()];
		for(int i=0; i<userTags.length; i++) {
			userTags[i]=userTagList.get(i);
		}
		request.setAttribute("userDto", userDto);
		request.setAttribute("userTags", userTags);
		return new ModelAndView("svc/myPage");
	}
	@RequestMapping("/myTrip")
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
