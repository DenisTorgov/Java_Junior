package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.IOException;

public class WriteDataToFile {

    public static void WriteToXML(Object obj) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.writeValue(new File("Student.xml"), obj);
        System.out.println("Объект " + obj.toString() + " сериализован в файл XML");
    }
    public static void WriteToJSON(Object obj) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("Student.json"), obj);
        System.out.println("Объект " + obj.toString() + " сериализован в файл JSON");
    }
}
