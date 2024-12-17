package org.springframework.samples.petclinic.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.exceptions.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseService sts;

    @Autowired
    public CourseController(CourseService sts) {
        this.sts = sts;
    }

    @GetMapping
    public List<Course> findAll() {
        return sts.findCourses();
    }
    
    @GetMapping("/{id}")
    public Course getSurgeryType(@PathVariable("id") Integer id) {
        Course st = sts.findCourseById(id);
        if(st==null){
            throw new ResourceNotFoundException("No encuentro el course que me ha pedido con id: " + id);
        }
        return st;
    }
}
