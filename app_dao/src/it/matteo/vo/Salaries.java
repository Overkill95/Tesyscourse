package it.matteo.vo;

import java.sql.Date;

public class Salaries {
	private int empt_no;
	private int salary;
	private String from_date;
	private String to_date;
	public Salaries(int empt_no, int s, String fd, String td) {
		this.empt_no=empt_no;
		this.salary=s;
		this.from_date=fd;
		this.to_date=td;
	}
	public void setEmpt_no(int empt_no) {
		this.empt_no = empt_no;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public void setFrom_date(String from_date) {
		this.from_date = from_date;
	}
	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}
	public int getEmpt_no() {
		return empt_no;
	}
	public int getSalary() {
		return salary;
	}
	public String getFrom_date() {
		return from_date;
	}
	public String getTo_date() {
		return to_date;
	}
	@Override
	public String toString() {
		return "Salaries [empt_no=" + empt_no + ", salary=" + salary + ", from_date=" + from_date + ", to_date="
				+ to_date + "]";
	}
	
}
