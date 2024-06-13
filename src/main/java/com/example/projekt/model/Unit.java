package com.example.projekt.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Unit name is mandatory")
    @Size(min = 2, max = 255)
    private String name;

    @NotBlank(message = "Location is mandatory")
    @Size(min = 2, max = 255)
    private String location;

    @Positive
    private Integer unitNumber;

    @ManyToOne(optional = false)
    @JoinColumn(name = "operation_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Operation operation;

}
