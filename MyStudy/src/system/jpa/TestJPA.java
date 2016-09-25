package system.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

public class TestJPA {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeService");
		EntityManager em = emf.createEntityManager();
		EmployeeService service = new EmployeeService(em);

		// Create and persist an employee
		em.getTransaction().begin();
		Department depart=service.crateDepartment(1,"compute");
		ParkingSpace ps=service.createParkingSpace(1,"W");
		Employee emp = service.createEmployee(158, "John Doe", 45000,depart,ps);
		em.getTransaction().commit();
		System.out.println("Persisted " + emp);
		int pk = emp.getId();
		System.out.println("PK " + pk);

		// find a specitic employee
		emp = service.findEmployee(pk);
		System.out.println("Found " + emp);

		// find all employees
		List<Employee> emps = service.findAllEmployees();
		for (Employee e : emps)
			System.out.println("Found employee: " + e);

		// update the employee
		em.getTransaction().begin();
		emp = service.raiseEmployeeSalary(pk, 1000);
		em.getTransaction().commit();
		System.out.println("Updated " + emp);
		
		// remove an employee
		em.getTransaction().begin();
		//service.removeEmployee(pk);
		em.getTransaction().commit();
		System.out.println("Removed Employee " + pk);

		// close the EM and EMF when done
		em.close();
		emf.close();
	}
	
	@Test
	public void testGetEmp()
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeService");
		EntityManager em = emf.createEntityManager();
		EmployeeService service = new EmployeeService(em);
		
		Employee emp = service.findEmployee(1);
		System.out.println(emp.getParkingSpace().getLocation());
		System.out.println(emp.getDepart().getEmployees().iterator().next().toString());
		em.close();
		emf.close();
	}
}
