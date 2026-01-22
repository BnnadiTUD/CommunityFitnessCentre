package com.example.emissions.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
public class User extends PanacheEntityBase {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String email;

    public String password;

    public boolean admin = false;

    public boolean active = true;
}
