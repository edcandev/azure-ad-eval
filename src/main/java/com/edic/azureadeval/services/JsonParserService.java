package com.edic.azureadeval.services;

import com.edic.azureadeval.models.Answer;
import com.edic.azureadeval.models.Ponder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonParserService {

    JSONObject inputJsonObject;

    public List<Ponder> toPondersList(String jsonFile) {

        Path filePath;

        if(System.getProperty("os.name").contains("Windows")) {
            filePath = Path.of(System.getProperty("user.dir"),"src","main","resources","input",jsonFile);
        } else {
            filePath = Path.of("/results",jsonFile);
        }

        System.out.println(filePath);

        try {

            String jsonString = new String(Files.readAllBytes(filePath));

            JSONObject obj = new JSONObject(jsonString);
            JSONArray pondersJSON = (JSONArray) obj.get("ponder");

            List<Ponder> ponderObjectsList = new ArrayList<>();

            for(int i = 0; i < pondersJSON.length(); i++) {

                JSONObject ponderJSON = (JSONObject) pondersJSON.get(i);

                JSONArray _rangos = (JSONArray) ponderJSON.get("rangos");
                Integer[] ponderJSONRangos = new Integer[3];
                for(int j = 0; j < _rangos.length(); j++) {
                    ponderJSONRangos[j] = (Integer) _rangos.get(j);
                }

                Ponder ponderObject = new Ponder();

                ponderObject.setSeccion(ponderJSON.getInt("seccion"));
                ponderObject.setNumPreguntas(ponderJSON.getInt("numPreguntas"));
                ponderObject.setMaxPosible(ponderJSON.getInt("maxPosible"));
                ponderObject.setRangos(ponderJSONRangos);

                ponderObjectsList.add(ponderObject);
            }
            return ponderObjectsList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public String getUsername(String json) {
        inputJsonObject = new JSONObject(json);
        return (String)inputJsonObject.get("username");
    }

    public List<Answer> toAnswersList(String json) {

        inputJsonObject = new JSONObject(json);
        JSONArray answersJSON = (JSONArray) inputJsonObject.get("answers");
        List<Answer> answerObjectsList = new ArrayList<>();

        for(int i = 0; i < answersJSON.length(); i++) {
            JSONObject answerJSON = (JSONObject) answersJSON.get(i);

            Answer answerObject = new Answer(
                    (Integer)answerJSON.get("question"),
                    (Integer)answerJSON.get("section"),
                    (String)answerJSON.get("answer"),
                    (String)answerJSON.get("correctAnswer"));

            answerObjectsList.add(answerObject);
        }
        return answerObjectsList;
    }
}
