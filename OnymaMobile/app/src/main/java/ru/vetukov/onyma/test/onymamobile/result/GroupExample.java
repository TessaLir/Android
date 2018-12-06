package ru.vetukov.onyma.test.onymamobile.result;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GroupExample extends BaseExample {

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

        @SerializedName("audit_id")
        @Expose
        private String auditId;
        @SerializedName("audit_seq")
        @Expose
        private String auditSeq;
        @SerializedName("tz_id")
        @Expose
        private String tzId;
        @SerializedName("object_id")
        @Expose
        private String objectId;
        @SerializedName("locale_id")
        @Expose
        private String localeId;
        @SerializedName("enterpr")
        @Expose
        private String enterpr;
        @SerializedName("name_int")
        @Expose
        private String nameInt;
        @SerializedName("rid")
        @Expose
        private String rid;
        @SerializedName("group_id")
        @Expose
        private String groupId;
        @SerializedName("up_id")
        @Expose
        private String upId;

        public String getAuditId() {
            return auditId;
        }

        public void setAuditId(String auditId) {
            this.auditId = auditId;
        }

        public String getAuditSeq() {
            return auditSeq;
        }

        public void setAuditSeq(String auditSeq) {
            this.auditSeq = auditSeq;
        }

        public String getTzId() {
            return tzId;
        }

        public void setTzId(String tzId) {
            this.tzId = tzId;
        }

        public String getObjectId() {
            return objectId;
        }

        public void setObjectId(String objectId) {
            this.objectId = objectId;
        }

        public String getLocaleId() {
            return localeId;
        }

        public void setLocaleId(String localeId) {
            this.localeId = localeId;
        }

        public String getEnterpr() {
            return enterpr;
        }

        public void setEnterpr(String enterpr) {
            this.enterpr = enterpr;
        }

        public String getNameInt() {
            return nameInt;
        }

        public void setNameInt(String nameInt) {
            this.nameInt = nameInt;
        }

        public String getRid() {
            return rid;
        }

        public void setRid(String rid) {
            this.rid = rid;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getUpId() {
            return upId;
        }

        public void setUpId(String upId) {
            this.upId = upId;
        }

    }
}
