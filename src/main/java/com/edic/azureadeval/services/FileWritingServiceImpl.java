package com.edic.azureadeval.services;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

@Component
public class FileWritingServiceImpl implements FileWritingService {

    @Override
    public boolean createAndWriteJson(String username, JSONObject resultJson) throws IOException {

        //Prefijo del usuario...

        String user = username.replaceAll("@edicmexico.onmicrosoft.com","");

        // Crear directorio y JSON personalizado

        //file.createNewFile();

        /*File f = new File("/results/".concat(user)); // Crea el directorio sino existe

        if (f.mkdir()) {
            System.out.println("Directorio de " + user + " sido creado");
        }
        else {
            System.out.println("El directorio ya existe");
        }*/
        try {

            String fileSeparator = System.getProperty("file.separator");

            new File(fileSeparator+"results"+fileSeparator + user).mkdirs();

            String absoluteFilePath = fileSeparator+"results"+fileSeparator + user + fileSeparator + user + "-res.json";
            //String absoluteFilePath = fileSeparator+ user + "-res.json";

            System.out.println(fileSeparator);

            File file = new File(absoluteFilePath);

            file.createNewFile(); // Crea archivo

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(resultJson.toString(4));
            fileWriter.close();
            System.out.println("Archivo de " + user + " ha sido creado");
            return true;

            //String text = "this is just a test ";
            //FileWriter fw = new FileWriter(String.valueOf(file.toURI()));
            //fw.write(text);
            //fw.close();
            /*
            System.out.println(file);
            FileWriter fileWriter;

            if(file.createNewFile()) {
                fileWriter = new FileWriter(file);
                fileWriter.write(resultJson.toString(4));
                fileWriter.close();
                System.out.println("Archivo de " + user + " sido creado");
                return true;
            } else {
                System.out.println("Ya existe el archivo de " + user);
            }*/

        } catch (Exception e) { e.printStackTrace();
            return false;
        }
    }
}
