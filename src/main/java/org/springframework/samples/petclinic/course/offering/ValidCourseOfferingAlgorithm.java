package org.springframework.samples.petclinic.course.offering;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.samples.petclinic.course.Course;
import org.springframework.samples.petclinic.pet.PetType;


public class ValidCourseOfferingAlgorithm implements CourseOfferingAlgorithm{

    @Override
    public Set<Course> selectCourses(Set<PetType> types) {
        if(types==null || types.isEmpty())
            return Set.of();
        // Obtenemos todos los cursos asociados a los tipos de mascotas
        Set<Course> allCourses = types.stream().flatMap(t -> t.getCourses().stream()).collect(Collectors.toSet());
        // Filtramos los cursos que tengan menos asistentes que la mitad de los máximos asistentes y devolvemos el resultado
        return allCourses.stream().filter(c -> c.getAttendants().size() < (c.getMaxAttendants()/2)).collect(Collectors.toSet());
    }
}
