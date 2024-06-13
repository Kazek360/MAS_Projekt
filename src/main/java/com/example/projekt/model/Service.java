package com.example.projekt.model;

import com.example.projekt.model.Validation.ValidServiceDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ValidServiceDate
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Soldier ID is mandatory")
    @Size(min = 2, max = 255)
    private String soldierID;

    @FutureOrPresent(message = "The date cannot be earlier than the current date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "date_from")
    private LocalDate dateFrom;

    @FutureOrPresent(message = "The date cannot be earlier than the current date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "date_to")
    private LocalDate dateTo;

    @ManyToOne
    @JoinColumn(name = "artillery_site_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ArtillerySite artillerySite;

    @ManyToOne
    @JoinColumn(name = "operation_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Operation operation;

}
