package parser;

import entities.Emissions;
import repos.EmissionsRepo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.io.InputStream;
import java.util.List;

class JsonEmissionRecordDto {
    public String categoryName;
    public int Year;
    public double Value;
    public String Scenario;

}

@ApplicationScoped
public class JsonEmissionParser {

    @Inject
    EmissionsRepo eR;

    @Transactional
    public int parseAndSave() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        InputStream is = getClass().getClassLoader()
                .getResourceAsStream("data/actual_2023.json");

        List<JsonEmissionRecordDto> list =
                mapper.readValue(is, new TypeReference<List<JsonEmissionRecordDto>>() {});

        int count = 0;

        for (JsonEmissionRecordDto r : list) {

            if (r.Value <= 0) continue;
            if (!"WEM".equalsIgnoreCase(r.Scenario)) continue;
            if (r.Year != 2023) continue;

            Emissions e = new Emissions();

            e.categoryName = r.categoryName;
            e.year = r.Year;
            e.value = r.Value;
            e.scenario = r.Scenario;
            e.sourceType = "JSON";
            e.description = null;
            eR.persist(e);
            count++;
        }

        return count;
    }
}


