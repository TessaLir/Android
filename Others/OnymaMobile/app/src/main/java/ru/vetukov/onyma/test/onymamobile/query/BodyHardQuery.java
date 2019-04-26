package ru.vetukov.onyma.test.onymamobile.query;

import java.util.HashMap;

public class BodyHardQuery extends BaseQuery {
    private HashMap<String, HashMap<String, String>> params;
    private String auth_token;

    public BodyHardQuery(String method, HashMap<String, HashMap<String, String>> params, String token) {
        super(method);
        this.params = params;
        this.auth_token = token;
    }
}
