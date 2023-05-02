package com.edic.azureadeval.services;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;

@Component
public class FileWritingServiceImpl implements FileWritingService {

    @Override
    public boolean createAndWriteJson(String username, JSONObject resultJson) {

        //Prefijo del usuario...

        String user = username.replaceAll("@edicmexico.onmicrosoft.com","");

        // Crear directorio y JSON personalizado

        File f = new File("/results/".concat(user)); // Crea el directorio sino existe

        if (f.mkdir()) {
            System.out.println("Directorio de " + user + " sido creado");
        }
        else {
            System.out.println("El directorio ya existe");
        }
        try {
            File file = new File(Path.of("/results/".concat(user),user.concat("_res.json")).toUri());
            FileWriter fileWriter;

            if(file.createNewFile()) {
                fileWriter = new FileWriter(file);
                fileWriter.write(resultJson.toString(4));
                fileWriter.close();
                System.out.println("Archivo de " + user + " sido creado");
                return true;
            } else {
                System.out.println("Ya existe el archivo de " + user);
            }
        } catch (Exception e) { e.printStackTrace();
            return false;
        }
        return false;
    }
}
