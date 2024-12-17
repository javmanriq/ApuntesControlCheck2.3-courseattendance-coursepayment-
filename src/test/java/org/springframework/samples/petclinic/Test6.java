package org.springframework.samples.petclinic;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.samples.petclinic.course.CourseRepository;
import org.springframework.samples.petclinic.pet.PetType;
import org.springframework.samples.petclinic.vet.Vet;

import jakarta.persistence.EntityManager;

@DataJpaTest
public class Test6 extends ReflexiveTest{
       
    @Autowired
    CourseRepository cr;
        
    @Autowired
    EntityManager em;

    @Test
    public void test() {
        validatefindByComplexCriteria();
    }    

    private void 
    validatefindByComplexCriteria() {
        PetType turtle=em.find(PetType.class, 7);
        Vet v1 = em.find(Vet.class,1);
        Vet v2 = em.find(Vet.class,2);
        Vet v3 = em.find(Vet.class,3);
        Vet v4 = em.find(Vet.class,4);
        //PetType dogs=em.find(PetType.class, 2);
        //PetType hamsters=em.find(PetType.class, 6);
        Set<Vet> vets = cr.findVetsWithANumberOfCoursesOfAPetType(           
            Set.of(v1,v2,v3,v4),    
            turtle,
            2
        );
        assertNotNull(vets);
        assertEquals(2, vets.size());
        assertFalse(vets.stream().filter(v->v.getId()==1).toList().isEmpty());
        assertFalse(vets.stream().filter(v->v.getId()==3).toList().isEmpty());
        
        

        vets = cr.findVetsWithANumberOfCoursesOfAPetType(           
            Set.of(v1,v2,v3),    
            turtle,
            2
        );
        assertNotNull(vets);
        assertEquals(2, vets.size());
        assertFalse(vets.stream().filter(v->v.getId()==1).toList().isEmpty());
        assertFalse(vets.stream().filter(v->v.getId()==3).toList().isEmpty());
        
        

        vets = cr.findVetsWithANumberOfCoursesOfAPetType(           
            Set.of(v1,v3,v4),    
            turtle,
            2
        );
        assertNotNull(vets);
        assertEquals(2, vets.size());
        assertFalse(vets.stream().filter(v->v.getId()==1).toList().isEmpty());
        assertFalse(vets.stream().filter(v->v.getId()==3).toList().isEmpty());
        
        

        vets = cr.findVetsWithANumberOfCoursesOfAPetType(           
            Set.of(v1,v2,v4),    
            turtle,
            2
        );
        assertNotNull(vets);
        assertEquals(1, vets.size());
        assertFalse(vets.stream().filter(v->v.getId()==1).toList().isEmpty());
       
        

        vets = cr.findVetsWithANumberOfCoursesOfAPetType(           
            Set.of(v3,v4),    
            turtle,
            2
        );
        assertNotNull(vets);
        assertEquals(1, vets.size());
        assertFalse(vets.stream().filter(v->v.getId()==3).toList().isEmpty());
        
        
        

    }
}
