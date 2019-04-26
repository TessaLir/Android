package ru.vetukov.onyma.test.onymamobile.query;

public class LightQuery extends BaseQuery {

    private String auth_token;

    public LightQuery(String method, String auth_token, int id) {
        super(method, id);
    }


}
