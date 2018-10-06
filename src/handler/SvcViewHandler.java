package handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.SessionScope;
import org.springframework.web.servlet.ModelAndView;

import db.AlbumDBBean;
import db.AlbumDataBean;
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
	private static final int PHOTOSIZE=5;//한 화면에 출력되는 사진 개수
	
	private static final String MAP="0";
	
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
	@RequestMapping(value="/loadMoreList", produces = "application/json")
	@ResponseBody
	public List<TbDataBean> loadMoreList(int last_tb_no) {
		List<TbDataBean> additionalList=tbDao.loadMoreList(last_tb_no);
		
		return additionalList;
	}
	
	@RequestMapping("/album")
	public ModelAndView svcAlbumProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {	
		int count=albumDao.getCount();
		request.setAttribute("count", count);
		if(count>0) {
			//select album
			List<AlbumDataBean>album=albumDao.getAlbum();
			request.setAttribute("album", album);			
		}
		return new ModelAndView("svc/album");
	}
	@RequestMapping("/svc/boardAlbum")//svc/boardAlbum
	public ModelAndView svcBoardAlbumProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		int tb_no=Integer.parseInt(request.getParameter("tb_no"));
		request.setAttribute("tb_no", tb_no);
		
		String user_id=(String) request.getSession().getAttribute( "memid" );
		if(user_id==null)user_id="";
		
		int count=albumDao.getBoardCount(tb_no);
		request.setAttribute("count", count);
		
		if(count>0) {
			//page
			int start=Integer.parseInt(request.getParameter("start"));
			int end=start+PHOTOSIZE-1;
			request.setAttribute("start", start);
			request.setAttribute("end", end);
			request.setAttribute("size", PHOTOSIZE);

			int last=(count/PHOTOSIZE)*PHOTOSIZE+1;
			request.setAttribute("last",last);

			//select board album
			Map<String, Integer>map=new HashMap<String,Integer>();
			map.put("start",start);
			map.put("end", end);
			map.put("tb_no", tb_no);
			List<AlbumDataBean>album=albumDao.getBoardAlbum(map);
			request.setAttribute("album", album);

			//check user whether user is member or not
			TbDataBean tbDto=new TbDataBean();
			tbDto.setUser_id(user_id);
			tbDto.setTb_no(tb_no);
			boolean isMember=tbDao.isMember(tbDto);
			request.setAttribute("isMember", isMember);
		}
		return new ModelAndView("svc/boardAlbum");
	}
	@RequestMapping("/reg")
	public ModelAndView svcRegProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("svc/reg");
	}
	@RequestMapping("/myPage")
	public ModelAndView svcMyPageProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		//I don't know why but it fails to get userDto, so here I try to get it.
		UserDataBean userDto=userDao.getUser((String)request.getSession().getAttribute("memid"));
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
//		int tb_no=Integer.parseInt(request.getParameter("tb_no"));
		int tb_no=1;//test용;
		//get the content of the post
		//TripDataBean has a part of contents of board
		//TbDataBean has every content of board
//		TbDataBean tbDto=tbDao.getTb(tb_no);
//		request.setAttribute("tbDto", tbDto);
		
		//determine tab
		String tab=request.getParameter("tab");
		if(tab==null)tab=MAP;

		request.setAttribute("tab", tab);
		
		//map data
		//test용
		double lat=37.554690;
		double lng=126.970702;
		//
		request.setAttribute("lat",lat);
		request.setAttribute("lng", lng);
		
		//board album data	
		String start=request.getParameter("start");
		if(start==null)start="1";
		request.setAttribute("tb_no", tb_no);
		request.setAttribute("start",start);

		return new ModelAndView("svc/tripView");
	}
}
