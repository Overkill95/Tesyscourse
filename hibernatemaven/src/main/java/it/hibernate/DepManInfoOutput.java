package it.hibernate;

import java.sql.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Info_impiegati_dipartimenti_manager")
public class DepManInfoOutput {
	private SimpleEmployee manager;
	private SimpleDepartment dipartimento;
	private Date fromDate;
	private Date toDate;
	private String title;
	private int salary;
	
	
	
	public DepManInfoOutput() {
		super();
	}



	public DepManInfoOutput( SimpleEmployee manager, SimpleDepartment dipartimento,
			Date fromDate, Date toDate, String title, int salary) {
		super();
		this.manager = manager;
		this.dipartimento = dipartimento;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.title = title;
		this.salary = salary;
	}



	public SimpleEmployee getManager() {
		return manager;
	}



	public void setManager(SimpleEmployee manager) {
		this.manager = manager;
	}



	public SimpleDepartment getDipartimento() {
		return dipartimento;
	}



	public void setDipartimento(SimpleDepartment dipartimento) {
		this.dipartimento = dipartimento;
	}


	@XmlAttribute(name="from_date")
	public Date getFromDate() {
		return fromDate;
	}



	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}


	@XmlAttribute(name="to_date")
	public Date getToDate() {
		return toDate;
	}



	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}


	@XmlAttribute(name="title")
	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}


	@XmlAttribute(name="salary")
	public int getSalary() {
		return salary;
	}



	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	
	
}
