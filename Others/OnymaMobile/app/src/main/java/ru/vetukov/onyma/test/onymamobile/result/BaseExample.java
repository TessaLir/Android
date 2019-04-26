package ru.vetukov.onyma.test.onymamobile.result;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public abstract class BaseExample {

    @SerializedName("jsonrpc")
    @Expose
    private String jsonrpc;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("error")
    @Expose
    private Error error;

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public class Error {

        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("code")
        @Expose
        private Integer code;
        @SerializedName("data")
        @Expose
        private Data data;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

    }

    public class Data {

        @SerializedName("side")
        @Expose
        private String side;

        public String getSide() {
            return side;
        }

        public void setSide(String side) {
            this.side = side;
        }

    }
}
