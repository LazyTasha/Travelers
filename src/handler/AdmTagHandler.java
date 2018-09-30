package handler;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
			int num=Integer.parseInt(request.getParameter("num"));
			for(int i=1;i<=num;i++) {
				int tag_id=Integer.parseInt(request.getParameter("tag_id"+i+""));
				String tag_value=request.getParameter("tag_value"+i+"");
				//update하기
				resultcheck=tagDao.checkTag(tag_value);
				if(resultcheck<=1) {
					tagDto.setTag_id(tag_id);
					tagDto.setTag_value(tag_value);
					result=tagDao.modifyTag(tagDto);
				}
			}
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
	@RequestMapping("adminTagMod")
	public ModelAndView admTagModProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		int state=tagmod;
		
		TagDataBean tagDto=new TagDataBean();
		ArrayList<TagDataBean> tags=new ArrayList<TagDataBean>();
		try {
			String num=request.getParameter("num");
			if(num!=null) {
				for(int i=0;i<Integer.parseInt(num);i++) {
					int tag_id=Integer.parseInt(request.getParameter("id"+i+""));
					TagDataBean tag=tagDao.getTag(tag_id);
					tags.add(i, tag);
				}
			}
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		request.setAttribute("tags", tags);
		request.setAttribute("state", state);
		return new ModelAndView("adm/tagMod");
	}
	
}
	