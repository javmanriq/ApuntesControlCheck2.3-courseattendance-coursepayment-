package org.springframework.samples.petclinic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.samples.petclinic.course.CourseAttendance;
import org.springframework.samples.petclinic.course.CoursePayment;

import jakarta.persistence.EntityManager;

@DataJpaTest()
public class Test3 extends ReflexiveTest {    
    @Autowired
    EntityManager em;    
    
    @Test
    public void test3InitialCourseAttendances(){                        
        CourseAttendance ca1=em.find(CourseAttendance.class,1);
        assertNotNull(ca1,"There should exist a CourseAttendance with id:1");
        assertEquals(LocalDate.of(2023,01,05),ca1.getRegisteredOn(), "incorrect registering date for id:1");
        assertEquals(5,ca1.getGrade());    
        
        CourseAttendance ca2=em.find(CourseAttendance.class,2);
        assertNotNull(ca2,"There should exist a CourseAttendance with id:2");
        assertEquals(LocalDate.of(2023,12,18),ca2.getRegisteredOn(), "incorrect registering date for id:2");
        assertEquals(null,ca2.getGrade());
    }

    @Test
    public void test3AttendanceReslationships(){
        checkLinkedById(CourseAttendance.class,1,"getCourse",1,em);
        checkLinkedById(CourseAttendance.class,2,"getCourse",2,em);
        checkLinkedById(CourseAttendance.class,1,"getAttendant",5,em);
        checkLinkedById(CourseAttendance.class,2,"getAttendant",3,em);
    } 
    
}
