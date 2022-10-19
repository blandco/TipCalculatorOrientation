package com.joelbland.tipcalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    public final static String MA = "MainActivity";

    private TipCalculator tipCalc;
    private NumberFormat money = NumberFormat.getCurrencyInstance();
    private EditText billEditText;
    private EditText tipEditText;
    private EditText guestsEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.w(MA,"Inside onCreate");
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        Configuration config = getResources().getConfiguration();
        modifyLayout(config);

        tipCalc = new TipCalculator(1f,1f, 1f);

    }

    private void modifyLayout(Configuration config) {
        Log.w(MA,"Inside modifyLayout");
        if(config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main_landscape);
        } else if (config.orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_main);
        }

        billEditText = findViewById(R.id.amount_bill);
        tipEditText = findViewById(R.id.amount_tip_percent);
        guestsEditText = findViewById(R.id.amount_guests);

        TextChangeHandler tch = new TextChangeHandler();
        billEditText.addTextChangedListener(tch);
        tipEditText.addTextChangedListener(tch);
        guestsEditText.addTextChangedListener(tch);

    }

    public void calculate() {

        String billString = billEditText.getText().toString();
        String tipString = tipEditText.getText().toString();
        String guestsString = guestsEditText.getText().toString();

        TextView tipTextView = findViewById(R.id.amount_tip);
        TextView totalTextView = findViewById(R.id.amount_total);
        TextView tipPerGuestTextView = findViewById(R.id.amount_tip_per_guest);
        TextView totalPerGuestTextView = findViewById(R.id.amount_total_per_guest);

        try {

            // convert strings to floats
            float billAmount = Float.parseFloat(billString);
            int tipPercent = Integer.parseInt(tipString);
            float guestsNum = Float.parseFloat(guestsString);

            // update Model
            tipCalc.setBill(billAmount);
            tipCalc.setTip(.01f * tipPercent);
            tipCalc.setGuests(guestsNum);

            // calculate in Model
            float tip = tipCalc.tipAmount();
            float total = tipCalc.totalAmount();
            float tipPerGuest = tipCalc.tipPerGuest();
            float totalPerGuest = tipCalc.totalPerGuest();

            // update View
            tipTextView.setText(money.format(tip));
            totalTextView.setText(money.format(total));
            tipPerGuestTextView.setText(money.format(tipPerGuest));
            totalPerGuestTextView.setText(money.format(totalPerGuest));

        } catch (NumberFormatException nfe) {
            // alert message
        }

    }


    private class TextChangeHandler implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            calculate();
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        Log.w(MA,"Inside onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
        modifyLayout(newConfig);
    }
}