package it.hibernate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Managers_salary_title_count")
public class ManSalaryTitleOutput {
	private String title;
	private int salary;
	private long count;
	
	public ManSalaryTitleOutput() {
		super();
	}


	public ManSalaryTitleOutput( String title, int salary, long count) {
		super();
		this.title = title;
		this.salary = salary;
		this.setCount(count);
	}

	@XmlAttribute(name="title")
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getSalary() {
		return salary;
	}

	@XmlAttribute(name="salary")
	public void setSalary(int salary) {
		this.salary = salary;
	}

	@XmlAttribute(name="Count")
	public long getCount() {
		return count;
	}


	public void setCount(long count) {
		this.count = count;
	}
	
}
