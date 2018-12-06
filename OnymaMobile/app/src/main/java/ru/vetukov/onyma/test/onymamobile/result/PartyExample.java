package ru.vetukov.onyma.test.onymamobile.result;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PartyExample extends BaseExample {

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

        @SerializedName("group_id")
        @Expose
        private String groupId;
        @SerializedName("name_attr")
        @Expose
        private String nameAttr;
        @SerializedName("audit_id")
        @Expose
        private String auditId;
        @SerializedName("sset_id")
        @Expose
        private String ssetId;
        @SerializedName("audit_seq")
        @Expose
        private String auditSeq;
        @SerializedName("base_group_id")
        @Expose
        private String baseGroupId;
        @SerializedName("class_id")
        @Expose
        private String classId;
        @SerializedName("party_id")
        @Expose
        private String partyId;
        @SerializedName("start_dt")
        @Expose
        private String startDt;
        @SerializedName("end_dt")
        @Expose
        private String endDt;

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getNameAttr() {
            return nameAttr;
        }

        public void setNameAttr(String nameAttr) {
            this.nameAttr = nameAttr;
        }

        public String getAuditId() {
            return auditId;
        }

        public void setAuditId(String auditId) {
            this.auditId = auditId;
        }

        public String getSsetId() {
            return ssetId;
        }

        public void setSsetId(String ssetId) {
            this.ssetId = ssetId;
        }

        public String getAuditSeq() {
            return auditSeq;
        }

        public void setAuditSeq(String auditSeq) {
            this.auditSeq = auditSeq;
        }

        public String getBaseGroupId() {
            return baseGroupId;
        }

        public void setBaseGroupId(String baseGroupId) {
            this.baseGroupId = baseGroupId;
        }

        public String getClassId() {
            return classId;
        }

        public void setClassId(String classId) {
            this.classId = classId;
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
