package uts.server.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import uts.server.ThreatRequest;


import java.io.IOException;

public class PayloadHandler {

    static ObjectMapper mapper = new ObjectMapper();

    static ObjectReader reader = mapper.reader(ThreatRequest.class);

    static ObjectWriter writer = mapper.writer();

    public static ThreatRequest parseJson(String jsonRequest)
            throws JsonProcessingException, IOException {

        return reader.readValue(jsonRequest);
    }

    public static String generateJson(ThreatRequest response)
            throws JsonProcessingException {

        return writer.writeValueAsString(response);
    }

}

