/*
 * !!! ���ǻ��� !!!
 * ���� void, ���� �������� �޼ҵ� �̸��� �����ص� ����.
 * �޼ҵ帶�� ���� ��, ���� �˾Ƽ� ä�� ���� ��.
 */

package db;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;

public class LocDBBean {
	SqlSession session=SqlMapClient.getSession();
	
	public void getLoc() {
	}
	public void setLoc() {
	}
	public void delLoc() {
	}
	//when knowing country code, insert coordinate and if inserting data succeed, return coord_id
	public int insertCoord(LocDataBean locDto) {
		 return session.insert("db.insertCoord",locDto);
	}
	public int insertCal(LocDataBean locDto) {
		return session.insert("db.insertCal",locDto);
	}
	
	public List<LocDataBean> selectDetail(int tb_no) {
		return session.selectList("db.selectDetail",tb_no);
	}
}