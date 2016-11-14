package system.jpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "emp")
public class Employee {

	@Column(name = "EMP_ID")
	@TableGenerator(name = "empGen", table = "ID_GEN", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "EMP_ID", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "empGen")
	private int id;
	private String name;
	@Column(name = "SAL")
	private long salary;
	@Column(name = "COMM")
	private String comments;
	@Lob
	@Column(name = "pic")
	@Basic(fetch = FetchType.LAZY)
	private byte[] picture;

	@ManyToOne
	@JoinColumn(name = "departid")
	private Department depart;

	@OneToOne
	@JoinColumn(name = "pspaceid")
	private ParkingSpace parkingSpace;

	@ManyToMany
	@JoinTable(name = "emp_proj", joinColumns = @JoinColumn(name = "empid") , inverseJoinColumns = @JoinColumn(name = "projid") )
	private Collection<Project> projects = new ArrayList<Project>();

	@Embedded
	private Address address;

	@ElementCollection(targetClass = VacationEntry.class)
	private Collection vacationBookings = new ArrayList();

	@ElementCollection
	private Set<String> nickNames = new HashSet<String>();

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Collection<Project> getProjects() {
		return projects;
	}

	public void setProjects(Collection<Project> projects) {
		this.projects = projects;
	}

	public ParkingSpace getParkingSpace() {
		return parkingSpace;
	}

	public void setParkingSpace(ParkingSpace parkingSpace) {
		this.parkingSpace = parkingSpace;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public Department getDepart() {
		return depart;
	}

	public void setDepart(Department depart) {
		this.depart = depart;
	}

	public Employee() {

	}

	public Employee(int id) {
		// this.id = id;
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

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	public Collection getVacationBookings() {
		return vacationBookings;
	}

	public void setVacationBookings(Collection vacationBookings) {
		this.vacationBookings = vacationBookings;
	}

	public Set<String> getNickNames() {
		return nickNames;
	}

	public void setNickNames(Set<String> nickNames) {
		this.nickNames = nickNames;
	}
}
