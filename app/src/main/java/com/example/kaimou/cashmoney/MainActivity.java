package com.example.kaimou.cashmoney;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by kaimou on 12/5/15.
 */
public class MainActivity extends Activity {
    private TextView loanAmount;
    private int maxAmountToLoan = 50; //Max amount to loan to this person. For test purposes, this is set to 50.

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        loanAmount = (TextView) findViewById(R.id.make_a_loan_textview);
        loanAmount.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Bundle bundle = new Bundle();
                bundle.putInt("MAX_LOAN_AMOUNT", maxAmountToLoan);
                NumberPickerFragment npFragment = new NumberPickerFragment();
                npFragment.setArguments(bundle);
            }
        });
    }




}
