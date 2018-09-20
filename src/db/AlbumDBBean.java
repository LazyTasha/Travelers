/*
 * !!! ���ǻ��� !!!
 * ���� void, ���� �������� �޼ҵ� �̸��� �����ص� ����.
 * �޼ҵ帶�� ���� ��, ���� �˾Ƽ� ä�� ���� ��.
 */

package db;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;

public class AlbumDBBean {
	SqlSession session=SqlMapClient.getSession();
	
	public void addPhoto() {
	}
	public void delPhoto() {
	}
	public void addPhotos() {
	}
	public void delPhotos() {
	}
}