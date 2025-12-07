package parser;

import entities.Emissions;
import repos.EmissionsRepo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.io.InputStream;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
class XmlProjectionRecord {
    public String Category__1_3;  
    public int Year;
    public String Scenario;
    public String Gas___Units;
    public double Value;
}

@JsonIgnoreProperties(ignoreUnknown = true)
class XmlRoot {

    @JacksonXmlElementWrapper(useWrapping = false)
    public List<XmlProjectionRecord> Row;
}

@ApplicationScoped
public class XmlEmissionParser {

    @Inject
    EmissionsRepo eR;

    /**
     * Parse projections.xml from classpath and save matching rows to the DB.
     * @return number of rows inserted
     */
    @Transactional
    public int parseAndSave() throws Exception {
        XmlMapper xmlMapper = new XmlMapper();

        InputStream is = getClass().getClassLoader()
                .getResourceAsStream("data/projections.xml");

        XmlRoot root = xmlMapper.readValue(is, XmlRoot.class);

        if (root.Row == null || root.Row.isEmpty()) {
            throw new IllegalStateException("No <Row> records found in projections.xml");
        }

        int count = 0;

        for (XmlProjectionRecord r : root.Row) {
            if (r.Value <= 0) continue;
            if (!"WEM".equalsIgnoreCase(r.Scenario)) continue;
            if (r.Year != 2023) continue;

            Emissions e = new Emissions();

            // Map XML fields -> entity fields

            e.categoryName = r.Category__1_3;
            e.year = r.Year;
            e.value = r.Value;
            e.scenario = r.Scenario;
            e.sourceType = "XML";
            e.description = null; //for now
            eR.persist(e);
            count++;
        }
        return count;
    }
}
