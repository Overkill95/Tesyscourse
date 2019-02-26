package it.hibernate;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name="Impiegati")
public class EmployeesOutput
  implements Serializable
{
  private Integer empNo;
  private Date birthDate;
  private String firstName;
  private String lastName;
  private String gender;
  private Date hireDate;
  private Set<DeptEmpOutput> Deps;
  private Set <DeptManOutput> Mans;

  public EmployeesOutput() {}
  
  public EmployeesOutput(Integer empNo, Date birthDate, String firstName, String lastName, String gender, Date hireDate )
  {
    this.empNo=empNo;
    this.birthDate=birthDate;
    this.firstName=firstName;
    this.lastName=lastName;
    this.gender=gender;
    this.hireDate=hireDate;
  }
  
  public EmployeesOutput(Integer empNo, Date birthDate, String firstName, String lastName, String gender, Date hireDate, Set<DeptEmpOutput> Deps, Set<DeptManOutput> Mans) {
	  this.empNo=empNo;
	    this.birthDate=birthDate;
	    this.firstName=firstName;
	    this.lastName=lastName;
	    this.gender=gender;
	    this.hireDate=hireDate;
	    this.Deps=Deps;
	    this.Mans=Mans;
  }
  
  @XmlAttribute(name="EmpNo")
  public Integer getEmpNo() {
	return empNo;
}

public void setEmpNo(Integer empNo) {
	this.empNo = empNo;
}
@XmlAttribute(name="birthDate")
public Date getBirthDate() {
	return birthDate;
}

public void setBirthDate(Date birthDate) {
	this.birthDate = birthDate;
}
@XmlAttribute(name="firstName")
public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}
@XmlAttribute(name="lastName")
public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}
@XmlAttribute(name="gender")
public String getGender() {
	return gender;
}

public void setGender(String gender) {
	this.gender = gender;
}
@XmlAttribute(name="HireDate")
public Date getHireDate() {
	return hireDate;
}

public void setHireDate(Date hireDate) {
	this.hireDate = hireDate;
}

public void AddEmployeeDep(DeptEmpOutput de) {
    this.Deps.add(de);
  }
  

public Set<DeptEmpOutput> getDeptEmps() {
	return Deps;
}

public void setDeptEmps(Set<DeptEmpOutput> deptEmps) {
	this.Deps = deptEmps;
}

public Set<DeptManOutput> getDeptMans() {
	return Mans;
}

public void setDeptMans(Set<DeptManOutput> deptMans) {
	this.Mans = deptMans;
}

public void AddEmployeeMan(DeptManOutput dm) {
	this.Mans.add(dm);
}
}