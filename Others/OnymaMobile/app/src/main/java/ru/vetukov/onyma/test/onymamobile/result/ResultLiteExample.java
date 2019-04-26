package ru.vetukov.onyma.test.onymamobile.result;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultLiteExample extends BaseExample {


    @SerializedName("result")
    @Expose
    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public class Result {
        @SerializedName("more_rows")
        @Expose
        private Integer moreRows;
        @SerializedName("return")
        @Expose
        private String _return = null;

        public Integer getMoreRows() {
            return moreRows;
        }

        public void setMoreRows(Integer moreRows) {
            this.moreRows = moreRows;
        }

        public String getReturn() {
            return _return;
        }

        public void setReturn(String _return) {
            this._return = _return;
        }
    }

}
