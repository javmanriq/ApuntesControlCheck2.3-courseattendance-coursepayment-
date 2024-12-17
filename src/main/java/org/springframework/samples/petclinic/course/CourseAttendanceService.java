package org.springframework.samples.petclinic.course;

import java.util.List;


import org.springframework.dao.DataAccessException;

import org.springframework.transaction.annotation.Transactional;

public class CourseAttendanceService {
    CourseAttendanceRepository repo;

    public CourseAttendanceService(CourseAttendanceRepository car){
        this.repo=car;
    }

    @Transactional(readOnly = true)
    public List<CourseAttendance> getAll() {
        return repo.findAll();
    }

    @Transactional(rollbackFor = UnfeasibleCourseAttendanceException.class)
    public CourseAttendance save(CourseAttendance ca) throws DataAccessException, UnfeasibleCourseAttendanceException {
        Course course = ca.getCourse();
        if (course.getAttendants().size() >= course.getMaxAttendants()) {
            throw new UnfeasibleCourseAttendanceException();
        }
        return repo.save(ca);
    }
}