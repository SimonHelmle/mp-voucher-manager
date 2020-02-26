package com.simonhelmle.mp_voucher_manager.data;

public class JsonResponse {

    /*
    This class is used as a object to store JSON API responses.
            */

    int success;
    String message;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
