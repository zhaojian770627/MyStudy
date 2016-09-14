package system.jpa;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
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

}
