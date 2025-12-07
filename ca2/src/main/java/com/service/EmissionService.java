package service;


import entities.Emissions;
import entities.User;
import repos.EmissionsRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class EmissionService {

    @Inject
    EmissionsRepo emissionRepository;

    public List<Emissions> getAll() {
        return emissionRepository.listAll();
    }

    public Emissions getById(Long id) {
        return emissionRepository.findById(id);
    }

    public List<Emissions> getByCategory(String categoryCode) {
        return emissionRepository.findByCategory(categoryCode);
    }

    @Transactional
    public Emissions create(Emissions e) {
        emissionRepository.persist(e);
        return e;
    }

    @Transactional
    public Emissions update(Long id, Emissions updated) {
        Emissions existing = emissionRepository.findById(id);
        if (existing == null) return null;

        existing.categoryCode = updated.categoryCode;
        existing.categoryName = updated.categoryName;
        existing.description = updated.description;
        existing.year = updated.year;
        existing.value = updated.value;
        existing.scenario = updated.scenario;
        existing.sourceType = updated.sourceType;

        return existing;
    }

    @Transactional
    public boolean delete(Long id) {
        return emissionRepository.deleteById(id);
    }

    @Transactional
    public Emissions approve(Long id, User approver) {
        Emissions e = getById(id);
        if (e == null) return null;
        e.approved = true;
        e.approvedBy = approver;
        return e;
    }

}
