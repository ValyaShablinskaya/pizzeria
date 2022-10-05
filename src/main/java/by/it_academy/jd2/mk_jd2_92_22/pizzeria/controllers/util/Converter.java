package by.it_academy.jd2.mk_jd2_92_22.pizzeria.controllers.util;

import java.io.BufferedReader;
import java.io.IOException;

public class Converter {
    public String convertToString(BufferedReader reader) throws IOException {
        StringBuffer json = new StringBuffer();

        String line;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }
        return json.toString();
    }
}
