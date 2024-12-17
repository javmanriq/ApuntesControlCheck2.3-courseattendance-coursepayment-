package org.springframework.samples.petclinic.course;

import java.util.Set;

import org.springframework.samples.petclinic.model.NamedEntity;
import org.springframework.samples.petclinic.pet.PetType;
import org.springframework.samples.petclinic.vet.Vet;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Course extends NamedEntity {
    String description;
    @NotNull
    Integer maxAttendants;
    @ManyToMany
    Set<PetType> regardedPetTypes;
    Double price;
    @ManyToOne(optional = false)
	@JoinColumn(name = "vet_id")
	private Vet teacher;
    
    @OneToMany(mappedBy="course")
    @JsonIgnore
    Set<CourseAttendance> attendants;
}
