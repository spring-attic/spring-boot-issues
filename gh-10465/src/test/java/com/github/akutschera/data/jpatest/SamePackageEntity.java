package com.github.akutschera.data.jpatest;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class SamePackageEntity {

    @Id
    private Long id;

    private String name;
}
