package db;

import java.sql.Timestamp;

public class CmtDataBean {
	private int id;
	private String writer;
	private int tb_no;
	private String content;
	private Timestamp reg_date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getTb_no() {
		return tb_no;
	}
	public void setTb_no(int tb_no) {
		this.tb_no = tb_no;
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
}
