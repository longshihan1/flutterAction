package model;

public class FANYIINFO {
    boolean success;
    String data;
    String msg;
    int code;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "FANYIINFO{" +
                "success=" + success +
                ", data='" + data + '\'' +
                ", msg='" + msg + '\'' +
                ", code=" + code +
                '}';
    }
}
