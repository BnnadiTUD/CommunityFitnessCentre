package repos;

import entities.Emissions;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class EmissionsRepo implements PanacheRepository<Emissions> {

    public List<Emissions> findByCategory(String categoryCode) {
        return list("categoryCode", categoryCode);
    }
}
