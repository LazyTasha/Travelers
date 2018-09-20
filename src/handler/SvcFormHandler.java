package handler;

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
	
	////////////////로그인
	@RequestMapping( "/member/inputForm" )
	public ModelAndView UserInputProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView( "svc/inputForm" );
	}
	
	@RequestMapping( "/member/modifyForm" ) 
	public ModelAndView ModifyProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView( "svc/modifyForm" );
	}
	
	@RequestMapping( "/member/loginForm" )
	public ModelAndView LoginProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView( "svc/loginForm" );
	}
	
	@RequestMapping( "/member/deleteForm" )
	public ModelAndView DeleteProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView( "svc/deleteForm" );
	}
	/////////////////////////////////// 로그인
	@RequestMapping("/svc/regForm")
	public ModelAndView svcRegFormProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("svc/regForm");
	}
	@RequestMapping("/svc/tripWriteForm")
	public ModelAndView svcTripWriteFormProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("svc/tripWriteForm");
	}
	@RequestMapping("/svc/tripModForm")
	public ModelAndView svcTripModFormProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("svc/tripModForm");
	}
}
