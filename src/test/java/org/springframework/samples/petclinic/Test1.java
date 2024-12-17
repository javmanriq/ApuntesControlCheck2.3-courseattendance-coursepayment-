package org.springframework.samples.petclinic;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.course.Course;
import org.springframework.samples.petclinic.course.CourseAttendance;
import org.springframework.samples.petclinic.course.CourseAttendanceRepository;
import org.springframework.samples.petclinic.course.CoursePayment;
import org.springframework.samples.petclinic.course.CoursePaymentRepository;
import org.springframework.samples.petclinic.vet.Vet;
import org.springframework.stereotype.Service;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class Test1 extends ReflexiveTest{

    @Autowired(required = false)
    CourseAttendanceRepository attendanceRepo;
    @Autowired(required = false)
    CoursePaymentRepository paymentRepo;
    
    @Autowired
    EntityManager em;

    @Test
    public void test1RepositoriesExist(){
        assertNotNull(attendanceRepo,"The course attendance repository was not injected into the tests, its autowired value was null");
        assertNotNull(paymentRepo,"The course payment repository was not injected into the tests, its autowired value was null");
        test1RepositoriesContainsMethod();
    }

    public void test1RepositoriesContainsMethod(){
        if(attendanceRepo!=null){
            Object v=attendanceRepo.findById(12);
            assertFalse(null!=v && ((Optional)v).isPresent(), "No result (null) should be returned for a course attendance that does not exist");
        }else
            fail("The course attendance repository was not injected into the tests, its autowired value was null");
        
        if(paymentRepo!=null){
            Object v=paymentRepo.findById(12);
            assertFalse(null!=v && ((Optional)v).isPresent(), "No result (null) should be returned for a course payment that does not exist");
        }else
            fail("The course payment repository was not injected into the tests, its autowired value was null");
    }
    
    

    
    @Test
    public void test1CheckCourseAttendanceConstraints() {
        Map<String,List<Object>> invalidValues=Map.of(
                                            "grade",     List.of(
                                                        -1,11       
                                                    )                                            
                                            );


        CourseAttendance ca=createValidCourseAttendance(em);
        em.persist(ca);
        
        checkThatFieldsAreMandatory(ca, em, "registeredOn");        
        
        checkThatValuesAreNotValid(ca, invalidValues,em);   
    }

    @Test
    public void test1CheckCoursePaymentContraints() {


        CoursePayment cp=createValidCoursePayment(em);
        em.persist(cp);
        
        checkThatFieldsAreMandatory(cp, em, "paidOn","amount");        
          
    }

    
    @Test
    public void test1CheckCourseAttendanceAnnotations() {        
        assertTrue(classIsAnnotatedWith(CourseAttendance.class,Entity.class));
    }

    @Test
    public void test1CheckCoursePaymentAnnotations() {
        assertTrue(classIsAnnotatedWith(CoursePayment.class,Entity.class));
    }

    public static CoursePayment createValidCoursePayment(EntityManager em){        
        CoursePayment cp=new CoursePayment();     
        cp.setAmount(10.00);
        cp.setPaidOn(LocalDate.of(2023,12,18));
        return cp;
    }

    public static CourseAttendance createValidCourseAttendance(EntityManager em){
        CourseAttendance ca=new CourseAttendance();
        ca.setGrade(10);
        ca.setRegisteredOn(LocalDate.of(2023,12,18));
        ca.setPayments(Set.of(createValidCoursePayment(em)));
        ca.setCourse(em.find(Course.class,1));
        ca.setAttendant(em.find(Vet.class,1));
        return ca;
    }
}

