package system.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ParkingSpace {
	@Id
	private int id;
	private String location;
	
	@OneToOne(mappedBy="parkingSpace")
	private Employee employee;
	
	public ParkingSpace()
	{
		
	}
	
	public ParkingSpace(int id, String location) {
		this.id=id;
		this.location=location;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
