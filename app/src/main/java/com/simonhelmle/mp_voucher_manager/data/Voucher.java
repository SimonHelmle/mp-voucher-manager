package com.simonhelmle.mp_voucher_manager.data;

public class Voucher {

    private int id;
    private String voucherCode;
    private String voucherCreationDate;
    private boolean voucherVoid;
    private String voucherVoidDate;

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVoucherCreationDate() {
        return voucherCreationDate;
    }

    public void setVoucherCreationDate(String voucherCreationDate) {
        this.voucherCreationDate = voucherCreationDate;
    }

    public boolean isVoucherVoid() {
        return voucherVoid;
    }

    public void setVoucherVoid(boolean voucherVoid) {
        this.voucherVoid = voucherVoid;
    }

    public String getVoucherVoidDate() {
        return voucherVoidDate;
    }

    public void setVoucherVoidDate(String voucherVoidDate) {
        this.voucherVoidDate = voucherVoidDate;
    }
}
