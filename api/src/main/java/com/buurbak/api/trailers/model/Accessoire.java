package com.buurbak.api.trailers.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Accessoire {
    @Id
    private String name;

    @ManyToMany(mappedBy = "accessoire")
    Set<TrailerOffer> trailerOffers;
}
