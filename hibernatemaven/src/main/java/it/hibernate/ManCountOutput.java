package it.hibernate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Manager_Count")
public class ManCountOutput {
	private SimpleDepartment sd;
	private long count;
	
	
	
	public ManCountOutput() {
		super();
	}



	public ManCountOutput(SimpleDepartment sd, long count) {
		super();
		this.sd=sd;
		this.count=count;
	}


	
	public SimpleDepartment getSd() {
		return sd;
	}



	public void setSd(SimpleDepartment sd) {
		this.sd = sd;
	}


	@XmlAttribute(name="Count")
	public long getCount() {
		return count;
	}



	public void setCount(long count) {
		this.count = count;
	}
	
	
}
