package com.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class JSService {

    @Value("${dev}")
    private Boolean dev;

    public String getJSContent(String fileName) {

        StringBuilder result = new StringBuilder();
        if (fileName.endsWith(".js")) {
            String responseFileName = fileName;
            boolean fileExists = new File("src/main/resources/scripts/" + fileName).exists();
            boolean minExists = new File("src/main/resources/scripts/" + fileName.replace(".js", ".min.js")).exists();
            if (minExists && !dev) {
                responseFileName = fileName.replace(".js", ".min.js");
            }
            if (!fileExists && minExists) {
                responseFileName = fileName.replace(".js", ".min.js");
            }

            try {
                BufferedReader reader =
                        new BufferedReader(
                                new InputStreamReader(
                                        new FileInputStream("src/main/resources/scripts/" + responseFileName)));
                int i;
                while ((i = reader.read()) != -1) {
                    result.append((char) i);
                }

            } catch (FileNotFoundException ex) {
                return "File " + fileName + " was not found";
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result.toString();
        } else {
            throw new RuntimeException("Wrong file. Should be JavaScript file ");
        }
    }
}
