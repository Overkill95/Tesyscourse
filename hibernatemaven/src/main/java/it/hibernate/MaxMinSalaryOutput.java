package it.hibernate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="max_min_salary_per_dip_titolo")
public class MaxMinSalaryOutput {
	private String deptName;
	private String title;
	private long max;
	private long min;
	public MaxMinSalaryOutput() {}
	public MaxMinSalaryOutput(String deptName, String title, long max, long min) {
		super();
		this.deptName = deptName;
		this.title = title;
		this.setMax(max);
		this.setMin(min);
	}
	@XmlAttribute(name="Dipartimento")
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	@XmlAttribute(name="Titolo")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@XmlAttribute(name="Max")
	public long getMax() {
		return max;
	}
	public void setMax(long max) {
		this.max = max;
	}
	@XmlAttribute(name="Min")
	public long getMin() {
		return min;
	}
	public void setMin(long min) {
		this.min = min;
	}
	
}
