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
		return session.insert("Travelers.insertMember", UserDto);
	}

	public int check( String id ) {
		return session.selectOne( "Travelers.checkId", id);
	}
		
	public int check( String id, String passwd ) {
		int result = 0;		
		if( check( id ) > 0 ) {
			// 아이디가 있다
			UserDataBean UserDto = getMember( id );
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

	public int deleteMember( String id ) {
		return session.delete("Travelers.deleteMember", id);
	}
	
	public UserDataBean getMember( String id ) {
		return session.selectOne( "Travelers.getMember", id );
		
	}
	
	public int modifyMember( UserDataBean UserDto ) {
		return session.update( "Travelers.modifyMember", UserDto );
	}
	
}
