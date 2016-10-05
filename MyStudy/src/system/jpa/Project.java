package system.jpa;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Project {
	@Id
	private int id;

	private String name;

	@ManyToMany(mappedBy = "projects")
	private Collection<Employee> employees;

	public Project(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Collection<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Collection<Employee> employees) {
		this.employees = employees;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
