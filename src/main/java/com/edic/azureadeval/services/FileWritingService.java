package com.edic.azureadeval.services;

import org.json.JSONObject;

public interface FileWritingService {

    public boolean createAndWriteJson(String username, JSONObject resultJson);
}
