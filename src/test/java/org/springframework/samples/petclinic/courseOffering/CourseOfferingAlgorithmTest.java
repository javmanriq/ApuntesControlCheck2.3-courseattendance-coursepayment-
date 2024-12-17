package org.springframework.samples.petclinic.courseOffering;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.samples.petclinic.course.Course;
import org.springframework.samples.petclinic.course.CourseAttendance;
import org.springframework.samples.petclinic.course.offering.CourseOfferingAlgorithm;
import org.springframework.samples.petclinic.course.offering.ValidCourseOfferingAlgorithm;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.PetType;
import org.springframework.samples.petclinic.vet.Vet;
import org.springframework.samples.petclinic.visit.Visit;

public class CourseOfferingAlgorithmTest {
    // This is your SUT:
    CourseOfferingAlgorithm offeringAlgorithm = new ValidCourseOfferingAlgorithm();
    
    // We provide three examples of pet types to ease your work:
    PetType turtle=createValidPetType("turtle");
    PetType cat=createValidPetType("cat");
    PetType lion=createValidPetType("lion");

    // We provide three examples of courses to ease your work:
    Course ninjaInfiltration=createCourse(4, "Ninja infiltration",4,Set.of(turtle));
    Course safeGrooming = createCourse(5, "Safe grooming", 3, Set.of(cat,lion));
    Course splinterRemoval = createCourse(6, "Splinter removal", 5, Set.of(turtle, cat));
   
    // Please specify as many tests as you need using structures similar to this:
    /* 
    @Test
    public void someTest(){
        // Arrangement / Configuration /Fixture 
        Set<PetType> data=Set.of(cat,lion);
        // add some createCourseAttendance(..)
        // Act / SUT invocation 
        Set<Course> actualResult=algorithm.selectCourses(data);
        // Asssert 
        assertEquals(TODO: COMPLETE HERE!);
    }        
     */

  

    // We provide this method to ease the creation of valid instances of CourseAttendance
    public CourseAttendance createCourseAttendance(Course c){
        Vet april=new Vet();
        april.setFirstName("April");
        april.setLastName("O'Neil");
        CourseAttendance ca = new CourseAttendance();
        ca.setAttendant(april);
        ca.setCourse(c);
        ca.setRegisteredOn(LocalDate.now());
        c.getAttendants().add(ca);
        return ca;
    }

    public PetType createValidPetType(String name){
        PetType pt=new PetType();
        pt.setName(name);
        pt.setCourses(new HashSet<Course>());
        return pt;
    }

      public Course createCourse(Integer id, String name, Integer maxAttendants, Set<PetType> types) {
        Vet splinter = new Vet();
        splinter.setFirstName("Splinter");
        splinter.setLastName("Master");
        Course c = new Course();
        c.setId(id);
        c.setName(name);
        c.setMaxAttendants(maxAttendants);
        c.setAttendants(new HashSet<CourseAttendance>());
        c.setRegardedPetTypes(types);
        for(PetType pt:types) {
            pt.getCourses().add(c);
        }
        return c;
    }
  
    public void setAlgorithm(CourseOfferingAlgorithm value){
        this.offeringAlgorithm=value;
    }
}
