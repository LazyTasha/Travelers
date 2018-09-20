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

public class UserDBBean {
	private SqlSession session=SqlMapClient.getSession();
	
	public List<UserDataBean> getUsers(Map<String, Integer> map) {
		return session.selectList("Member.getUsers",map);
	}
	public int getCount() {
		return session.selectOne("Member.getUCount");
	}
}
