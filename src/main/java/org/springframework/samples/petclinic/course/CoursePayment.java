package org.springframework.samples.petclinic.course;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CoursePayment extends BaseEntity{

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "paymentDate")
    LocalDate paidOn;
    
    @NotNull
    Double amount;
}
