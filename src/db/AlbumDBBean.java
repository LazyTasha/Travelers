package db;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;

public class AlbumDBBean {
	SqlSession session=SqlMapClient.getSession();
	
	public int addPhoto(AlbumDataBean albumDto) {
		return session.insert("db.addPhoto",albumDto);
	}
	public void delPhoto(int photo_id) {
		session.delete("db.delPhoto",photo_id);
	}
	public void addPhotos() {
	}
	public void delPhotos() {
	}
	public List<AlbumDataBean> getAlbum(){
		return session.selectList("db.getAlbum");
	}
	public int getCount() {
		return session.selectOne("db.getPCount");
	}
}