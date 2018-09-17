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
import db.UserDBBean;

@Controller
public class AdmListHandler {
	@Resource
	private TripDBBean tripDao;
	@Resource
	private AlbumDBBean albumDao;
	@Resource
	private CmtDBBean cmtDao;
	@Resource
	private UserDBBean userDao;
	
	@RequestMapping("/adm/*")
	public ModelAndView admDefaultProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("adm/default");
	}
	@RequestMapping("/adm/main")
	public ModelAndView admMainListProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("adm/main");
	}
	@RequestMapping("/adm/user")
	public ModelAndView admUserProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("adm/user");
	}
	@RequestMapping("/adm/trip")
	public ModelAndView admTripProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("adm/trip");
	}
	@RequestMapping("/adm/album")
	public ModelAndView admAlbumProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("adm/album");
	}
	@RequestMapping("/adm/cmt")
	public ModelAndView admCmtProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("adm/cmt");
	}
}
