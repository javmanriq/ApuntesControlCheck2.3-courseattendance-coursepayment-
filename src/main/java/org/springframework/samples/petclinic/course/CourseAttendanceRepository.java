package org.springframework.samples.petclinic.course;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseAttendanceRepository extends CrudRepository<CourseAttendance,Integer>{

    Optional<CourseAttendance> findById(Integer i);

    List<CourseAttendance> findAll();

    CourseAttendance save(CourseAttendance any);
    
}
