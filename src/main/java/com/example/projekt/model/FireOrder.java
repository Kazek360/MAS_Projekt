package com.example.projekt.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FireOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Min(1)
    private Integer duration;

    @FutureOrPresent(message = "The date cannot be earlier than the current date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    @NotBlank(message = "Barage location is mandatory")
    @Size(min = 2, max = 255)
    private String artilleryBarageLocation;

    @ManyToOne(optional = false)
    @JoinColumn(name = "artillery_site_id", nullable = false, updatable = false)
    private ArtillerySite artillerySite;

}
