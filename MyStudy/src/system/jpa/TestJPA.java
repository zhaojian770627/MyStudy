package system.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestJPA {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeService");
		EntityManager em = emf.createEntityManager();
		EmployeeService service = new EmployeeService(em);

		// Create and persist an employee
		em.getTransaction().begin();
		Employee emp = service.createEmployee(158, "John Doe", 45000);
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
//		em.getTransaction().begin();
//		service.removeEmployee(pk);
//		em.getTransaction().commit();
//		System.out.println("Removed Employee " + pk);

		// close the EM and EMF when done
		em.close();
		emf.close();
	}

}
