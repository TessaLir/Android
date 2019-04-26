package ru.vetukov.onyma.test.onymamobile.result;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContactsExample extends BaseExample {

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

        @SerializedName("return")
        @Expose
        private List<Return> _return = null;

        public List<Return> getReturn() {
            return _return;
        }

        public void setReturn(List<Return> _return) {
            this._return = _return;
        }

    }

    public class Return {

        @SerializedName("phone_type")
        @Expose
        private String phoneType;
        @SerializedName("attr_id")
        @Expose
        private String attrId;
        @SerializedName("value")
        @Expose
        private String value;
        @SerializedName("contact_id")
        @Expose
        private String contactId;

        public String getPhoneType() {
            return phoneType;
        }

        public void setPhoneType(String phoneType) {
            this.phoneType = phoneType;
        }

        public String getAttrId() {
            return attrId;
        }

        public void setAttrId(String attrId) {
            this.attrId = attrId;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getContactId() {
            return contactId;
        }

        public void setContactId(String contactId) {
            this.contactId = contactId;
        }

    }
}
