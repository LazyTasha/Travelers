package handler;

import java.util.List;
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
import db.TagDataBean;
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
		return new ModelAndView( "svc/userModPassCheck" );
	}
	
	@RequestMapping( "/userModify" )
	public ModelAndView userModifyProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		String user_id=(String)request.getSession().getAttribute("user_id");
		String passwd=request.getParameter("passwd");
		
		int result=0;
		UserDataBean userDto=userDao.getUser(user_id);
		
		if(userDto!=null) {
			if(passwd.equals(userDto.getPasswd())) {
				result=1;
				request.setAttribute("userDto", userDto);
				List<TagDataBean> tagList=tagDao.getStyleTags();
				request.setAttribute("tagList", tagList);
				List<TagDataBean> userTags=tagDao.getUserTags(user_id);
				request.setAttribute("userTags", userTags);
			} else {
				result=0;
			}
		} else {
			result=-1;
		}
		
		request.setAttribute("result", result);
		
		return new ModelAndView( "svc/userModify" );
	}
	
	@RequestMapping( "/userLeave" )
	public ModelAndView DeleteProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView( "svc/userLeave" );
	}
	
	/////////////////////////////////board pages/////////////////////////////////
	
	@RequestMapping("/tripWrite")
	public ModelAndView svcTripWriteFormProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		//Need to know the writer: Bring user_id from session & user_name(nickname)
		//String user_id=(String)request.getSession().getAttribute("user_id");//나중에 주석 지우기
		String user_id="aaa";
		String user_name= userDao.getUserName(user_id);//user_id �޾Ƽ� db�� �ִ� name ���� �ҷ�����
		
		List<TagDataBean> styleTags=tagDao.getStyleTags();
		List<TagDataBean> cityTags=tagDao.getCityTags();
		List<TagDataBean> countryTags=tagDao.getCountryTags();
		//send them to set User Name on the form
		request.setAttribute("user_id", user_id); 
		request.setAttribute("user_name", user_name); 
		request.setAttribute("tags", styleTags); 
		request.setAttribute("tags", cityTags); 
		request.setAttribute("tags", countryTags); 
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
