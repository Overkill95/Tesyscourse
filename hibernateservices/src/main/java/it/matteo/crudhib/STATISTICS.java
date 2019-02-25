package it.matteo.crudhib;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import it.hibernate.DepCountOutput;
import it.hibernate.DepManInfoOutput;
import it.hibernate.DepartmentsHome;
import it.hibernate.EmployeesHome;
import it.hibernate.ManCountOutput;
import it.hibernate.ManSalaryTitleOutput;
import it.hibernate.MaxMinSalaryOutput;
import it.hibernate.RangeDistributionOutput;
import it.hibernate.SalariesHome;
import it.hibernate.SalaryCountOutput;
import it.hibernate.TitleCountOutput;
import it.hibernate.TitlesHome;
import it.hibernate.genderEmployeesOutput;

@WebService(serviceName="StatisticsServ", name="StatisticServ",portName="StatisticServPortType", targetNamespace="http://StatisticServ.it")
public class STATISTICS {
	@WebMethod
	@WebResult(name="StatisticsGenderEmployee",
    targetNamespace="http://StatisticServ.it/StatisticsGenderEmployee")
	public List<genderEmployeesOutput> genderEmployees(){
		EmployeesHome ed=new EmployeesHome();
		return ed.genderEmployees();
	}
	@WebMethod
	@WebResult(name="StatisticsMaxMinSalary",
    targetNamespace="http://StatisticServ.it/StatisticsMaxMinSalary")
	public List<MaxMinSalaryOutput> MaxMinSalaryOutput(){
		SalariesHome sh=new SalariesHome();
		return sh.MaxMinSalary();
	}
	
	@WebMethod
	@WebResult(name="StatisticsSalaryCount",
    targetNamespace="http://StatisticServ.it/StatisticsSalaryCount")
	public List<SalaryCountOutput> SalaryCount(){
		SalariesHome sh=new SalariesHome();
		return sh.SalaryCount();
	}
	
	@WebMethod
	@WebResult(name="TitlesCount",
    targetNamespace="http://StatisticServ.it/StatisticsTitlesCount")
	public List<TitleCountOutput> TitleCount(){
		TitlesHome th=new TitlesHome();
		return th.TitleCount();
	}
	
	@WebMethod
	@WebResult(name="DepCount",
    targetNamespace="http://StatisticServ.it/StatisticsDepCount")
	public List<DepCountOutput> DepCount(){
		DepartmentsHome dh=new DepartmentsHome();
		return dh.DepCount();
	}
	
	@WebMethod
	@WebResult(name="ManCount",
    targetNamespace="http://StatisticServ.it/StatisticsManCount")
	public List<ManCountOutput> ManCount(){
		DepartmentsHome dh=new DepartmentsHome();
		return dh.ManCount();
	}
	
	
	@WebMethod
	@WebResult(name="ManSalaryTitleCount",
    targetNamespace="http://StatisticServ.it/ManSalaryTitleCount")
	public List<ManSalaryTitleOutput> ManSalaryTitleCount(){
		EmployeesHome eh=new EmployeesHome();
		return eh.ManSalaryTitleCount();
	}
	//join fra deptEmp e deptMan
	@WebMethod
	@WebResult(name="DepManEmployeeInfo",
    targetNamespace="http://StatisticServ.it/DepManEmployeeInfo")
	public List<DepManInfoOutput> DepManEmployeeInfo(@WebParam(name="emp_no")Integer emp_no){
		EmployeesHome eh=new EmployeesHome();
		return eh.DepManEmployeeInfo(emp_no);
	}
	
	@WebMethod
	@WebResult(name="RangeDistributionInfo",
    targetNamespace="http://StatisticServ.it/RangeDistributionInfo")
	public List<RangeDistributionOutput> RangeDistributionInfo(){
		EmployeesHome eh=new EmployeesHome();
		return eh.RangeDistributionInfo();
	}
}
