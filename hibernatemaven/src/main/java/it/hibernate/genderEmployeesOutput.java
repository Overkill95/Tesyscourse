package it.hibernate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Numero_impiegati_per_sesso_e_titolo")
public class genderEmployeesOutput {
	private String title;
	private String gender;
	private long count;
	public genderEmployeesOutput() {}
	public genderEmployeesOutput(String title, String gender, long count) {
		super();
		this.title = title;
		this.gender = gender;
		this.count = count;
	}
	@XmlAttribute(name="Title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@XmlAttribute(name="Gender")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@XmlAttribute(name="Count")
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	
}
