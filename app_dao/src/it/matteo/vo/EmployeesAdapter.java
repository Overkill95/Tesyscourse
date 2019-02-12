package it.matteo.vo;

import javax.xml.bind.annotation.XmlAttribute;

import it.matteo.utils.InvalidInputException;
import javax.xml.bind.annotation.adapters.XmlAdapter;
public class EmployeesAdapter extends XmlAdapter<EmployeesAdapter, Employees> {
	private int empt_no;
	private String birth_date;
	private String first_name;
	private String last_name;
	private Gender gender;
	private String hire_date;
	public int getEmpt_no() {
		return empt_no;
	}
	public void setEmpt_no(int empt_no){
		this.empt_no = empt_no;
	}
	
	public void setBirth_date(String birth_date) throws InvalidInputException {
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

	public void setHire_date(String hire_date) throws InvalidInputException {
		if(hire_date == null) throw new InvalidInputException("Input non valido");
		this.hire_date = hire_date;
	}
	public String getBirth_date() {
		return birth_date;
	}
	public String getFirst_name() {
		return first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public Gender getGender() {
		return gender;
	}
	public String getHire_date() {
		return hire_date;
	}
	@Override
	public EmployeesAdapter marshal(Employees arg0) throws Exception {
		// TODO Auto-generated method stub
		EmployeesAdapter ed=new EmployeesAdapter();
		ed.setEmpt_no(arg0.getEmpt_no());
		ed.setBirth_date(arg0.getBirth_date());
		ed.setFirst_name(arg0.getFirst_name());
		ed.setLast_name(arg0.getLast_name());
		ed.setGender(arg0.getGender());
		ed.setHire_date(arg0.getHire_date());
		return ed;
	}
	@Override
	public Employees unmarshal(EmployeesAdapter arg0) throws Exception {
		// TODO Auto-generated method stub
		return new Employees(arg0.getEmpt_no(), arg0.getBirth_date(), arg0.getFirst_name(), arg0.getLast_name(), arg0.getGender().toString(), arg0.getHire_date());
	}
}
