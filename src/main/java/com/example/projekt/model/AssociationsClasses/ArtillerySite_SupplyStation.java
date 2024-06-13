package com.example.projekt.model.AssociationsClasses;

import com.example.projekt.model.ArtillerySite;
import com.example.projekt.model.SupplyStation;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArtillerySite_SupplyStation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "artillery_site_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ArtillerySite artillerySite;

    @ManyToOne
    @JoinColumn(name = "supply_station_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private SupplyStation supplyStation;

}
