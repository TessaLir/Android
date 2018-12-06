package ru.vetukov.onyma.test.onymamobile.result;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContractExample extends BaseExample {


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
        private List<Return> _return = null;

        public Integer getMoreRows() {
            return moreRows;
        }

        public void setMoreRows(Integer moreRows) {
            this.moreRows = moreRows;
        }

        public List<Return> getReturn() {
            return _return;
        }

        public void setReturn(List<Return> _return) {
            this._return = _return;
        }

    }

    public class Return {

        @SerializedName("contract_id")
        @Expose
        private String contractId;
        @SerializedName("contract_num")
        @Expose
        private String contractNum;
        @SerializedName("party_id")
        @Expose
        private String partyId;
        @SerializedName("start_dt")
        @Expose
        private String startDt;
        @SerializedName("end_dt")
        @Expose
        private String endDt;

        public String getContractId() {
            return contractId;
        }

        public void setContractId(String contractId) {
            this.contractId = contractId;
        }

        public String getContractNum() {
            return contractNum;
        }

        public void setContractNum(String contractNum) {
            this.contractNum = contractNum;
        }

        public String getPartyId() {
            return partyId;
        }

        public void setPartyId(String partyId) {
            this.partyId = partyId;
        }

        public String getStartDt() {
            return startDt;
        }

        public void setStartDt(String startDt) {
            this.startDt = startDt;
        }

        public String getEndDt() {
            return endDt;
        }

        public void setEndDt(String endDt) {
            this.endDt = endDt;
        }

    }

}
