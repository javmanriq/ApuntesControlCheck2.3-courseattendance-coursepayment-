package org.springframework.samples.petclinic.course;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public class CoursePaymentService {
    CoursePaymentRepository repo;

    public CoursePaymentService(CoursePaymentRepository cpr){
        this.repo=cpr;
    }

    @Transactional(readOnly = true)
    public List<CoursePayment> getAll() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    public CoursePayment save(CoursePayment cp) {
        return repo.save(cp);
    }
}
