/*
 * !!! ���ǻ��� !!!
 * ���� void, ���� �������� �޼ҵ� �̸��� �����ص� ����.
 * �޼ҵ帶�� ���� ��, ���� �˾Ƽ� ä�� ���� ��.
 */

package db;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;



public class CmtDBBean {
	SqlSession session=SqlMapClient.getSession();
	//total comment
	public int insertComment(CmtDataBean cmtDto) {
		return session.insert("db.insertComment", cmtDto);
	}
	
	public List<CmtDataBean> getComment( CmtDataBean cmtDto ) {
		return session.selectList("db.getComment", cmtDto);
	}
	
	public int updateComment(CmtDataBean cmtDto) {
		return session.update("db.updateComment", cmtDto);
	}
	
	public int deleteComment( int c_id ) {
		return session.delete("db.deleteComment", c_id);
	}
	
	
	
}
