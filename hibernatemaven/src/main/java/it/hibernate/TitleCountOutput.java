package it.hibernate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="TitleCount")
public class TitleCountOutput {
	private String title;
	private long count;
	
	public TitleCountOutput() {
		super();
	}

	public TitleCountOutput(String title, long count) {
		super();
		this.title = title;
		this.count = count;
	}
	@XmlAttribute(name="title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@XmlAttribute(name="Count")
	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
	
	
}
