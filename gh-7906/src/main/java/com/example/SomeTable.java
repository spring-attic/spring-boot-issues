package com.example;

import javax.persistence.*;

@Entity
@Table(name = "some_table")
public class SomeTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
