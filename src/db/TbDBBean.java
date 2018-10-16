package db;

import java.util.ArrayList;
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
	
	public void addCount(int tb_no) {
		session.update("db.addCount", tb_no);
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
		//get 20 latest articles from db
		List<TripDataBean> originList=session.selectList("db.getTripList");
		List<TbDataBean> tripList=new ArrayList<TbDataBean>();
		
		for(int i=originList.size(); i>0; i--) {
			TripDataBean tripDto=originList.get(i-1);
			TbDataBean tbDto=new TbDataBean();
			tbDto.setTb_no(tripDto.getTb_no());
			//set Nickname instead of id
			String user_id=tripDto.getUser_id();
			String user_name;
			//if that user left
			if(user_id==null||user_id.equals("")) {
				user_name="Ex-User";
			} else {
				user_name=(String) session.selectOne("db.getUserName", user_id);
			}
			tbDto.setUser_id(user_name);
			tbDto.setTb_title(tripDto.getTb_title());
			tbDto.setTb_content(tripDto.getTb_content());
			tbDto.setTb_reg_date(tripDto.getTb_reg_date());
			tbDto.setTb_v_count(tripDto.getTb_v_count());
			tbDto.setTb_m_num(tripDto.getTb_m_num());
			tbDto.setTb_notice(tripDto.getTb_notice());
			tbDto.setTb_talk(tripDto.getTb_talk());
			
			//locations and tags 
			List <Integer> tripIds=session.selectList("db.getTripIds", tripDto.getTb_no());
			String[] locs=new String[tripIds.size()];
			for(int j=0; j<tripIds.size(); j++) {
				locs[j]=session.selectOne("db.getDestination", tripIds.get(j));
			}
			tbDto.setLocs(locs);
			
			List<TagDataBean> originTags=session.selectList("db.getTripTags", tripDto.getTb_no());
			String[] tags=new String[originTags.size()];
			for(int k=0; k<originTags.size(); k++) {
				tags[k]=originTags.get(k).getTag_value();
			}
			tbDto.setTags(tags);
			//put each TbDataBean into the List!
			tripList.add(tbDto);
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
	public List<TbDataBean> loadMoreList(int last_tb_no) {
		List<TripDataBean> originMoreList=session.selectList("db.loadMoreList", last_tb_no);
		List<TbDataBean> moreList;
		if(originMoreList==null||originMoreList.size()==0) {
			moreList=null;
		} else {
			moreList=new ArrayList<TbDataBean>(originMoreList.size());
			moreList=new ArrayList<TbDataBean>(originMoreList.size());
			for(int i=0; i<originMoreList.size(); i++) {
				TbDataBean tempTb=new TbDataBean();
				TripDataBean tripDto=originMoreList.get(i);
				tempTb.setTb_no(tripDto.getTb_no());
				tempTb.setUser_id((String) session.selectOne("db.getUserName", tripDto.getUser_id()));
				tempTb.setTb_title(tripDto.getTb_title());
				tempTb.setTb_content(tripDto.getTb_content());
				tempTb.setTb_reg_date(tripDto.getTb_reg_date());
				tempTb.setTb_v_count(tripDto.getTb_v_count());
				tempTb.setTb_m_num(tripDto.getTb_m_num());
				tempTb.setTb_notice(tripDto.getTb_notice());
				tempTb.setTb_talk(tripDto.getTb_talk());
				
				//get location list
				List <Integer> tripIds=session.selectList("db.getTripIds", tripDto.getTb_no());
				String[] locs=new String[tripIds.size()];
				for(int j=0; j<tripIds.size(); j++) {
					locs[j]=session.selectOne("db.getDestination", tripIds.get(j));
				}
				tempTb.setLocs(locs);
				
				List<String> originTags=session.selectList("db.getTripTags", tripDto.getTb_no());
				String[] tags=new String[originTags.size()];
				for(int k=0; k<originTags.size(); k++) {
					tags[k]=originTags.get(k);
				}
				tempTb.setTags(tags);
				
				tempTb.setLocs(locs);
				tempTb.setTags(tags);
				
				moreList.set(i, tempTb);
			}
		}
		return moreList;
	}
}
