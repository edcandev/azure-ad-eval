package com.edic.azureadeval.services;

import org.json.JSONObject;

import java.io.IOException;

public interface FileWritingService {

    public boolean createAndWriteJson(String username, JSONObject resultJson, String sufix) throws IOException;
}
