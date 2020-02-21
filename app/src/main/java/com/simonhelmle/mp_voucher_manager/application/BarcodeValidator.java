package com.simonhelmle.mp_voucher_manager.application;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.simonhelmle.mp_voucher_manager.R;
import com.simonhelmle.mp_voucher_manager.data.Voucher;
import com.simonhelmle.mp_voucher_manager.databinding.ActivityBarcodeValidatorBinding;

public class BarcodeValidator extends AppCompatActivity {

    private ActivityBarcodeValidatorBinding binding;
    Bundle extras = getIntent().getExtras();
    final String voucherCode = extras.getString("voucherCode");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_validator);
        binding = ActivityBarcodeValidatorBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        initViews();
    }

    private void initViews() {

        binding.barcodeResult.setText("Gutschein ID: " + voucherCode);

        if (Voucher.validateVoucher(voucherCode) != null) {

            binding.voucherStatusValidity.setText("Gutschein Code ist gültig.");
            binding.voucherStatusValidity.setTextColor(Color.GREEN);
            binding.barcodeResult.setTypeface(null, Typeface.BOLD);

            if (Voucher.validateIfVoucherVoid(voucherCode)) {

                binding.voucherStatusVoided.setText("Gutschein bereits entwertet.");
                binding.voucherStatusVoided.setTextColor(Color.RED);
            } else {
                binding.voucherStatusVoided.setText("Gutschein noch nicht entwertet.");
                binding.voucherStatusVoided.setTextColor(Color.GREEN);
            }
        } else {

            binding.voucherStatusValidity.setText("Gutschein Code ist ungültig.");
            binding.voucherStatusValidity.setTextColor(Color.RED);
            binding.barcodeResult.setTypeface(null, Typeface.BOLD);

            binding.voucherStatusVoided.setText("-");
        }
    }
}

