/*
 * !!! ���ǻ��� !!!
 * ���� void, ���� �������� �޼ҵ� �̸��� �����ص� ����.
 * �޼ҵ帶�� ���� ��, ���� �˾Ƽ� ä�� ���� ��.
 */

package db;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;

public class CmtDBBean {
	SqlSession session=SqlMapClient.getSession();
	
	public void addComment() {
	}
	public void delComment() {
	}
	public void modComment() {
	}
}
