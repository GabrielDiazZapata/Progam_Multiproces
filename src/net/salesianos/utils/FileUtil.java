package net.salesianos.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileUtil {

    public static BufferedReader getBufferedReader(String nombreFichero) throws IOException {
        FileReader lector = new FileReader(nombreFichero);
        return new BufferedReader(lector);
    }

    public static PrintWriter getPrintWriter(String nombreFichero) throws IOException {
        FileWriter fileWriter = new FileWriter(nombreFichero);
        return new PrintWriter(fileWriter);
    }

    public static ArrayList<String> getLineasFichero(String nombreFichero) throws IOException {
        ArrayList<String> lineas = new ArrayList<>();
        try (BufferedReader bfr = getBufferedReader(nombreFichero)) {
            String linea;
            while ((linea = bfr.readLine()) != null) {
                lineas.add(linea);
            }
        }
        return lineas;
    }
}
