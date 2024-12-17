package org.springframework.samples.petclinic.course.offering;

import java.util.Set;

import org.springframework.samples.petclinic.course.Course;
import org.springframework.samples.petclinic.pet.PetType;

public class DummyCourseOfferingAlgorithm implements CourseOfferingAlgorithm {

    @Override
    public Set<Course> selectCourses(Set<PetType> types) {
        return Set.of();
    }
    
}
