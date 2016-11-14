package system.jpa;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Department {
	@Id
	private int id;
	private String name;
	
	@OneToMany(mappedBy="depart",targetEntity=Employee.class)
	private Collection employees;
	
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
}
