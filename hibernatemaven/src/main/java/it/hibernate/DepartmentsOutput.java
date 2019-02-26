package it.hibernate;

import java.io.Serializable;
import java.util.Set;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name="Dipartimenti")
public class DepartmentsOutput
  implements Serializable
{
  private String deptNo;
  private String deptName;
  private Set<DeptEmpOutput> deptEmps;
  private Set<DeptManOutput> deptMans;

  public DepartmentsOutput() {}
  
  public DepartmentsOutput(String deptNo, String deptName)
  {
    this.deptNo = deptNo;
    this.deptName = deptName;
  }
  
  public DepartmentsOutput(String deptNo, String deptName, Set<DeptEmpOutput> employeesDep, Set<DeptManOutput> employeesMan) {
    this.deptNo = deptNo;
    this.deptName = deptName;
    this.deptEmps = employeesDep;
    this.deptMans=employeesMan;
  }
  @XmlAttribute(name = "DeptNo")
  public String getDeptNo() {
    return deptNo;
  }
  
  public void setDeptNo(String deptNo) {
    this.deptNo = deptNo;
  }
  @XmlAttribute(name = "DeptName")
  public String getDeptName() {
    return deptName;
  }
  
  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }
  
  public void AddEmployeeDep(DeptEmpOutput de) {
    this.deptEmps.add(de);
  }
  

public Set<DeptEmpOutput> getDeptEmps() {
	return deptEmps;
}

public void setDeptEmps(Set<DeptEmpOutput> deptEmps) {
	this.deptEmps = deptEmps;
}

public Set<DeptManOutput> getDeptMans() {
	return deptMans;
}

public void setDeptMans(Set<DeptManOutput> deptMans) {
	this.deptMans = deptMans;
}

public void AddEmployeeMan(DeptManOutput dm) {
	this.deptMans.add(dm);
}
}
