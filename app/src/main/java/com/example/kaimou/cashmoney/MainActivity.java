package com.example.kaimou.cashmoney;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.kaimou.cashmoney.model.Loan;
import com.example.kaimou.cashmoney.model.User;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.okhttp.OkHttpClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by kaimou on 12/5/15.
 */
public class MainActivity extends Activity implements NumberPicker.OnValueChangeListener {
    private TextView loanAmount;
    private RecyclerView loanRecyclerView;
    AsyncHttpClient client = new AsyncHttpClient();
    private ArrayList<Loan> loanList;
    private LoanListAdapter mAdapter;
    private int maxAmountToLoan; //Max amount to loan to this person. For test purposes, this is set to 50.

    public static final String BASE_URL_LOAN_LIST = "http://10.9.104.253:3000/api/users/";

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        loanRecyclerView = (RecyclerView)findViewById(R.id.paymentListView1);
        maxAmountToLoan = User.getCurrentUser().getTotalCreditLine();
        loadLoanList();
        loanAmount = (TextView) findViewById(R.id.make_a_loan_textview);
        loanAmount.setText(Integer.toString(maxAmountToLoan));
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
        final String[] scrollContent = arrayGeneration(maxAmountToLoan);

        np.setMaxValue(scrollContent.length - 1);
        np.setMinValue(0);
        np.setDisplayedValues(scrollContent);
        np.setOnValueChangedListener(this);
        okButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                loanAmount.setText(String.valueOf(scrollContent[np.getValue()]));
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
            valuesLoaned[arrayCounter] = String.valueOf(i);
            arrayCounter++;
        }
        return valuesLoaned;

    }

    private void loadLoanList(){
        String tempURL = BASE_URL_LOAN_LIST + User.getCurrentUser().get_id()+"/get-loans";
        client.get(tempURL, new AsyncHttpResponseHandler() {
            Loan[] loans;
            ArrayList<Loan> arrayList;
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                Log.d("loans", response);
                Gson gson = new Gson();
                loans = gson.fromJson(response, Loan[].class);
                arrayList = new ArrayList<Loan>(Arrays.asList(loans));

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
            @Override
            public void onFinish(){
                loadRecyclerView(arrayList);
            }

        });
    }

    private void loadRecyclerView(ArrayList<Loan> list){
        this.loanList = list;
        mAdapter = new LoanListAdapter(this, loanList);
        loanRecyclerView.setLayoutManager(new org.solovyev.android.views.llm.LinearLayoutManager(this));
        loanRecyclerView.setAdapter(mAdapter);

    }









}
