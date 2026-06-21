package com.maxi.incidentmanager.application.entity;

import com.maxi.incidentmanager.service.entity.BusinessService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100)
    private String name;

    @OneToMany(mappedBy = "application",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    List<BusinessService> services = new ArrayList<>();
}
