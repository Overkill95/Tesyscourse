package it.hibernate;

import java.sql.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="TitleCount")
public class SalariesOutput {
	private Date fromDate;
	private SimpleEmployee employees;
	private int salary;
	private Date toDate;
	
	public SalariesOutput() {
		super();
	}
	public SalariesOutput( Date fromDate, SimpleEmployee employees, int salary, Date toDate) {
		super();
		this.fromDate = fromDate;
		this.employees = employees;
		this.salary = salary;
		this.toDate = toDate;
	}

	@XmlAttribute(name="from_date")
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public SimpleEmployee getEmployees() {
		return employees;
	}
	public void setEmployees(SimpleEmployee employees) {
		this.employees = employees;
	}
	@XmlAttribute(name="salary")
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	@XmlAttribute(name="to_date")
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
}
