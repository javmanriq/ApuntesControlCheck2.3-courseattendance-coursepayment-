package org.springframework.samples.petclinic;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.course.Course;
import org.springframework.samples.petclinic.course.CourseAttendance;
import org.springframework.samples.petclinic.course.CourseAttendanceRepository;
import org.springframework.samples.petclinic.course.CourseAttendanceService;
import org.springframework.samples.petclinic.course.CourseService;
import org.springframework.samples.petclinic.course.UnfeasibleCourseAttendanceException;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.samples.petclinic.vet.Vet;
import org.springframework.samples.petclinic.vet.VetService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class Test8 {

	@MockBean
	CourseAttendanceRepository car;

	private static final Integer AVAILABLE_COURSE_ID = 1;
	private static final Integer FULL_COURSE_ID = 4;

	@Autowired(required = false)
	CourseAttendanceService courseAttendanceService;

	@Autowired
	VetService vetService;

	@Autowired
	CourseService courseService;

	@Test
	public void test8UnfeasibleCourseAttendanceDetected() {
		Course c = courseService.findCourseById(FULL_COURSE_ID);
		CourseAttendance ca = createCourseAttendance(c);
		assertThrows(UnfeasibleCourseAttendanceException.class, () -> courseAttendanceService.save(ca),
				"The course associated with the attendacne is complete, so an Unfeasible Course Attendance exception should be thrown.");
	}

	@Test
	public void test8ValiCourseAttendanceSaved() {
		Course c = courseService.findCourseById(AVAILABLE_COURSE_ID);
		CourseAttendance ca = createCourseAttendance(c);
		try {
			courseAttendanceService.save(ca);
		} catch (UnfeasibleCourseAttendanceException e) {
			fail("The course attendance is valid, the exception should not be thrown! :" + e.getMessage());
		}
		verify(car, times(1)).save(ca);
	}

	public CourseAttendance createCourseAttendance(Course c) {
		Vet vet = vetService.findVetById(1);
		CourseAttendance ca = new CourseAttendance();
		ca.setAttendant(vet);
		ca.setCourse(c);
		ca.setRegisteredOn(LocalDate.now());
		return ca;
	}

}
