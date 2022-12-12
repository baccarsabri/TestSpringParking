package com.example.demospring.Entite;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ref;
    private float dimension;
    @JsonIgnore
    @OneToMany
    private List<Personnel> gardes =new ArrayList<>();
    @JsonIgnore
    @OneToOne(mappedBy = "zone")
    private Personnel responsable;
    @JsonIgnore
    @ManyToOne
    private Parking parking;
}
