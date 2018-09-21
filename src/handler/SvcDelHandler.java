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
import db.UserDBBean;

@Controller
public class SvcDelHandler {
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
	
	@RequestMapping("/svc/regDel")
	public ModelAndView svcRegDelProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("svc/regDel");
	}
	@RequestMapping("/svc/tripDel")
	public ModelAndView svcTripDelProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("svc/tripDel");
	}
	@RequestMapping( "/memberDeletePro" )
	public ModelAndView DeleteProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
 		String id = (String) request.getSession().getAttribute( "memid" );
		String passwd = request.getParameter( "passwd" );
 		int resultCheck = userDao.check( id, passwd );
 		request.setAttribute( "resultCheck", resultCheck );
		
		if( resultCheck == 1 ) {
			int result =userDao.deleteMember( id );
			request.setAttribute( "result", result );
		}
		
		return new ModelAndView( "svc/deletePro" );
	}
}
