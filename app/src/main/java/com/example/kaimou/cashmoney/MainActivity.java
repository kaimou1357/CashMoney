package com.example.kaimou.cashmoney;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

/**
 * Created by kaimou on 12/5/15.
 */
public class MainActivity extends Activity implements NumberPicker.OnValueChangeListener {
    private TextView loanAmount;
    static Dialog d;
    private int maxAmountToLoan = 50; //Max amount to loan to this person. For test purposes, this is set to 50.

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        loanAmount = (TextView) findViewById(R.id.make_a_loan_textview);
        loanAmount.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                showNumberPicker();
            }
        });
    }

    public void onValueChange(NumberPicker picker, int oldVal, int newVal){
        //this is useless.

    }

    private void showNumberPicker(){
        final Dialog d = new Dialog(MainActivity.this);
        d.setTitle("Loan Amount");
        d.setContentView(R.layout.loan_amount_numberpicker);
        Button okButton = (Button)d.findViewById(R.id.button1);
        Button cancelButton = (Button)d.findViewById(R.id.button2);
        final NumberPicker np = (NumberPicker)d.findViewById(R.id.numberPicker1);
        String[] test = {"1", "2" , "3"};

        np.setMaxValue(maxAmountToLoan/5);
        np.setMinValue(0);
        np.setDisplayedValues(arrayGeneration(maxAmountToLoan));
        np.setOnValueChangedListener(this);
        okButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                loanAmount.setText(String.valueOf(np.getValue()));
                d.dismiss();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                d.dismiss();
            }
        });
        d.show();


    }

    private String[] arrayGeneration(int amount){
        String[] valuesLoaned = new String[amount/5];
        int arrayCounter = 0;
        //Amount to loan can only be in increments of 5.
        for(int i = amount; i>0; i= i - 5){
            valuesLoaned[arrayCounter] = String.valueOf(amount);
            arrayCounter++;
        }
        return valuesLoaned;

    }




}
