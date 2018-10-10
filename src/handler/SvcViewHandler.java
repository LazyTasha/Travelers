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
	private static final int PHOTOSIZE=6;//�븳 �솕硫댁뿉 異쒕젰�릺�뒗 �궗吏� 媛쒖닔
	
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
	
	/////////////////////////////////default pages/////////////////////////////////
	
	@RequestMapping("/*")
	public ModelAndView svcDefaultProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("svc/default");
	}
	
	/////////////////////////////////main page/////////////////////////////////
	
	//temporary go to login
	@RequestMapping("/main")
	public ModelAndView svcMainProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("svc/login");
	}
	
	/////////////////////////////////user pages/////////////////////////////////
	
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
	
	/////////////////////////////////board pages/////////////////////////////////
	
	@RequestMapping("/tripList")
	public ModelAndView svcListProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		UserDataBean userDto=(UserDataBean)request.getAttribute("userDto");
		List<TbDataBean> tripList=tbDao.getTripList();
		int count=tbDao.getCount();
		request.setAttribute("userDto", userDto);
		request.setAttribute("tripList", tripList);
		request.setAttribute("count", count);
		return new ModelAndView("svc/tripList");
	}
	
	@RequestMapping("/trip")
	public ModelAndView svcTripProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		//get tb_no of the post
		int tb_no=Integer.parseInt(request.getParameter("tb_no"));
		request.setAttribute("tb_no", tb_no);
		//get the content of the post
		//TripDataBean has a part of contents of board
		//TbDataBean has every content of board
		TbDataBean tbDto=tbDao.getTb(tb_no);
		request.setAttribute("tbDto", tbDto);
		
		//determine tab
		String tab=request.getParameter("tab");
		if(tab==null)tab=MAP;
		request.setAttribute("tab", tab);
		
		//map data
		//test�슜
		double lat=37.554690;
		double lng=126.970702;
		//
		request.setAttribute("lat",lat);
		request.setAttribute("lng", lng);
		
		//board album data	
		String start=request.getParameter("start");
		if(start==null)start="1";
		request.setAttribute("start",start);
		
		//where are the comments?

		return new ModelAndView("svc/trip");
	}
	
	/////////////////////////////////album pages/////////////////////////////////
	
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
			int last=(count%PHOTOSIZE==0?count-PHOTOSIZE:(count/PHOTOSIZE)*PHOTOSIZE);//+(count%PHOTOSIZE==0?0:1));

			request.setAttribute("start",start);
			request.setAttribute("size", PHOTOSIZE);
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
	
	/////////////////////////////////ajax method list/////////////////////////////////
	@RequestMapping(value="/loadMoreList", produces = "application/json")
	@ResponseBody
	public List<TbDataBean> loadMoreList(int last_tb_no) {
		//get more 10 trip articles when 'load more' button is pressed
		List<TbDataBean> additionalList=tbDao.loadMoreList(last_tb_no);
		return additionalList;
	}
}
