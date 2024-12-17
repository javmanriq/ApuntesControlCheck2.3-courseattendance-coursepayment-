package org.springframework.samples.petclinic.course;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursePaymentRepository extends CrudRepository<CoursePayment,Integer>{

    Optional<CoursePayment> findById(Integer i);

    List<CoursePayment> findAll();

    CoursePayment save(CoursePayment any);
    
}
