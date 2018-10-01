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

public class CmtDBBean {
	SqlSession session=SqlMapClient.getSession();
	//total comment
	public int getCount() {
		return session.selectOne("db.getCmtCount");
	}
	//
	public List<CmtDataBean>getComments(Map<String,Integer>map){
		return session.selectList("db.getComments",map);
	}
	public void addComment() {
	}
	public int delComment(int c_id) {
		return session.delete("db.delComment",c_id);
	}
	public void modComment() {
	}
}
