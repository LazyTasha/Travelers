package handler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import db.AlbumDBBean;
import db.CmtDBBean;
import db.TripDBBean;

@Controller
public class AdmListHandler {
	@Resource
	private TripDBBean tripDao;
	@Resource
	private AlbumDBBean albumDao;
	@Resource
	private CmtDBBean cmtDao;
	
	@RequestMapping("/adm/*")
	public ModelAndView admDefaultProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("adm/default");
	}
	@RequestMapping("/adminMain")
	public ModelAndView adminMainHandler(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
	
		String pageName=request.getParameter("pageName");
		if(pageName==null) {
			pageName="Trip";
		}
		pageName="admin"+pageName;
		request.setAttribute("pageName", pageName);
		return new ModelAndView("adm/main");
		
	}
	
	@RequestMapping("adm/adminTrip")
	public ModelAndView adminTripHandler(HttpServletRequest request, HttpServletResponse response) throws HandlerException {

		return new ModelAndView("adm/trip");
	}
	@RequestMapping("adm/adminComment")
	public ModelAndView adminContentHandler(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("adm/comment");
	}
	@RequestMapping("adm/adminUser")
	public ModelAndView adminUserHandler(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		//int count=userDao.getCount();
		
		//if(count>0) {
				//List<LogonDataBean>users=userDao.getUsers();
				//request.setAttribute("users", users);
		//}
		return new ModelAndView("adm/user");
	}
	@RequestMapping("adm/adminTag")
	public ModelAndView adminTagHandler(HttpServletRequest request, HttpServletResponse response) throws HandlerException {

		return new ModelAndView("adm/tag");
	}
	
	@RequestMapping("adm/adminAlbum")
	public ModelAndView adminPhotoHandler(HttpServletRequest request, HttpServletResponse response) throws HandlerException {

		return new ModelAndView("adm/album");
	}
	
}
