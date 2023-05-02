package com.edic.azureadeval.services;


import com.edic.azureadeval.models.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EvaluatorServiceImpl implements EvaluatorService {

    @Autowired
    private JsonParserService jsonParserService;

    @Override
    public <T> JSONObject evaluate(List<T> list, String username) {

        ArrayList<Answer> answerArrayList = (ArrayList<Answer>) new ArrayList<>(list);
        ArrayList<Ponder> ponderArrayList = new ArrayList<>(jsonParserService.toPondersList("ponder.json"));

        // ponderArrayList.forEach(ponder -> System.out.println(ponder.toString()));
        // answerArrayList.forEach(answer -> System.out.println(answer.toString()));

        /* Creando un nuevo usuario con username como parámetro */
        Results user = new Results(username);

        /* Creando sublistas para cada sección */
        Integer currentSection = 0;
        ArrayList<ArrayList<Answer>> sectionSublist = new ArrayList<>();
        for(Answer answer : answerArrayList) {

            if(! currentSection.equals(answer.getSection())) {
                sectionSublist.add(new ArrayList<>());
                currentSection++;
                // System.out.println("se creo la lista " + answer.getSection());
            }

            if(answer.getSection().equals(currentSection)) {
                sectionSublist.get(currentSection - 1).add(answer);
            }

        }

        ArrayList<Ranges> ranges = new ArrayList<>();

        for(ArrayList<Answer> section : sectionSublist) { // Iteración sobre cada lista por sección


            if(section.get(0).getSection() < 6 ) {

                Integer acc = 0;
                for( Answer answer : section) {
                    Map<String, Integer> mapping = new HashMap<>();
                    mapping.put("Totalmente de acuerdo", 4);
                    mapping.put("De acuerdo", 3);
                    mapping.put("Desacuerdo", 2);
                    mapping.put("Totalmente en desacuerdo", 1);

                    Map<String, Integer> reversedMapping = new HashMap<>();
                    reversedMapping.put("Totalmente de acuerdo", 1);
                    reversedMapping.put("De acuerdo", 2);
                    reversedMapping.put("Desacuerdo", 3);
                    reversedMapping.put("Totalmente en desacuerdo", 4);

                    Integer [] reversedQuestions = {2, 5, 6, 7, 10, 11, 15, 16, 18, 19, 23, 24, 25, 26, 27, 28, 32, 33, 34, 36, 37, 38, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 51, 53, 54, 55, 56, 57, 59, 60, 61, 62, 64, 65, 66, 67, 68, 70, 72, 73, 75, 76, 78, 79, 80, 81, 84, 86, 88, 89, 90, 93, 94, 95, 97, 98, 99, 100, 101, 102, 104, 105, 107, 109, 110, 111, 116, 117, 119, 120, 121, 123, 125, 126, 128, 129};

                    Integer value;

                    if(Arrays.asList(reversedQuestions).contains(answer.getQuestion())) {
                        value = reversedMapping.get(answer.getAnswer());
                        //System.out.println(reversedMapping.get(answer.getAnswer()));
                    } else {
                        value = mapping.get(answer.getAnswer());
                        //System.out.println(mapping.get(answer.getAnswer()));
                    }

                    acc += value;

                    //System.out.println(acc);
                }

                Integer[] sectionRanges = ponderArrayList.get(sectionSublist.indexOf(section)).getRangos();


                Integer range = 0;

                // System.out.println(acc);

                //


                if( acc < sectionRanges[1]) {
                    range = 1;
                } else if ( acc < sectionRanges[2] ) {
                    range = 2;
                } else if (acc >= sectionRanges[2] ) {
                    range = 3;
                }

                // System.out.println(range);
                Ranges rangeDescription = new Ranges(section.get(0).getSection(),range);
                ranges.add(rangeDescription);

                //System.out.println(ranges.size());
            }

            if(section.get(0).getSection() >= 6) {

                Integer acc = 0;

                for( Answer answer : section) {

                    Integer value = 0;

                    if(answer.getAnswer().equals(answer.getCorrectAnswer())) {
                        value = 1;
                    }

                    acc += value;


                    //System.out.println(answer);
                }

                Integer[] sectionRanges = ponderArrayList.get(sectionSublist.indexOf(section)).getRangos();

                Integer range = 0;

                if( acc < sectionRanges[0]) {
                    range = 1;
                } else if ( acc < sectionRanges[1] ) {
                    range = 2;
                } else if (acc >= sectionRanges[2] ) {
                    range = 3;
                }

                Ranges rangeDescription = new Ranges(section.get(0).getSection(),range);
                ranges.add(rangeDescription);
            }

        }

        // ranges.forEach(r -> System.out.println(r.toString()));

        Integer subtotal = 0;

        for( Ranges range : ranges ) {
            subtotal += range.getScore();
        }

        Double total = 0.0;

        // Formula

        total = ((subtotal * 10) / 21) + 1.5;

        if(total >= 10.0) total = 10.0;

        System.out.println(username + " - Subtotal (rangos) ->" + subtotal);
        System.out.println(username + " - Total ->" + total);

        //System.out.println(finalEvaluation.toString());

        Map<String, Object> resultsMap = new HashMap<>();

        resultsMap.put("username",username);
        resultsMap.put("ranges",ranges);
        resultsMap.put("total",total);

        return new JSONObject(resultsMap);
    }
}
