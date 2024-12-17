package org.springframework.samples.petclinic.course;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.pet.PetType;
import org.springframework.samples.petclinic.vet.Vet;

public interface CourseRepository extends CrudRepository<Course, Integer> {
    @Query("SELECT v FROM Vet v")
    Set<Vet> findVetsWithANumberOfCoursesOfAPetType(@Param("vets") Set<Vet> vets,
                                        @Param("petType") PetType petType, 
                                        @Param("minCourses") Integer minCourses);

    List<Course> findAll();
}
