package com.buurbak.api.trailers.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TrailerType {
    @Id
    private String name;
    public TrailerType(String name) {
        this.name = name;
    }
}


