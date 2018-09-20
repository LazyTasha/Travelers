/*
 * !!! ���ǻ��� !!!
 * ���� void, ���� �������� �޼ҵ� �̸��� �����ص� ����.
 * �޼ҵ帶�� ���� ��, ���� �˾Ƽ� ä�� ���� ��.
 */

package db;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;

public class TbDBBean {
SqlSession session=SqlMapClient.getSession();
	
	public int getCount() {
		return session.selectOne("db.getCount");
	}
	public TbDataBean getTb(int num) {
		TbDataBean tbDto=new TbDataBean();
		TripDataBean tripDto=session.selectOne("db.getTrip", num);
		
		tbDto.setTb_no(num);
		//set Nickname instead of id
		tbDto.setUser_id((String) session.selectOne("db.getUserName", tbDto.getUser_id()));
		tbDto.setTb_title(tripDto.getTb_title());
		tbDto.setTb_content(tripDto.getTb_content());
		tbDto.setTb_reg_date(tripDto.getTb_reg_date());
		tbDto.setTb_v_count(tripDto.getTb_v_count());
		tbDto.setTb_m_num(tripDto.getTb_m_num());
		tbDto.setTb_notice(tripDto.getTb_notice());
		tbDto.setTb_talk(tripDto.getTb_talk());
		List<String> locList=session.selectList("db.getLocs", num);
		String[] locs = null;
		for(int i=0; i<locList.size(); i++) {
			locs[i]=locList.get(i);
		}
		List<String> tagList=session.selectList("db.getTripTags", num);
		String[] tags=null;
		for(int i=0; i<tagList.size(); i++) {
			tags[i]=tagList.get(i);
		}
		tbDto.setLocs(locs);
		tbDto.setTags(tags);
		return tbDto;
	}
	public int writeTb(TbDataBean tbDto) {
		int result=session.update("db.writeTrip", tbDto);
		System.out.println(result);
		result=session.update("db.setTripTags", tbDto.getTags());
		System.out.println(result);
		return result;
	}
	public String getUserId(String user_name) {
		return session.selectOne("db.getUserId", user_name);
	}
}
