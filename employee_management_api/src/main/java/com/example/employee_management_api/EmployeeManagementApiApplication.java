package com.example.employee_management_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@SpringBootApplication
@RestController
@RequestMapping("/employee")
public class EmployeeManagementApiApplication {

	@PersistenceContext
	private EntityManager entityManager;

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementApiApplication.class, args);
	}

	@GetMapping("/getAll")
	public List<employee> getAllEmployees() {
    return entityManager
            .createQuery("SELECT e FROM employee e", employee.class)
            .getResultList();
	}

	@GetMapping("/get/{id}")
	public employee getEmployeeId(@PathVariable Long id) {
		return entityManager.find(employee.class, id);
	}

	@Transactional
	@PostMapping("/create")
	public employee createEmployee(@RequestBody employee e) {
		entityManager.persist(e);
		return e;
	}
	
	@Transactional
	@PutMapping("/update/{id}")
	public employee updateEmployeeDetails(@PathVariable Long id, @RequestBody employee e) {
		employee existing_employee = entityManager.find(employee.class, id);
		if(existing_employee==null) {
			throw new RuntimeException("Employee Not Found");
		}
		existing_employee.setFirstName(e.getFirstName());
		existing_employee.setLastName(e.getLastName());
		existing_employee.setGender(e.getGender());
		existing_employee.setDept(e.getDept());
		existing_employee.setAge(e.getAge());

		return existing_employee;
	}

	@Transactional
	@DeleteMapping("/remove/{id}")
	public String removeEmployee(@PathVariable Long id) {
		employee existing_employee = entityManager.find(employee.class, id);
		if(existing_employee==null) {
			throw new RuntimeException("Employee Not Found");
		}
		entityManager.remove(existing_employee);
		return "Employee record removed successfully!";
	}
}
