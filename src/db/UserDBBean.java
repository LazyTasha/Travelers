/*
 * !!! ���ǻ��� !!!
 * ���� void, ���� �������� �޼ҵ� �̸��� �����ص� ����.
 * �޼ҵ帶�� ���� ��, ���� �˾Ƽ� ä�� ���� ��.
 */

package db;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;

public class UserDBBean {
private SqlSession session = SqlMapClient.getSession();
	
	public int insertMember( UserDataBean UserDto ) {
		return session.insert("db.insertMember", UserDto);
	}

	public int check( String user_id ) {
		return session.selectOne( "db.checkId", user_id);
	}
	
	public int namecheck( String user_name ) {
		return session.selectOne( "db.checkname", user_name);
	}
	
	public int idcheck( String user_id ) {
		return session.selectOne( "db.idcheck", user_id);
	}
		
	public int check( String user_id, String passwd ) {
		int result = 0;		
		if( check( user_id ) > 0 ) {
			// 아이디가 있다
			UserDataBean UserDto = getMember( user_id );
			if( passwd.equals( UserDto.getPasswd() ) ) {
				result = 1;
			} else {
				result = -1;
			}				
		} else {
			// 아이디가 없다
			result = 0;				
		}
		return result;
	}

	public int deleteMember( String user_id ) {
		return session.delete("db.deleteMember", user_id);
	}
	
	public UserDataBean getMember( String user_id ) {
		return session.selectOne( "db.getMember", user_id );
		
	}
	
	public int modifyMember( UserDataBean UserDto ) {
		return session.update( "db.modifyMember", UserDto );
	}
	
}
