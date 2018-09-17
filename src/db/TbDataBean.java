package db;

import java.sql.Timestamp;
import java.util.Map;

public class TbDataBean extends TripDataBean {
	public int num;
	public String writer;
	public String title;
	public String content;
	public Timestamp reg_date;
	public int viewcount;
	public int memberNum;
	public int notice;
	public String talk;
	public Map<Integer, Integer> locs;
	public Map<Integer, String> tags;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public int getViewcount() {
		return viewcount;
	}
	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}
	public int getMemberNum() {
		return memberNum;
	}
	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}
	public int getNotice() {
		return notice;
	}
	public void setNotice(int notice) {
		this.notice = notice;
	}
	public String getTalk() {
		return talk;
	}
	public void setTalk(String talk) {
		this.talk = talk;
	}
	public Map<Integer, Integer> getLocs() {
		return locs;
	}
	public void setLocs(Map<Integer, Integer> locs) {
		this.locs = locs;
	}
	public Map<Integer, String> getTags() {
		return tags;
	}
	public void setTags(Map<Integer, String> tags) {
		this.tags = tags;
	}
}
