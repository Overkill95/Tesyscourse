package it.matteo.vo;

import java.sql.Date;

public class DeptEmp {
	private int emp_no;
	private String dept_no;
	private String from_date;
	private String to_date;
	public DeptEmp(int emp_no, String dept_no, String fd, String td){
		this.emp_no=emp_no;
		this.dept_no=dept_no;
		this.from_date=fd;
		this.to_date=td;
	}
	public int getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}
	public String getDept_no() {
		return dept_no;
	}
	public void setDept_no(String dept_no) {
		this.dept_no = dept_no;
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
		return "DeptEmp [emp_no=" + emp_no + ", dept_no=" + dept_no + ", from_date=" + from_date + ", to_date="
				+ to_date + "]";
	}
}
