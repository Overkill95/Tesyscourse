package it.hibernate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Salary_count_foreach_employee_output")
public class SalaryCountOutput {
	private int salary;
	private long count;
	
	public SalaryCountOutput() {
		super();
	}

	public SalaryCountOutput(int s, long count) {
		super();
		this.salary = s;
		this.count = count;
	}
	@XmlAttribute(name="Salary")
	public int getSalary() {
		return salary;
	}

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
