package db;

public class LocDataBean {
	private int num;
	private int tripId;
	private int country_id;
	private String country_code;
	private double coord_long;
	private double coord_lat;
	private int coord_order;
	private int coord_id;
	private String cal_start_date;
	private String cal_end_date;
	private int td_trip_id;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getTripId() {
		return tripId;
	}
	public void setTripId(int tripId) {
		this.tripId = tripId;
	}
	public int getCountry_id() {
		return country_id;
	}
	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}
	public String getCountry_code() {
		return country_code;
	}
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}
	public double getCoord_long() {
		return coord_long;
	}
	public void setCoord_long(double coord_long) {
		this.coord_long = coord_long;
	}
	public double getCoord_lat() {
		return coord_lat;
	}
	public void setCoord_lat(double coord_lat) {
		this.coord_lat = coord_lat;
	}
	public int getCoord_order() {
		return coord_order;
	}
	public void setCoord_order(int coord_order) {
		this.coord_order = coord_order;
	}
	public int getCoord_id() {
		return coord_id;
	}
	public void setCoord_id(int coord_id) {
		this.coord_id = coord_id;
	}
	public String getCal_start_date() {
		return cal_start_date;
	}
	public void setCal_start_date(String cal_start_date) {
		this.cal_start_date = cal_start_date;
	}
	public String getCal_end_date() {
		return cal_end_date;
	}
	public void setCal_end_date(String cal_end_date) {
		this.cal_end_date = cal_end_date;
	}
	public int getTd_trip_id() {
		return td_trip_id;
	}
	public void setTd_trip_id(int td_trip_id) {
		this.td_trip_id = td_trip_id;
	}
}
