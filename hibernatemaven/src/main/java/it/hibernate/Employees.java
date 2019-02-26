package it.hibernate;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="employees", catalog="employees")
public class Employees
  implements Serializable
{
  private int empNo;
  private Date birthDate;
  private String firstName;
  private String lastName;
  private String gender;
  private Date hireDate;
  private Set<Titles> titleses = new HashSet(0);
  private Set<Salaries> salarieses = new HashSet(0);
  private Set<DeptEmp> deptEmps = new HashSet(0);
  private Set<DeptManager> deptManagers = new HashSet(0);
  
  public Employees() {}
  
  public Employees(int empNo, Date birthDate, String firstName, String lastName, String gender, Date hireDate)
  {
    this.empNo = empNo;
    this.birthDate = birthDate;
    this.firstName = firstName;
    this.lastName = lastName;
    this.gender = gender;
    this.hireDate = hireDate;
  }
  
  public Employees(int empNo, Date birthDate, String firstName, String lastName, String gender, Date hireDate, Set<Titles> titleses, Set<Salaries> salarieses, Set<DeptEmp> deptEmps, Set<DeptManager> deptManagers)
  {
    this.empNo = empNo;
    this.birthDate = birthDate;
    this.firstName = firstName;
    this.lastName = lastName;
    this.gender = gender;
    this.hireDate = hireDate;
    this.titleses = titleses;
    this.salarieses = salarieses;
    this.deptEmps = deptEmps;
    this.deptManagers = deptManagers;
  }
  
  @Id
  @Column(name="emp_no", unique=true, nullable=false)
  public int getEmpNo()
  {
    return empNo;
  }
  
  public void setEmpNo(int empNo) {
    this.empNo = empNo;
  }
  
  //@Temporal(TemporalType.DATE)
  @Column(name="birth_date", nullable=false, length=10)
  public Date getBirthDate() {
    return birthDate;
  }
  
  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }
  
  @Column(name="first_name", nullable=false, length=14)
  public String getFirstName() {
    return firstName;
  }
  
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  
  @Column(name="last_name", nullable=false, length=16)
  public String getLastName() {
    return lastName;
  }
  
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
  @Column(name="gender", nullable=false, length=2)
  public String getGender() {
    return gender;
  }
  
  public void setGender(String gender) {
    this.gender = gender;
  }
  
  //@Temporal(TemporalType.DATE)
  @Column(name="hire_date", nullable=false, length=10)
  public Date getHireDate() {
    return hireDate;
  }
  
  public void setHireDate(Date hireDate) {
    this.hireDate = hireDate;
  }
  
  @OneToMany(fetch=FetchType.LAZY, mappedBy="employees")
  public Set<Titles> getTitleses() {
    return titleses;
  }
  
  public void AddTitleses(Titles titleses) {
    this.titleses.add(titleses);
  }
  
  @OneToMany(fetch=FetchType.LAZY, mappedBy="employees")
  public Set<Salaries> getSalarieses() {
    return salarieses;
  }
  
  public void AddSalarieses(Salaries salarieses) {
    this.salarieses.add(salarieses);
  }
  
  @OneToMany(fetch=FetchType.LAZY, mappedBy="employees")
  public Set<DeptEmp> getDeptEmps() {
    return deptEmps;
  }
  
  public void AddDeptEmps(DeptEmp deptEmps) {
    this.deptEmps.add(deptEmps);
  }
  
  public void removeDeptEmps(DeptEmp deptEmps) { this.deptEmps.remove(deptEmps); }
  
  public void removeDeptManager(DeptManager deptm) {
    deptManagers.remove(deptm);
  }
  
  public void removeTitle(Titles t) { titleses.remove(t); }
  
  public void removeSalary(Salaries s) {
    salarieses.remove(s);
  }
  
  @OneToMany(fetch=FetchType.LAZY, mappedBy="employees")
  public Set<DeptManager> getDeptManagers() { return deptManagers; }
  
  public void AddDeptManagers(DeptManager deptManagers)
  {
    this.deptManagers.add(deptManagers);
  }
  
  public void setTitleses(Set<Titles> titleses) {
    this.titleses = titleses;
  }
  
  public void setSalarieses(Set<Salaries> salarieses) {
    this.salarieses = salarieses;
  }
  
  public void setDeptEmps(Set<DeptEmp> deptEmps) {
    this.deptEmps = deptEmps;
  }
  
  public void setDeptManagers(Set<DeptManager> deptManagers) {
    this.deptManagers = deptManagers;
  }
}
