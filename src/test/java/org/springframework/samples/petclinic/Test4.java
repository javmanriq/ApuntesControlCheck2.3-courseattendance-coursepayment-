package org.springframework.samples.petclinic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.samples.petclinic.course.CourseAttendance;
import org.springframework.samples.petclinic.course.CoursePayment;

import jakarta.persistence.EntityManager;

@DataJpaTest
public class Test4 extends ReflexiveTest {
    
    @Autowired
    EntityManager em;

    @Test
    public void test4InitialCoursePayments(){                        
        CoursePayment cp1=em.find(CoursePayment.class,1);
        assertNotNull(cp1,"There should exist a CoursePayment with id:1");
        assertEquals(LocalDate.of(2023,01,06),cp1.getPaidOn(), "incorrect payment date for id:1");
        assertEquals(150.00,cp1.getAmount());    
        
        CoursePayment cp2=em.find(CoursePayment.class,2);
        assertNotNull(cp2,"There should exist a CoursePayment with id:2");
        assertEquals(LocalDate.of(2023,01,10),cp2.getPaidOn(), "incorrect payment date for id:2");
        assertEquals(150.00,cp2.getAmount());     

        CoursePayment cp3=em.find(CoursePayment.class,3);
        assertNotNull(cp3,"There should exist a CoursePayment with id:3");
        assertEquals(LocalDate.of(2023,12,19),cp3.getPaidOn(), "incorrect payment date for id:3");
        assertEquals(50.00,cp3.getAmount());     
    }       
    
    @Test
    public void test4PaymentLinks() {    
        checkContainsById(CourseAttendance.class,1,"getPayments",1,em);
        checkContainsById(CourseAttendance.class,1,"getPayments",2,em);
        checkContainsById(CourseAttendance.class,2,"getPayments",3,em);
        
    }
    
}
