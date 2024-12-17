package org.springframework.samples.petclinic;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.course.Course;
import org.springframework.samples.petclinic.course.CourseAttendance;
import org.springframework.samples.petclinic.course.CourseAttendanceRepository;
import org.springframework.samples.petclinic.course.CourseAttendanceService;
import org.springframework.samples.petclinic.course.CoursePayment;
import org.springframework.samples.petclinic.course.CoursePaymentRepository;
import org.springframework.samples.petclinic.course.CoursePaymentService;
import org.springframework.samples.petclinic.course.UnfeasibleCourseAttendanceException;
import org.springframework.samples.petclinic.pet.PetType;
import org.springframework.samples.petclinic.vet.Vet;

import jakarta.persistence.EntityManager;

@ExtendWith(MockitoExtension.class)
public class Test5 extends ReflexiveTest{
    @Mock
    CourseAttendanceRepository car;
    @Mock
    CoursePaymentRepository cpr;

    @Autowired(required = false)
    EntityManager em;  

    
    CourseAttendanceService cas;    
    CoursePaymentService cps;
    
    @BeforeEach
    public void configuation(){
        cas=new CourseAttendanceService(car);
        cps=new CoursePaymentService(cpr);
    }
    
    @Test
    public void test5CheckTransactionalityOfCourseAttendanceService(){
        checkTransactional(CourseAttendanceService.class,"save", CourseAttendance.class);        
        checkTransactional(CourseAttendanceService.class,"getAll");
    }
    
    @Test
    public void test5CheckTransactionalityOfCoursePaymentService(){
        checkTransactional(CoursePaymentService.class,"save", CoursePayment.class);        
        checkTransactional(CoursePaymentService.class,"getAll");
    }    
    
    @Test
    public void test5CourseAttendanceServiceCanGetCourseAttendances(){
        assertNotNull(cas,"CourseAttendanceService was not injected by spring");
        when(car.findAll()).thenReturn(List.of());
        List<CourseAttendance> attendances=cas.getAll();
        assertNotNull(attendances,"The list of Attendances found by the CourseAttendanceService was null");
        // The test fails if the service does not invoke the findAll of the repository:
        verify(car).findAll();            
    }
    

    @Test
    public void test5CoursePaymentServiceCanGetCoursePayments(){
        assertNotNull(cps,"CoursePaymentService was not injected by spring");
        when(cpr.findAll()).thenReturn(List.of());
        List<CoursePayment> payments=cps.getAll();
        assertNotNull(payments,"The list of Payments found by the CoursePaymentService was null");
        // The test fails if the service does not invoke the findAll of the repository:
        verify(cpr).findAll();            
    }

    @Test
    public void test5CourseAttendanceServiceCanSaveCourseAttendances(){
        assertNotNull(cas,"CourseAttendanceService was not injected by spring");
        when(car.save(any(CourseAttendance.class))).thenReturn(null);
        CourseAttendance ca = createValidCourseAttendance();
        try {
            cas.save(ca);
        } catch (DataAccessException | UnfeasibleCourseAttendanceException e) {
            fail("The save method in CourseAttendanceService has failed: "+e.getMessage());
        }        
        // The test fails if the service does not invoke the save function of the repository:
        verify(car).save(ca);     
    }

    @Test
    public void test5CoursePaymentServiceCanSaveCoursePayments(){
        assertNotNull(cps,"CoursePaymentService was not injected by spring");
        when(cpr.save(any(CoursePayment.class))).thenReturn(null);
        CoursePayment cp = Test1.createValidCoursePayment(em);
        cps.save(cp);        
        // The test fails if the service does not invoke the save function of the repository:
        verify(cpr).save(cp);     
    }

    private CourseAttendance createValidCourseAttendance(){
        CourseAttendance ca=new CourseAttendance();
        //setValue(ca,"name",String.class,"Un course attendance válido");
        ca.setGrade(10);
        ca.setRegisteredOn(LocalDate.of(2023,12,18));
        ca.setPayments(Set.of(Test1.createValidCoursePayment(em)));
        ca.setCourse(new Course());
        ca.setAttendant(new Vet());
        return ca;
    }



  


}
