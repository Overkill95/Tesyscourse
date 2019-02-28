package it.hibernate;

import java.sql.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="TitlesOutput")
public class TitlesOutput {
	private String title;
	private Date fromDate;
	private SimpleEmployee se;
	
	public TitlesOutput() {
		super();
	}

	public TitlesOutput(String title, Date fromDate, SimpleEmployee se) {
		super();
		this.title = title;
		this.fromDate = fromDate;
		this.se = se;
	}
	@XmlAttribute(name="title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@XmlAttribute(name="from_date")
	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public SimpleEmployee getSe() {
		return se;
	}

	public void setSe(SimpleEmployee se) {
		this.se = se;
	} 
	
	
}
