/*
 * !!! ���ǻ��� !!!
 * ���� void, ���� �������� �޼ҵ� �̸��� �����ص� ����.
 * �޼ҵ帶�� ���� ��, ���� �˾Ƽ� ä�� ���� ��.
 */

package db;

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
	//when knowing country code, insert coordinate
	public int insertCoord(LocDataBean locDto) {
		return session.insert("db.insertCoord",locDto);
	}
}
