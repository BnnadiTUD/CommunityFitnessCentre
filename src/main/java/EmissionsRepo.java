package com.example.emissions.repos;

import com.entities.emissions;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class EmissionRepository implements PanacheRepository<EmissionRecord> {

    public List<EmissionRecord> findByCategory(String categoryCode) {
        return list("categoryCode", categoryCode);
    }
}

