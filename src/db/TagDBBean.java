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

public class TagDBBean {
	SqlSession session=SqlMapClient.getSession();
	
	//add tag
	public int insertTag(String tag_value) {
		return session.insert("db.insertTag",tag_value);
	}
	//tag list
	public  List<TagDataBean> getTags(Map<String, Integer> map){
		return session.selectList("db.getTags",map);	
	}
	//total tag
	public int getCount() {
		return session.selectOne("db.getTCount");
	}
	//check tag
	public int checkTag(String tag_value) {
		return session.selectOne("db.checkTag",tag_value);
	}
	//show tag info
	public TagDataBean getTag(int tag_id) {
		return session.selectOne("db.getTag",tag_id);
	}
	//modify tag
	public int modifyTag(TagDataBean tagDao) {
		return session.update("db.modifyTag",tagDao);
	}
	//delete tag
	public int deleteTag(String tag_id) {
		return session.delete("db.deleteTag",tag_id);
	}
	public List<TagDataBean> getUserTags(String user_id) {
		return session.selectList("db.getUserTags", user_id);
	}
	public List<TagDataBean> getTripTags(int tb_no) {
		return session.selectList("db.getTripTags", tb_no);
	}
	public List<TagDataBean> getStyleTags() {
		return session.selectList("db.getStyleTags");
	}
}
