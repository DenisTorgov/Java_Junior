package org.example;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.IOException;

public class ReadDataFromFile {
    public static Student ReadFromXML (File file) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
//        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Student student = xmlMapper.readValue(file, Student.class);
        return student;
    }
    public static Student ReadFromJSON(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Student student = objectMapper.readValue(file, Student.class);
        return student;
    }
}
