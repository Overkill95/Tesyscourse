package it.hibernate;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="Impiegati")
public class SimpleEmployee {
	 private int empNo;
	  private Date birthDate;
	  private String firstName;
	  private String lastName;
	  private String gender;
	  private Date hireDate;
	  
	  public SimpleEmployee() {}
	  
	  public SimpleEmployee(int empNo, Date birthDate, String firstName, String lastName, String gender, Date hireDate)
	  {
	    this.empNo = empNo;
	    this.birthDate = birthDate;
	    this.firstName = firstName;
	    this.lastName = lastName;
	    this.gender = gender;
	    this.hireDate = hireDate;
	  }
	  @XmlAttribute(name = "EmpNo")
	  public int getEmpNo()
	  {
	    return empNo;
	  }
	  
	  public void setEmpNo(int empNo) {
	    this.empNo = empNo;
	  }
	  @XmlAttribute(name = "DataNascita")
	  public Date getBirthDate() {
	    return birthDate;
	  }
	  
	  public void setBirthDate(Date birthDate) {
	    this.birthDate = birthDate;
	  }
	  @XmlAttribute(name = "Nome")
	  public String getFirstName() {
	    return firstName;
	  }
	  
	  public void setFirstName(String firstName) {
	    this.firstName = firstName;
	  }
	  @XmlAttribute(name = "Cognome")
	  public String getLastName() {
	    return lastName;
	  }
	  
	  public void setLastName(String lastName) {
	    this.lastName = lastName;
	  }
	  @XmlAttribute(name = "Sesso")
	  public String getGender() {
	    return gender;
	  }
	  
	  public void setGender(String gender) {
	    this.gender = gender;
	  }
	  @XmlAttribute(name = "DataAssunzione")
	  public Date getHireDate() {
	    return hireDate;
	  }
	  
	  public void setHireDate(Date hireDate) {
	    this.hireDate = hireDate;
	  }
}
