package com.training.courseservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.courseservice.intercomm.StudentClient;
import com.training.courseservice.model.Course;
import com.training.courseservice.model.Student;
import com.training.courseservice.repository.CourseRepository;

@RestController //Combination of @Controller+@ResponseBody
@RequestMapping("/courseapi")
public class CourseController {

	@Autowired
	CourseRepository repository;
	
	@Autowired
	StudentClient studClient;
	
//	@GetMapping("/course")
////	@ResponseBody
//	public List<Course> getStud() {
//		
//		return repository.findAll();
////		List<Course> courseList = new ArrayList<>();
////		courseList.add(new Course("Java",21));
////		courseList.add(new Course("Python",22));
////		courseList.add(new Course("French",23));
////		courseList.add(new Course("Sanskrit",24));
////	    return courseList;
//	}
//	
	
	
	//ResponseEntity : is a class which returns the http status code along with 
	@GetMapping("/course")
	public ResponseEntity<List<Course>> getStud() {	
		List<Course> courseList = repository.findAll();
		return new ResponseEntity<>(courseList,HttpStatus.OK);
		
	}
	
//	@PostMapping("/post")
//	public void post(@RequestBody List<Course> course) {
//		return new ResponseEntity(repository.save(course),HttpStatus.OK);
//		repository.saveAll(course);
//	}
	@PostMapping("/post")
	public ResponseEntity post(@RequestBody Course course) {
		return new ResponseEntity(repository.save(course),HttpStatus.CREATED);
	}
	
	@GetMapping("/service/stud/{studId}")
	public Student getStud(@PathVariable int studId) {
		return studClient.getStudent(studId);
	}
	
	
	@GetMapping("/Course/{id}")
	public Course getCourseById(@PathVariable("id") long id) {
		
		Optional<Course> stud = repository.findById(id);
		
		if(stud.isPresent()) {
			return stud.get();
		}
		else {
			return null;
		}
	}
	
	@PutMapping("/Course/{id}")
	public void updateUser(@PathVariable("id") long id, @RequestBody Course course) {
		
		Optional<Course> oldData = repository.findById(id);
		if(oldData.isPresent()) {
			Course std = oldData.get();
			std.setDuration(course.getDuration());
			std.setCourseName(course.getCourseName());
			repository.save(std);
		}
		else {
			System.out.println("No Data Found");
		}
		
	}
	
}
