package handler;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import db.TagDBBean;
import db.TagDataBean;

@Controller
public class AdmTagHandler {
	final int tagadd=1;
	final int tagmod=2;
	@Resource
	private TagDBBean tagDao;
	
	@RequestMapping("/adminTagMng")
	public ModelAndView admTagMngProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		//tag 추가 및 수정
		//if state=1 then insert/if state=2 then update
		int result=0;
		int resultcheck=-1;
		try {
			request.setCharacterEncoding( "utf-8" );
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		int state=Integer.parseInt(request.getParameter("state"));
		
		TagDataBean tagDto=new TagDataBean();
		if(state==tagadd) {
			//tag추가
			String values=request.getParameter("values");
			String[] v=values.split(",");
			for(int i=0;i<v.length;i++) {
				//insert하기	
				tagDto.setTag_value(v[i]);
				resultcheck=tagDao.checkTag(tagDto.getTag_value());
				if(resultcheck==0) {
					result=tagDao.insertTag(v[i]);
				}
			}
		}else if(state==tagmod) {
			//tag수정
		}
		request.setAttribute( "result", result );
		request.setAttribute( "state", state );
		return new ModelAndView("adm/tagMng");
	}
	@RequestMapping("/adminTagAdd")
	public ModelAndView admTagAddProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		int state=tagadd;
		request.setAttribute("state", state);
		return new ModelAndView("adm/tagAdd");
	}
	@RequestMapping("/adminTagMod")
	public ModelAndView admTagModProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		int state=tagmod;
		String[] ids=request.getParameterValues("ids");
		System.out.println(ids);
		System.out.println(ids[0]);
		
		request.setAttribute("state", state);
		return new ModelAndView("adm/tagMod");
	}
}
