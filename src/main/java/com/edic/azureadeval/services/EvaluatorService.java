package com.edic.azureadeval.services;

import com.edic.azureadeval.models.Answer;
import org.json.JSONObject;

import java.util.List;

public interface EvaluatorService {

    public <T> JSONObject evaluate (List<T> list, String username);
}