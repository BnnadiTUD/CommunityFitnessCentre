package service;

import parser.JsonEmissionParser;
import parser.XmlEmissionParser;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ParserService {

    @Inject
    XmlEmissionParser xmlParser;

    @Inject
    JsonEmissionParser jsonParser;

    @Transactional
    public int runAllParsers() throws Exception {
        int fromXml = xmlParser.parseAndSave();
        int fromJson = jsonParser.parseAndSave();
        return fromXml + fromJson;
    }
}
