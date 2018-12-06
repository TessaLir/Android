package ru.vetukov.onyma.test.onymamobile.query;

import java.util.HashMap;

public class AuthQuery extends BaseQuery{
    private HashMap<String, String> params;

    public AuthQuery(HashMap<String, String> params, int id) {
        super("open_session", id);
        this.params = params;
    }

}
