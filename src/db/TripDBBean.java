package db;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;

public class TripDBBean {
	SqlSession session=SqlMapClient.getSession();
	
	public TripDataBean getTrip(int tb_no) {
		TripDataBean tripDto=session.selectOne("db.getTrip", tb_no);
		return tripDto;
	}
	public void addViewCount(int tb_no) {
		session.update("db.addViewCount", tb_no);
	}
}
