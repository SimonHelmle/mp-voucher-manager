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

    public static String validateVoucher(String voucherCode) {

        /*
        This method returns the voucher code which has been scanned via QR reader,
        in case the voucher code was found in the data base.
         */

        String validationResult = null;

        for (Voucher voucher : DBStorage.voucherList) {

            if (voucherCode.equals(voucher.getVoucherCode())) {

                validationResult = voucher.getVoucherCode();
                break;

            }

        }
        return validationResult;
    }

    public static Boolean validateIfVoucherVoid(String voucherCode) {

        /*
        This method returns a boolean which indicates if a voucher has already been void.
        This information is originally stored in the SQL Data Base.
        If a voucher is void, it is not valid respectively has already been used.
         */

        Boolean voucherVoid = false;

        for (Voucher voucher : DBStorage.voucherList) {

            if (voucherCode.equals(voucher.getVoucherCode())) {

                voucherVoid = voucher.isVoucherVoid();
                break;

            }
        }
        return voucherVoid;
    }
}
