package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
//			createStudent(studentDAO);
			readStudent(studentDAO);
			queryForAllStudents(studentDAO);
			queryForStudentLastName(studentDAO);
		};
	}

	private void queryForStudentLastName(StudentDAO studentDAO) {
//		List<Student> theStudents = studentDAO.findByLastName("Owen");
		System.out.print("Last name that you're looking for: ");
		Scanner scanner = new Scanner(System.in);
		String lastName = scanner.nextLine();
		List<Student> theStudents = studentDAO.findByLastName(lastName);
		if (!theStudents.isEmpty()) {
			for (Student tempStudent: theStudents) {
				System.out.println(tempStudent);
			}
		}

	}

	private void queryForAllStudents(StudentDAO studentDAO) {
		List<Student> theStudents = studentDAO.findAll();
		for (Student tempStudent: theStudents) {
			System.out.println(tempStudent);
		}
	}

	private void createStudent(StudentDAO studentDAO) {
		// create the student object
		System.out.println("Creating new student object ... ");
		Student tempStudent = new Student("Paul", "Doe", "paul@luv2code.com");

		// save the student object
		System.out.println("Saving the student ... ");
		studentDAO.save(tempStudent);

		// display the student object
		System.out.println("Saved student. Generated id: " + tempStudent.getId());
	}

	private void readStudent(StudentDAO studentDAO) {
		// retrieve student based on the id : primary key
		System.out.print("Student id that you're looking for: ");
		Scanner scanner = new Scanner(System.in);
		int studentId = scanner.nextInt();
		System.out.println("Displaying student with id of '"+ studentId +"'");

		Optional<Student> myStudent = studentDAO.findById(studentId);
//		Optional<Student> myStudent = studentDAO.findById(2);

		if (myStudent.isPresent()) {
			System.out.println("Found the student: " + myStudent.get() + "\n");
		} else {
			System.out.println("Sorry, no student was found");
		}

	}


}
