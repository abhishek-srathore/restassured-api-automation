package com.api.reqres.payloads;

import org.json.JSONObject;

public class PayLoadBuilder {

    public static String createUserPayload(String name, String job) {
        JSONObject obj = new JSONObject();
        obj.put("name", name);
        obj.put("job", job);
        return obj.toString();
    }
}

