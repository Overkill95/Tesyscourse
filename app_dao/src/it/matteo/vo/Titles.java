package it.matteo.vo;

import java.sql.Date;

public class Titles {
	private int empt_no;
	private String title;
	private String from_date;
	private String to_date;
	public Titles(int empt_no, String t, String fd, String td) {
		this.empt_no=empt_no;
		this.title=t;
		this.from_date=fd;
		this.to_date=td;
	}
	public int getEmpt_no() {
		return empt_no;
	}
	public void setEmpt_no(int empt_no) {
		this.empt_no = empt_no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFrom_date() {
		return from_date;
	}
	public void setFrom_date(String from_date) {
		this.from_date = from_date;
	}
	public String getTo_date() {
		return to_date;
	}
	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}
	@Override
	public String toString() {
		return "Titles [empt_no=" + empt_no + ", title=" + title + ", from_date=" + from_date + ", to_date=" + to_date
				+ "]";
	}
}
