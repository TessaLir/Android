package ru.vetukov.onyma.test.onymamobile.query;

import java.util.HashMap;

public class BodyLightQuery extends BaseQuery {

    private HashMap<String, String> params;
    private String auth_token;

    public BodyLightQuery(String method, HashMap<String, String> params, String token) {
        super(method);
        this.params = params;
        auth_token = token;
    }

    public BodyLightQuery(String method, int id, HashMap<String, String> params, String token) {
        super(method, id);
        this.params = params;
        auth_token = token;
    }
}
