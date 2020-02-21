package com.simonhelmle.mp_voucher_manager.data;

public class Voucher {

    private int id;
    private String voucherCode;
    private String voucherCreationDate;
    private int voucherVoid;
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

    public static Boolean validateIfVoucherVoid(String voucherCode) {

        /*
        This method returns a boolean which indicates if a voucher has already been void.
        This information is originally stored in the SQL Data Base.
        If a voucher is void, it is not valid respectively has already been used.
         */

        Boolean voucherVoid = false;

        for (Voucher voucher : DBStorage.voucherList) {

            if (voucherCode.equals(voucher.getVoucherCode())) {

                voucherVoid = voucher.getVoucherVoid() != 0;

                break;
            }
        }
        return voucherVoid;
    }

    public static String getVoucherVoidDate(String voucherCode) {

        /*
        This method returns the voucher void date for a specific voucher code.
        This is the date on which the voucher has been used.
         */

        String voucherVoidDate = null;

        for (Voucher voucher : DBStorage.voucherList) {

            if (voucherCode.equals(voucher.getVoucherCode())) {

                voucherVoidDate = voucher.getVoucherVoidDate();
                break;
            }
        }
        return voucherVoidDate;
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

    public int getVoucherVoid() {
        return voucherVoid;
    }

    public void setVoucherVoid(int voucherVoid) {
        this.voucherVoid = voucherVoid;
    }
}
