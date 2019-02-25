package it.hibernate;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;




@Entity
@Table(name="departments", catalog="employees", uniqueConstraints={@javax.persistence.UniqueConstraint(columnNames={"dept_name"})})
public class Departments
  implements Serializable
{
  private String deptNo;
  private String deptName;
  private Set<DeptEmp> deptEmps = new HashSet(0);
  private Set<DeptManager> deptManagers = new HashSet(0);
  

  public Departments() {}
  
  public Departments(String deptNo, String deptName)
  {
    this.deptNo = deptNo;
    this.deptName = deptName;
  }
  
  public Departments(String deptNo, String deptName, Set<DeptEmp> deptEmps, Set<DeptManager> deptManagers) {
    this.deptNo = deptNo;
    this.deptName = deptName;
    this.deptEmps = deptEmps;
    this.deptManagers = deptManagers;
  }
  
  @Id
  @Column(name="dept_no", unique=true, nullable=false, length=4)
  public String getDeptNo() {
    return deptNo;
  }
  
  public void setDeptNo(String deptNo) {
    this.deptNo = deptNo;
  }
  
  @Column(name="dept_name", unique=true, nullable=false, length=40)
  public String getDeptName() {
    return deptName;
  }
  
  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }
  
  @OneToMany(fetch=FetchType.LAZY, mappedBy="departments")
  public Set<DeptEmp> getDeptEmps() {
    return deptEmps;
  }
  
  public void AddDeptEmps(DeptEmp deptEmps) {
    this.deptEmps.add(deptEmps);
  }
  
  @OneToMany(fetch=FetchType.LAZY, mappedBy="departments")
  public Set<DeptManager> getDeptManagers() {
    return deptManagers;
  }
  
  public void AddDeptManagers(DeptManager deptManagers) {
    this.deptManagers.add(deptManagers);
  }
  
  public void setDeptEmps(Set<DeptEmp> deptEmps) {
    this.deptEmps = deptEmps;
  }
  
  public void setDeptManagers(Set<DeptManager> deptManagers) {
    this.deptManagers = deptManagers;
  }
  public void removeDeptEmps(DeptEmp d) {
	  this.deptEmps.remove(d);
  }
  public void removeDeptMan(DeptManager d) {
	  this.deptManagers.remove(d);
  }
}
