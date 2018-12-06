package ru.vetukov.onyma.test.onymamobile.query;

public abstract class BaseQuery {

    private static int idALL = 0;

    private String jsonrpc;
    private String method;
    private int id;

    public BaseQuery(String jsonrpc, String method) {
        this.jsonrpc = jsonrpc;
        this.method = method;
        this.id = ++idALL;
    }

    public BaseQuery(String method) {
        this("2.0", method);
    }

    public BaseQuery(String method, int id) {
        this("2.0", method);
        this.id = id;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public String getMethod() {
        return method;
    }
    public int getId() {
        return id;
    }

}
