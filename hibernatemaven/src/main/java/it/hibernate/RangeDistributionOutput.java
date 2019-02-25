package it.hibernate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Range_Distribution_Output")
public class RangeDistributionOutput {
	private String title;
	private String deptNo;
	private String deptName;
	private String range;
	private long count;
	
	
	
	public RangeDistributionOutput() {
		super();
	}



	public RangeDistributionOutput(String title, String deptNo, String deptName, int lowerbound, int upperbound, long count) {
		super();
		this.title = title;
		this.setDeptNo(deptNo);
		this.deptName = deptName;
		this.range = ""+lowerbound+"-"+upperbound+"";
		this.count = count;
	}


	@XmlAttribute(name="title")
	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}


	@XmlAttribute(name="dipartimento")
	public String getDeptName() {
		return deptName;
	}



	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}


	@XmlAttribute(name="range_salari")
	public String getRange() {
		return range;
	}



	public void setRange(String range) {
		this.range = range;
	}


	
	public long getCount() {
		return count;
	}


	@XmlAttribute(name="Count")
	public void setCount(long count) {
		this.count = count;
	}



	public String getDeptNo() {
		return deptNo;
	}



	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
	
	
}
