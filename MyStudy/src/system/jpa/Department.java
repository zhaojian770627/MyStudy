package system.jpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

@Entity
public class Department {
	@Id
	private int id;
	private String name;
	
	@OneToMany(mappedBy="depart",targetEntity=Employee.class)
	private Collection employees=new ArrayList();
	
	@OneToMany(mappedBy="depart")
	@MapKey(name="id")
	private Map<Integer,Employee> memployees=new HashMap<Integer,Employee>();
	
	public Department()
	{
		
	}

	public Department(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Collection getEmployees() {
		return employees;
	}

	public void setEmployees(Collection employees) {
		this.employees = employees;
	}

	public Map<Integer, Employee> getMemployees() {
		return memployees;
	}

	public void setMemployees(Map<Integer, Employee> memployees) {
		this.memployees = memployees;
	}
	
	
}
