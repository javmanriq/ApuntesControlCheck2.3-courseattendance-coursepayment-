package org.springframework.samples.petclinic;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.samples.petclinic.course.Course;
import org.springframework.samples.petclinic.course.CourseAttendance;
import org.springframework.samples.petclinic.vet.Vet;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@DataJpaTest()
public class Test2 extends ReflexiveTest{
    
    @Autowired(required = false)
    EntityManager em;         


    @Test
    public void test2CourseRemoveAnnotations() {
        try{
            assertFalse(isFieldAnnotatedWith(Course.class, "attendants", Transient.class));
        }catch(NoSuchFieldException ex){
            fail("The Course class should not have a field with Transient annotation: "+ex.getMessage());
        }
    }

    @Test
    public void test2CourseAttendanceRemoveAnnotations() {
        try{
            assertFalse(isFieldAnnotatedWith(CourseAttendance.class, "attendant", Transient.class));
            assertFalse(isFieldAnnotatedWith(CourseAttendance.class, "course", Transient.class));
            assertFalse(isFieldAnnotatedWith(CourseAttendance.class, "payments", Transient.class));
        }catch(NoSuchFieldException ex){
            fail("The CourseAttendance class should not have a field with Transient annotation: "+ex.getMessage());
        }
    }

    @Test
    public void test2CourseAnnotations() {
        checkThatFieldIsAnnotatedWith(Course.class, "attendants", OneToMany.class);                          
    }

    @Test
    public void test2CourseAttendanceAnnotations() {
        checkThatFieldIsAnnotatedWith(CourseAttendance.class, "attendant", ManyToOne.class);    
        checkThatFieldIsAnnotatedWith(CourseAttendance.class, "course", ManyToOne.class); 
        checkThatFieldIsAnnotatedWith(CourseAttendance.class, "payments", OneToMany.class);                       
    }

    @Test
    private void test2CourseConstraints() {
        Course c=createValidCourse(em);
        checkThatFieldsAreMandatory(c, em,"teacher","maxAttendants");        
    }

    @Test
    private void test2CourseAttendanceConstraints() {
        CourseAttendance ca =Test1.createValidCourseAttendance(em);
        checkThatFieldsAreMandatory(ca, em,"course","attendant");        
    }


    public static Course createValidCourse(EntityManager em){        
        Course c = new Course(); 
        setValue(c,"name",String.class,"Un course válido");
        c.setMaxAttendants(10);
        c.setTeacher(em.find(Vet.class,1));
        return c;
    }


}
