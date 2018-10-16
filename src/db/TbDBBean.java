package db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;

public class TbDBBean {
	SqlSession session=SqlMapClient.getSession();
	
	//get count of all trip
	public int getCount() {
		return session.selectOne("db.getCount");
	}
	
	//get one trip post by tb_no, including location and tag list
	public TbDataBean getTb(int tb_no) {
		//original trip data
		TbDataBean tbDto=session.selectOne("db.getTrip", tb_no);
		//set Nickname instead of id
		//set Nickname instead of id
		String user_id=tbDto.getUser_id();
		String user_name;
		//if that user left
		if(user_id==null||user_id.equals("")) {
			user_name="Ex-User";
		} else {
			user_name=(String) session.selectOne("db.getUserName", user_id);
		}
		tbDto.setUser_id(user_name);
		
		//trip detail
		List<Integer> td_trip_id_list=session.selectList("db.getTripIds", tb_no);
		int[] td_trip_ids=new int[td_trip_id_list.size()];
		if(td_trip_id_list.size()>0) {
			for(int i=0; i<td_trip_id_list.size(); i++) {
				td_trip_ids[i]=td_trip_id_list.get(i);
			}
		}
		tbDto.setTd_trip_ids(td_trip_ids);
		
		return tbDto;
	}
	public int insertTb_no(TbDataBean tbDto) {
	      return session.insert("db.insertTb_no",tbDto);
	   }
	//write a new trip post
	//We have to update three tables
	//trip_board : post
	//trip_tag : hash tag
	//trip_detail :location
	//not tested yet...
	public int writeTb(TbDataBean tbDto) {
		//first, insert into gg_trip_board
		int result=session.update("db.writeTrip", tbDto);
		//second, insert into gg_trip_tag
		result=session.update("db.setTripTags", tbDto);
		//last, insert into gg_trip_detail, it has list of locations
		result=session.update("db.setTripDetails", tbDto);
		return result;
	}
	//�뇦猿딆뒩占쎈뻣占쎈닱占쎈닔占쎈굵 嶺뚮씭�뒧獄�占� �뜝�럩�쐩 �뜝�럩逾у뜝�럩�젧 �뤆�룇裕뉛옙�빢嶺뚮씭�뒭野껓옙 �뜝�럡臾멨뜝�럡�뎽�뜝�럥�뵹�뜝�럥裕� gg_trip_detail table
	public int insertTripDetail(TbDataBean tbDto) {
		return session.insert("db.insertTripDetail",tbDto);
	}
	//TbDataBean has user_name as user_id instead of real user_id, 
	//so we need this
	public String getUserId(String user_name) {
		return session.selectOne("db.getUserId", user_name);
	}
	//updateTb
	public int updateTb(TbDataBean tbDto) {
		return session.update("db.updateTb", tbDto);
	}
	//trip board list
	public List<TbDataBean> getTrips(Map<String,Integer> map){
		return session.selectList("db.getTrips",map);
	}
	
	public List<TbDataBean> getTripList() {
		//get 10 latest articles from db
		int start=1;
		int end=10;
		
		Map<String, Integer> tripReq=new HashMap<String, Integer>();
		tripReq.put("start", start);
		tripReq.put("end", end);
		List<TbDataBean> tripList=session.selectList("db.getTrips", tripReq);
		
		for(TbDataBean tbDto:tripList) {
			//set Nickname instead of id
			String user_id=tbDto.getUser_id();
			String user_name;
			//if that user left
			if(user_id==null||user_id.equals("")) {
				user_name="Ex-User";
			} else {
				user_name=(String) session.selectOne("db.getUserName", user_id);
			}
			tbDto.setUser_id(user_name);
			
			//locations and tags 
			List <Integer> tripIds=session.selectList("db.getTripIds", tbDto.getTb_no());
			String[] locs=new String[tripIds.size()];
			for(int j=0; j<tripIds.size(); j++) {
				locs[j]=session.selectOne("db.getDestination", tripIds.get(j));
			}
			tbDto.setLocs(locs);
			
			List<TagDataBean> originTags=session.selectList("db.getTripTags", tbDto.getTb_no());
			String[] tags=new String[originTags.size()];
			for(int k=0; k<originTags.size(); k++) {
				tags[k]=originTags.get(k).getTag_value();
			}
			tbDto.setTags(tags);
		}
		return tripList;
	}
	
	public List<TbDataBean> getNextTrips(int latest) {
		//get 20 previous articles 
		return session.selectList("db.getNextTrips", latest);
	}

	public int deleteTrip(int tb_no) {
		return session.delete("db.deleteTrip", tb_no);
	}
	//member check
	public boolean isMember(TbDataBean tbDto) {
		int count=session.selectOne("db.isMember",tbDto);
		if(count>0)return true;
		else return false;
	}
	
	//load more Trip articles from db
	//Loading starts from last number of current page
	public List<TbDataBean> loadMoreList(int last_row) {
		int start=last_row;
		int end=start+5;
		
		Map<String, Integer> tripReq=new HashMap<String, Integer>();
		tripReq.put("start", start);
		tripReq.put("end", end);
		
		System.out.println("추가 리스트 요청까지 잘 옴."+start+"번부터"+end+"번까지 꺼내달라고 함.");
		List<TbDataBean> tripList=session.selectList("db.getTrips", tripReq);
		System.out.println("추가 리스트 꺼내옴. 글 수는 "+tripList.size()+"개임.");
		for(TbDataBean tbDto:tripList) {
			//set Nickname instead of id
			String user_id=tbDto.getUser_id();
			String user_name;
			//if that user left
			if(user_id==null||user_id.equals("")) {
				user_name="Ex-User";
			} else {
				user_name=(String) session.selectOne("db.getUserName", user_id);
			}
			tbDto.setUser_id(user_name);
			
			//locations and tags 
			List <Integer> tripIds=session.selectList("db.getTripIds", tbDto.getTb_no());
			String[] locs=new String[tripIds.size()];
			for(int j=0; j<tripIds.size(); j++) {
				locs[j]=session.selectOne("db.getDestination", tripIds.get(j));
			}
			tbDto.setLocs(locs);
			
			List<TagDataBean> originTags=session.selectList("db.getTripTags", tbDto.getTb_no());
			String[] tags=new String[originTags.size()];
			for(int k=0; k<originTags.size(); k++) {
				tags[k]=originTags.get(k).getTag_value();
			}
			tbDto.setTags(tags);
		}
		return tripList;
	}
}
