package it.hibernate;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;




//@Entity
//@Table(name="departments", catalog="employees", uniqueConstraints={@javax.persistence.UniqueConstraint(columnNames={"dept_name"})})
@XmlRootElement(name="Dipartimenti")
public class SimpleDepartment
  implements Serializable
{
  private String deptNo;
  private String deptName;
  

  public SimpleDepartment() {}
  
  public SimpleDepartment(String deptNo, String deptName)
  {
    this.deptNo = deptNo;
    this.deptName = deptName;
  }
  
  @XmlAttribute(name="DeptNo")
  public String getDeptNo() {
    return deptNo;
  }
  
  public void setDeptNo(String deptNo) {
    this.deptNo = deptNo;
  }
  @XmlAttribute(name="DeptName")
  public String getDeptName() {
    return deptName;
  }
  
  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }
}