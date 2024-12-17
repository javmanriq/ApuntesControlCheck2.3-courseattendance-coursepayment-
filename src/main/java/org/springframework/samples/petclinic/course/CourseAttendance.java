package org.springframework.samples.petclinic.course;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.vet.Vet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CourseAttendance extends BaseEntity {

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "registrationDate")
    LocalDate registeredOn;
    
    @Min(0)
    @Max(10)
    Integer grade;
    
    @OneToMany
    Set<CoursePayment> payments;
    
    
    @ManyToOne(optional=false)
    Course course;
    
    @ManyToOne
    Vet attendant;
}
