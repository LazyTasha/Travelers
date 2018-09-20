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
import db.TagDataBean;
import db.TripDBBean;
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
	
	@RequestMapping("/loginForm")
	public ModelAndView svcLoginFormProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("svc/loginForm");
	}
	@RequestMapping("/regForm")
	public ModelAndView svcRegFormProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("svc/regForm");
	}
	@RequestMapping("/tripWriteForm")
	public ModelAndView svcTripWriteFormProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		UserDataBean userDto=new UserDataBean();
		userDto.setUser_name("병아리");
		List<TagDataBean> tagList=tagDao.getTagList();
		request.setAttribute("userDto", userDto);
		request.setAttribute("tagList", tagList);
		return new ModelAndView("svc/tripWriteForm");
	}
	@RequestMapping("/tripModForm")
	public ModelAndView svcTripModFormProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("svc/tripModForm");
	}
}
