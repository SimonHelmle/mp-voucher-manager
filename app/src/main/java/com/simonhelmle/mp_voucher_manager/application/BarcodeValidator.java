package com.simonhelmle.mp_voucher_manager.application;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.simonhelmle.mp_voucher_manager.R;
import com.simonhelmle.mp_voucher_manager.data.Voucher;
import com.simonhelmle.mp_voucher_manager.databinding.ActivityBarcodeValidatorBinding;

public class BarcodeValidator extends AppCompatActivity {

    private ActivityBarcodeValidatorBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_validator);
        binding = ActivityBarcodeValidatorBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Bundle extras = getIntent().getExtras();
        final String voucherCode = extras.getString("voucherCode");

        initViews();
    }

    //TODO: Here a proper override for onResume should be implemented. Doesnt work with code below.
//    @Override
//    protected void onResume() {
//        super.onResume();
//        startActivity(new Intent(BarcodeValidator.this, BarcodeScanner.class));
//    }

    private void initViews() {

        Bundle extras = getIntent().getExtras();
        final String voucherCode = extras.getString("voucherCode");

        binding.barcodeResult.setText("Gutschein ID: " + voucherCode);

        if (Voucher.validateVoucher(voucherCode) != null) {

            binding.voucherStatusValidity.setText("Gutschein Code in Datenbank gefunden.");
            binding.voucherStatusValidity.setTextColor(Color.GREEN);
            binding.barcodeResult.setTypeface(null, Typeface.BOLD);

            if (Voucher.validateIfVoucherVoid(voucherCode)) {

                binding.voucherStatusVoided.setText("Gutschein wurde bereits am " + Voucher.getVoucherVoidDate(voucherCode) + " entwertet.");
                binding.voucherStatusVoided.setTextColor(Color.RED);
                binding.statusGraphic.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_smiley_warning, null));
                binding.btnBarcodeVoidVoucher.setText("Weiteren Gutschein einlesen.");
                binding.btnBarcodeVoidVoucher.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        startActivity(new Intent(BarcodeValidator.this, BarcodeScanner.class));
                    }
                });
            } else {
                binding.voucherStatusVoided.setText("Gutschein noch nicht entwertet.");
                binding.voucherStatusVoided.setTextColor(Color.GREEN);
                binding.statusGraphic.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_smiley_success, null));
            }
        } else {

            binding.voucherStatusValidity.setText("Gutschein Code ist ungültig.");
            binding.voucherStatusValidity.setTextColor(Color.RED);
            binding.barcodeResult.setTypeface(null, Typeface.BOLD);
            binding.statusGraphic.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_smiley_error, null));

            binding.voucherStatusVoided.setText("-");
        }
    }
}

