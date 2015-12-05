package com.example.kaimou.cashmoney;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

/**
 * Created by kaimou on 12/5/15.
 */
public class NumberPickerFragment extends Fragment {
    private NumberPicker numberPicker;
    private int maxAmountToLoan;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        maxAmountToLoan = savedInstanceState.getInt("MAX_LOAN_AMOUNT");
        View v = inflater.inflate(R.layout.loan_amount_numberpicker, container, false);
        numberPicker = (NumberPicker) v.findViewById(R.id.numberPicker1);
        numberPicker.setDisplayedValues(arrayGeneration(maxAmountToLoan));
        return v;


    }

    private String[] arrayGeneration(int amount){
        String[] valuesLoaned = new String[amount/5];
        int arrayCounter = 0;
        //Amount to loan can only be in increments of 5.
        for(int i = amount; i>0; i-=5){
            valuesLoaned[arrayCounter] = String.valueOf(amount);
            arrayCounter++;
        }
        return valuesLoaned;

    }
}
