package handler;

import java.io.UnsupportedEncodingException;

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

@Controller
public class SvcProHandler {
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
	
	@RequestMapping("/loginPro")
	public ModelAndView svcLoginProProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("svc/loginPro");
	}
	@RequestMapping("/regPro")
	public ModelAndView svcRegProProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("svc/regPro");
	}
	@RequestMapping("/tripWritePro")
	public ModelAndView svcTripWriteProProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException, UnsupportedEncodingException {
		TbDataBean tbDto=new TbDataBean();
		tbDto.setTb_title(request.getParameter("title"));
		String user_name=new String((request.getParameter("user_name")).getBytes("8859_1"), "UTF-8");
		String user_id=tbDao.getUserId(user_name);
		tbDto.setUser_id(user_id);
		tbDto.setTb_content(request.getParameter("content"));
		tbDto.setTb_m_num(Integer.parseInt(request.getParameter("trip_m_num")));
		tbDto.setTb_talk(request.getParameter("trip_talk_link"));
		String[] testTag=request.getParameterValues("tag");
		tbDto.setTags(testTag);
		TbDBBean tbDao=new TbDBBean();
		int result=tbDao.writeTb(tbDto);
		request.setAttribute("result", result);
		request.setAttribute("tbDto", tbDto);
		return new ModelAndView("svc/tripWritePro");
	}
	@RequestMapping("/tripModPro")
	public ModelAndView svcTrpModProProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("svc/tripModPro");
	}
}
