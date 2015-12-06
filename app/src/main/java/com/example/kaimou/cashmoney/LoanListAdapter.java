package com.example.kaimou.cashmoney;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.kaimou.cashmoney.model.Loan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import timber.log.Timber;

/**
 * Created by kaimou on 12/5/15.
 */
public class LoanListAdapter extends RecyclerView.Adapter<LoanListAdapter.LoanViewHolder> {
    private final List<Loan> loanObjects;
    private final Activity activity;

    public LoanListAdapter(Activity activity, List<Loan> loanObjects){
        this.activity = activity;
        this.loanObjects = loanObjects;
    }
    @Override
    public LoanViewHolder onCreateViewHolder (ViewGroup parent, int viewCase) {
        //Shouldn't be item scroll chat. Change later to the appropriate layout.
        int layout = R.layout.layout_row_item;
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new LoanViewHolder(v);

    }

    @Override
    public void onBindViewHolder (final LoanViewHolder viewHolder, int position) {

        Loan currLoan = loanObjects.get(position);
        viewHolder.setLoanDate(currLoan.getCreated_at());
        viewHolder.setLoanValue(currLoan.getAmount());

    }

    @Override
    public int getItemCount () {
        return loanObjects.size();
    }


    public class LoanViewHolder extends RecyclerView.ViewHolder {

        private final TextView loanDate;
        private final TextView loanValue;
        private final CheckBox checkedOrNot;


        public LoanViewHolder (View view) {
            super(view);
            //set the onclick listener in the constructor.
            loanDate = (TextView)view.findViewById(R.id.loanDate);
            loanValue = (TextView)view.findViewById(R.id.loanAmount);
            checkedOrNot = (CheckBox)view.findViewById(R.id.checkBox);
        }

        public void setLoanDate (String date) {
            //
            // loanDate.setText(date);
            Timber.i(date);
            Date temp= null;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try{
                temp = format.parse(date.replace("T", " "));
            }catch(ParseException e){
                e.printStackTrace();
            }



            loanDate.setText(temp.getMonth() + "");
        }

        public void setLoanValue(int rowHeader) {
            String value = String.valueOf(rowHeader);
            loanValue.setText(value);
        }



    }


}
