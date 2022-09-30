package com.example.controller;

import com.example.exception.ResourceNotFoundException;
import com.example.model.*;
import com.example.repo.CourseRepository;
import com.example.repo.InstituteRepository;
import com.example.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class AdminController {
    @Autowired
    private InstituteRepository instituteRepo;

    @Autowired
    private CourseRepository coursesRepo;
    @Autowired
    private StudentRepository studentRepo;

    public AdminController() {
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/institute")
    public ResponseEntity<List<Institute>> getAllTutorials(@RequestParam(required = false) String title) {
        List<Institute> institutes = new ArrayList<Institute>();

        if (title == null)
            instituteRepo.findAll().forEach(institutes::add);
        else
            instituteRepo.findByInstitutenameContaining(title).forEach(institutes::add);

        if (institutes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(institutes, HttpStatus.OK);
    }

    @GetMapping("/institute/{id}")
    public ResponseEntity<Institute> getinstituteById(@PathVariable(value = "id") int instituteid)
            throws ResourceNotFoundException {
        Institute in = instituteRepo.findById(instituteid)
                .orElseThrow(() -> new ResourceNotFoundException("institute not found for this id :: " + instituteid));
        return new ResponseEntity<>(in, HttpStatus.OK);
    }

    @PostMapping("/institute")
    public Institute createinstitute(@Valid @RequestBody Institute in) {
        return instituteRepo.save(in);
    }

    @PutMapping("/institute/{id}")
    public ResponseEntity<Institute> updateinstitute(@PathVariable(value = "id") int instituteid,
                                                     @Valid @RequestBody Institute institutedetails) throws ResourceNotFoundException {
        Institute in = instituteRepo.findById(instituteid)
                .orElseThrow(() -> new ResourceNotFoundException("institute not found for this id :: " + instituteid));
        in.setInstitutename(institutedetails.getInstitutename());
        in.setInstitutedescription(institutedetails.getInstitutedescription());
        in.setInstituteaddress(institutedetails.getInstituteaddress());
        in.setMobile(institutedetails.getMobile());
        in.setEmail(institutedetails.getEmail());
        in.setCourses(institutedetails.getCourses());
        final Institute updateinstitute  = instituteRepo.save(in);
        return ResponseEntity.ok(updateinstitute);
    }
    @DeleteMapping("/institute/{id}")
    public Map<String, Boolean> deleteinstitute(@PathVariable(value = "id") int instituteid)
            throws ResourceNotFoundException {
        Institute in = instituteRepo.findById(instituteid)
                .orElseThrow(() -> new ResourceNotFoundException("institute not found for this id :: " + instituteid));

        instituteRepo.delete(in);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping("/courses")
    public courseDao getAllcourses() {
         courseDao cus = new courseDao();
         cus.setCoursesDetails(coursesRepo.findAll());
        return cus;
    }
    @GetMapping("/institute/{id}/course")
    public ResponseEntity<List<Course>> getAllCommentsByTutorialId(@PathVariable(value = "instituteid") int instituteid) throws ResourceNotFoundException {
        Institute in = instituteRepo.findById(instituteid)
                .orElseThrow(() -> new ResourceNotFoundException("Not found institute with id = " + instituteid));

        List<Course> courses = new ArrayList<Course>();
        courses.addAll(in.getCourses());

        return new ResponseEntity<>(courses, HttpStatus.OK);
    }
    @GetMapping("/course/{id}")
    public ResponseEntity<Course> getcoursesById(@PathVariable(value = "id") int courseid)
            throws ResourceNotFoundException {
        Course c = coursesRepo.findById(courseid)
                .orElseThrow(() -> new ResourceNotFoundException("course not found for this id :: " + courseid));
        return ResponseEntity.ok().body(c);
    }
    @PostMapping("/institute/{id}/course")
    public Course createcourse(@Valid @PathVariable(value = "id") int instituteid ,@RequestBody Course c)  throws ResourceNotFoundException{

        Course courses = instituteRepo.findById(instituteid).map(institute -> {
            institute.getCourses().add(c);
            return coursesRepo.save(c);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + instituteid));
        return courses;
    }
    @PutMapping("/course/{id}")
    public ResponseEntity<Course> updatecourse(@PathVariable(value = "id") int courseid,
                                               @Valid @RequestBody Course coursesdetails) throws ResourceNotFoundException {
        Course c = coursesRepo.findById(courseid)
                .orElseThrow(() -> new ResourceNotFoundException("course not found for this id :: " + courseid));
        c.setCoursename(coursesdetails.getCoursename());
        c.setCoursedescription(coursesdetails.getCoursedescription());
        c.setCourseduration(coursesdetails.getCourseduration());
        c.setCoursetime(coursesdetails.getCoursetime());
        c.setNostudents(coursesdetails.getNostudents());
        final Course updatecourses = coursesRepo.save(c);
        return ResponseEntity.ok(updatecourses);
    }
    @DeleteMapping("/course/{id}")
    public Map<String, Boolean> deletecourse(@PathVariable(value = "id") int courseid)
            throws ResourceNotFoundException {
        Course c = coursesRepo.findById(courseid)
                .orElseThrow(() -> new ResourceNotFoundException("course not found for this id :: " + courseid));

        coursesRepo.delete(c);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping("/student")
    public Iterable<Student> getAllstudents() {
        return  studentRepo.findAll();
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getEmployeeById(@PathVariable(value = "id") int studentid)
            throws ResourceNotFoundException {
        Student std = studentRepo.findById(studentid)
                .orElseThrow(() -> new ResourceNotFoundException("student not found for this id :: " + studentid));
        return ResponseEntity.ok().body(std);
    }


    @PostMapping("/course/{id}/student")
    public Student createstudent(@Valid @PathVariable(value = "id") int courseid ,@RequestBody Student std) throws ResourceNotFoundException {
        Student s = coursesRepo.findById(courseid).map(course -> {
           // Adress adress=new Adress(adress.setHouseno(),adress.setStreatname(););
            course.getStudents().add(std);
            return studentRepo.save(std);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found course with id = " + courseid));
        return s;
    }


    @PutMapping("/student/{id}")
    public ResponseEntity<Student> updatestudent(@PathVariable(value = "id") int studentid,
                                                  @Valid @RequestBody Student studentdetails) throws ResourceNotFoundException {
        Student std = studentRepo.findById( studentid)
                .orElseThrow(() -> new ResourceNotFoundException("student not found for this id :: " + studentid));
        std.setFirstname(studentdetails.getFirstname());
        std.setLastname(studentdetails.getLastname());
        std.setHouseno(studentdetails.getHouseno());
        std.setAreaname(studentdetails.getAreaname());
        std.setPincode(studentdetails.getPincode());
        std.setState(studentdetails.getState());
        std.setNationality(studentdetails.getNationality());
        std.setPhonenumber(studentdetails.getPhonenumber());
        std.setFathername(studentdetails.getFathername());
        std.setMothername(studentdetails.getMothername());
        std.setAlternativephonenumber(studentdetails.getAlternativephonenumber());
        std.setAge(studentdetails.getAge());
        std.setStreetname(studentdetails.getStreetname());
        std.setEmailid(studentdetails.getEmailid());
        final Student updatestudent = studentRepo.save(std);
        return ResponseEntity.ok(updatestudent);
    }

    @DeleteMapping("/student/{id}")
    public Map<String, Boolean> deletestudent(@PathVariable(value = "id") int studentid)
            throws ResourceNotFoundException {
        Student std = studentRepo.findById(studentid)
                .orElseThrow(() -> new ResourceNotFoundException("student not found for this id :: " + studentid));

        studentRepo.delete(std);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
