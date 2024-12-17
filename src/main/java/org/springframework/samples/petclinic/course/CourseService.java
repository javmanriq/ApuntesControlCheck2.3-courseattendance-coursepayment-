package org.springframework.samples.petclinic.course;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseService {

    CourseRepository repo;
    
    @Autowired    
    public CourseService(CourseRepository repo){
        this.repo=repo;
    }
    
    @Transactional(readOnly = true)
    public Course findCourseById(int i) {
        Optional<Course> od=repo.findById(i);
        return od.isPresent()?od.get():null;
    }
    

    @Transactional(readOnly = true)
    public List<Course> findCourses() {
        return repo.findAll();
    }
}
