/*
 * !!! ���ǻ��� !!!
 * ���� void, ���� �������� �޼ҵ� �̸��� �����ص� ����.
 * �޼ҵ帶�� ���� ��, ���� �˾Ƽ� ä�� ���� ��.
 */

package db;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;

public class TbDBBean {
	SqlSession session=SqlMapClient.getSession();
	
	//get count of all trip
	public int getCount() {
		return session.selectOne("db.getCount");
	}
	
	//get one trip post by tb_no, including location and tag list
	public TbDataBean getTb(int tb_no) {
		//augmented trip data
		TbDataBean tbDto=new TbDataBean();
		//original trip data
		TripDataBean tripDto=session.selectOne("db.getTrip", tb_no);
		
		tbDto.setTb_no(tb_no);
		//set Nickname instead of id
		tbDto.setUser_id((String) session.selectOne("db.getUserName", tripDto.getUser_id()));
		tbDto.setTb_title(tripDto.getTb_title());
		tbDto.setTb_content(tripDto.getTb_content());
		tbDto.setTb_reg_date(tripDto.getTb_reg_date());
		tbDto.setTb_v_count(tripDto.getTb_v_count());
		tbDto.setTb_m_num(tripDto.getTb_m_num());
		tbDto.setTb_notice(tripDto.getTb_notice());
		tbDto.setTb_talk(tripDto.getTb_talk());
		
		//get location list
		List<String> locList=session.selectList("db.getLocs", tb_no);
		String[] locs = null;
		for(int i=0; i<locList.size(); i++) {
			locs[i]=locList.get(i);
		}
		//get tag list
		List<String> tagList=session.selectList("db.getTripTags", tb_no);
		String[] tags=null;
		for(int i=0; i<tagList.size(); i++) {
			tags[i]=tagList.get(i);
		}
		
		tbDto.setLocs(locs);
		tbDto.setTags(tags);
		
		return tbDto;
	}
	
	//write a new trip post
	//We have to update three tables
	//trip_board : post
	//trip_tag : hash tag
	//trip_detail :location
	//not tested yet...
	public int writeTb(TbDataBean tbDto) {
		//first, insert into gg_trip_board
		int result=session.update("db.writeTrip", tbDto);
		//second, insert into gg_trip_tag
		result=session.update("db.setTripTags", tbDto);
		//last, insert into gg_trip_detail, it has list of locations
		result=session.update("db.setTripDetails", tbDto);
		return result;
	}
	
	//TbDataBean has user_name as user_id instead of real user_id, 
	//so we need this
	public String getUserId(String user_name) {
		return session.selectOne("db.getUserId", user_name);
	}

	//trip board list
	public List<TbDataBean> getTrips(Map<String,Integer> map){
		return session.selectList("db.getTrips",map);

	public int deleteTrip(int tb_no) {
		return session.selectOne("db.deleteTrip", tb_no);
	}
}
