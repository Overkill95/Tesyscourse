package it.matteo.vo2;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.*;

import it.matteo.utils2.Gender;
import it.matteo.utils2.InvalidInputException;
@XmlRootElement
@Entity
@Table(name = "employees", schema="employees")
public class Employees implements Serializable {
	@Id
	@Column(name = "emp_no")
	private int empt_no;
	@Column(name = "birth_date")
	private Date birth_date;
	@Column(name = "first_name")
	private String first_name;
	@Column(name = "last_name")
	private String last_name;
	@Column(name = "gender")
	private Gender gender;
	@Column(name = "hire_date")
	private Date hire_date;
	/*
	 * public Employees(Integer empt_no, Date bd ,String fn, String ln, String g, Date hd) throws InvalidInputException {
		if(empt_no==null || bd==null || fn==null || ln==null || g==null || hd==null) throw new InvalidInputException("Input non valido");
		this.setEmpt_no(empt_no);
		this.birth_date = bd;
		this.first_name=fn;
		this.last_name=ln;
		this.gender=Gender.toGender(g);
		this.hire_date=hd;
	}
	*/
	
	@Override
	public String toString() {
		return "Employees [empt_no=" + empt_no + ", birth_date=" + birth_date + ", first_name=" + first_name
				+ ", last_name=" + last_name + ", gender=" + gender + ", hire_date=" + hire_date + "]";
	}
	@XmlAttribute
	public int getEmpt_no() {
		return empt_no;
	}
	public void setEmpt_no(int empt_no){
		this.empt_no = empt_no;
	}
	
	public void setBirth_date(Date birth_date) throws InvalidInputException {
		if(birth_date == null) throw new InvalidInputException("Input non valido");
		this.birth_date = birth_date;
	}

	public void setFirst_name(String first_name) throws InvalidInputException {
		if(first_name == null) throw new InvalidInputException("Input non valido");
		this.first_name = first_name;
	}

	public void setLast_name(String last_name) throws InvalidInputException {
		if(last_name == null) throw new InvalidInputException("Input non valido");
		this.last_name = last_name;
	}

	public void setGender(Gender gender) throws InvalidInputException {
		if(gender == null) throw new InvalidInputException("Input non valido");
		this.gender = gender;
	}

	public void setHire_date(Date hire_date) throws InvalidInputException {
		if(hire_date == null) throw new InvalidInputException("Input non valido");
		this.hire_date = hire_date;
	}
	@XmlAttribute
	public Date getBirth_date() {
		return birth_date;
	}
	@XmlAttribute
	public String getFirst_name() {
		return first_name;
	}
	@XmlAttribute
	public String getLast_name() {
		return last_name;
	}
	@XmlAttribute
	public Gender getGender() {
		return gender;
	}
	@XmlAttribute
	public Date getHire_date() {
		return hire_date;
	}
	
}
