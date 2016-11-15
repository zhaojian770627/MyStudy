package system.jpa;

import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class EmployeeService {
	protected EntityManager em;

	public EmployeeService(EntityManager em) {
		this.em = em;
	}

	public Employee createEmployee(int id, String name, long salary) {
		Employee emp = new Employee(id);
		emp.setName(name);
		emp.setSalary(salary);
		em.persist(emp);
		return emp;
	}

	public void removeEmployee(int id) {
		Employee emp = findEmployee(id);
		if (emp != null)
			em.remove(emp);
	}

	public Employee raiseEmployeeSalary(int id, long raise) {
		Employee emp = new Employee(id);
		if (emp != null)
			emp.setSalary(emp.getSalary() + raise);
		return emp;
	}

	public Employee findEmployee(int id) {
		return em.find(Employee.class, id);
	}
	
	public Department findDepartment(int id) {
		return em.find(Department.class, id);
	}


	public List<Employee> findAllEmployees() {
		TypedQuery<Employee> query = em.createQuery("select e from Employee e", Employee.class);
		return query.getResultList();
	}

	public Department crateDepartment(int id, String name) {
		Department depart = new Department(id, name);
		em.persist(depart);
		return depart;
	}
	
	public void updateDepartment(Department depart,Employee e) {
		depart.getMemployees().put(1, e);
		em.persist(depart);
	}

	public Employee createEmployee(int id, String name, long salary, Department depart, ParkingSpace ps, Project pj) {
		Employee emp = new Employee(id);
		emp.setName(name);
		emp.setSalary(salary);
		emp.setDepart(depart);
		depart.getEmployees().add(emp);
		emp.setParkingSpace(ps);
		emp.getProjects().add(pj);
		Address ad = new Address();
		ad.setCity("feicheng");
		ad.setState("shandong");
		ad.setStreet("ab");
		emp.setAddress(ad);

		// 增加可嵌入的类
		emp.getVacationBookings().add(new VacationEntry(new GregorianCalendar(), 2));
		emp.getVacationBookings().add(new VacationEntry(new GregorianCalendar(), 3));

		emp.getNickNames().add("a");
		emp.getNickNames().add("b");

		emp.getPhoneNumbers().put(PhoneType.Home, "123456");
		em.persist(emp);
		//em.persist(depart);
		return emp;
	}

	public ParkingSpace createParkingSpace(int id, String location) {
		ParkingSpace ps = new ParkingSpace(id, location);
		em.persist(ps);
		return ps;
	}

	public Project createProject(int id, String name) {
		Project pj = new Project(id, name);
		em.persist(pj);
		return pj;
	}
}
