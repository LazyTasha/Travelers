/*
 * !!! ���ǻ��� !!!
 * ���� void, ���� �������� �޼ҵ� �̸��� �����ص� ����.
 * �޼ҵ帶�� ���� ��, ���� �˾Ƽ� ä�� ���� ��.
 */

package db;

import org.apache.ibatis.session.SqlSession;

public class TripDBBean {
	SqlSession session;
	
	public TripDataBean getTrip() {
		TripDataBean tripDto=new TripDataBean();
		return tripDto;
	}
}
