package system.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestJPA {

	public static void main(String[] args) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("EmployeeService");
	}

}
