package com.jpa.shipment.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.jpa.shipment.entity.Department;
import com.jpa.shipment.entity.Employee;
import com.jpa.shipment.utils.PropertyLoader;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaOrphanRemovalExample {
	public static void main(String[] args) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {

			PropertyLoader propertyLoader = new PropertyLoader();
			// Create an EntityManagerFactory and EntityManager
			emf = Persistence.createEntityManagerFactory("shippingPU",
					propertyLoader.loadProperties("application.properties"));

			em = emf.createEntityManager();
			// addData(em, emf);

			/*
			 * Optional<List<Employee>> test = getEmployeeWithDepartmentName("Engineering",
			 * em);
			 * 
			 * if (test.isPresent()) { for (Employee t : test.get()) {
			 * System.out.println(t.getName()); } }
			 */

			// List<Employee> testdata = em.find(Department.class, 5l).getEmployees();

			// retrieve all employees of a specific department

			// retriveve al departments along with their employees
			// List<Employee> testdata2 = em.find(Department.class,
			// Employee.class).getEmployees();
			
			Optional<List<Employee>> testdata2 = findAllEmps(em);
			Optional<List<Department>> testdataD = findAllDeps(em);
			if (testdata2.isPresent()) {
				testdata2.get().stream().forEach(e -> System.out
						.println("emp name->" + e.getName() + ",,,emp department->" + e.getDepartment().getName()));

				testdataD.get().stream().forEach(e -> {
					System.out.println("emp department->" + e);

					e.getEmployees().stream().forEach(j -> System.out.println("emp name->" + j.getName()));

				});
			}

			// findAllDepsOption1(em);
		} catch (Exception e) {

		} finally {
			emf.close();
			em.close();
		}
	}

	public static void addData(EntityManager em, EntityManagerFactory emf) {
		em.getTransaction().begin();

		// Define four types of departments
		String[] departmentTypes = { "Engineering", "Human Resources", "Marketing", "Sales" };

		// Create the departments and persist them
		List<Department> departments = new ArrayList<>();
		for (String deptName : departmentTypes) {
			Department department = new Department();
			department.setName(deptName);
			em.persist(department);
			departments.add(department);
		}

		// Create 20 employees and assign them to departments
		List<Employee> employees = new ArrayList<>();
		int employeeCounter = 1;

		for (int i = 0; i < 20; i++) {
			Employee employee = new Employee();
			employee.setName("Employee " + employeeCounter++);
			// Assign employees to departments cyclically
			Department assignedDepartment = departments.get(i % 4);
			employee.setDepartment(assignedDepartment);
			em.persist(employee); // Persist each employee
			employees.add(employee);

			// Add employee to the department's employee list
			if (assignedDepartment.getEmployees() == null) {
				assignedDepartment.setEmployees(new ArrayList<>());
			}
			assignedDepartment.getEmployees().add(employee);
		}

		em.getTransaction().commit();

		// Clean up resources
		em.close();
		emf.close();

		System.out.println("Successfully created 4 department types and 20 employees.");
	}

	public static Optional<List<Employee>> getEmployeeWithDepartmentName(String departmentName, EntityManager em) {
		// String departmentName = "Engineering"; // Example department name
		List<Employee> employees = em
				.createQuery("SELECT e FROM Employee e WHERE e.department.name = :departmentName", Employee.class)
				.setParameter("departmentName", departmentName).getResultList();
		if (!employees.isEmpty()) {
			return Optional.of(employees);
		} else {
			return Optional.empty();
		}
	}

	public static Optional<List<Employee>> findAllEmps(EntityManager em) {
		// String departmentName = "Engineering"; // Example department name
		List<Employee> employees = em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
		if (!employees.isEmpty()) {
			return Optional.of(employees);
		} else {
			return Optional.empty();
		}
	}

	public static Optional<List<Department>> findAllDeps(EntityManager em) {
		// String departmentName = "Engineering"; // Example department name
		long s = System.currentTimeMillis();
		List<Department> departments = em.createQuery("SELECT e FROM Department e", Department.class).getResultList();
		long e1 = System.currentTimeMillis();
		long elapsedTime = s - e1;
		System.out.println("time excuted" + elapsedTime);
		if (!departments.isEmpty()) {
			return Optional.of(departments);
		} else {
			return Optional.empty();
		}
	}

	public static void findAllDepsOption1(EntityManager em) {
		long s = System.currentTimeMillis();
		List<Department> departments = em
				.createQuery("SELECT d FROM Department d JOIN FETCH d.employees", Department.class).getResultList();
		long e1 = System.currentTimeMillis();
		long elapsedTime = s - e1;
		System.out.println("time excuted" + elapsedTime);
		departments.stream().forEach(e -> System.out.println(e));

	}
}
