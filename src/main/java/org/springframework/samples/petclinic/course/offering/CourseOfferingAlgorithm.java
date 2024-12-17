package org.springframework.samples.petclinic.course.offering;

import java.util.Set;

import org.springframework.samples.petclinic.course.Course;
import org.springframework.samples.petclinic.pet.PetType;

public interface CourseOfferingAlgorithm {
    Set<Course> selectCourses(Set<PetType> types);
}
