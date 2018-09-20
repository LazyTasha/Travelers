/*
 * !!! ���ǻ��� !!!
 * ���� void, ���� �������� �޼ҵ� �̸��� �����ص� ����.
 * �޼ҵ帶�� ���� ��, ���� �˾Ƽ� ä�� ���� ��.
 */

package db;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;

public class TagDBBean {
	SqlSession session=SqlMapClient.getSession();
	
	public List<TagDataBean> getTagList() {
		return session.selectList("db.getTagList");
	}
}
