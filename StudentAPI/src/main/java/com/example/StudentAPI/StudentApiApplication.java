package com.example.StudentAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;



@SpringBootApplication
@RestController
@RequestMapping("/student")
public class StudentApiApplication {

	@PersistenceContext
	private EntityManager entity_manager;
	public static void main(String[] args) {
		SpringApplication.run(StudentApiApplication.class, args);
	}

	@GetMapping()
	public List<Student> getAllStudents() {
		return entity_manager.createQuery("SELECT s FROM Student s",Student.class).getResultList();
	}
	
	@GetMapping("/get/{id}")
	public Student getById(@PathVariable Long id) {
		Student existing_student = entity_manager.find(Student.class, id);
		if(existing_student==null) {
			throw new RuntimeException("Student Not Found");
		}
		return entity_manager.find(Student.class, id);
	}
	
	@Transactional
	@PostMapping("/create")
	public Student createStudent(@RequestBody Student s) {
		entity_manager.persist(s);
		return s;
	}
	
	@PutMapping("/update/{id}")
	@Transactional
	public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
    	Student existing_student = entity_manager.find(Student.class, id);
    	if (existing_student == null) {
        	throw new RuntimeException("Student Not Found");
    	}

		existing_student.setFirstname(student.getFirstname());
		existing_student.setLastname(student.getLastname());
		existing_student.setCourse(student.getCourse());

		return existing_student;
	}

	@Transactional
	@DeleteMapping("/remove/{id}")
	public String removeStudent(@PathVariable Long id) {
		Student existing_student = entity_manager.find(Student.class, id);
		if(existing_student==null) {
			throw new RuntimeException("Student Not Found");
		}
		entity_manager.remove(existing_student);
		return "Student record removed successfully";
	}
}
