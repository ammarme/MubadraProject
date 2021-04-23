package com.ammar.mubadraproject.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ammar.mubadraproject.R;
import com.ammar.mubadraproject.model.Loan;
import com.google.android.material.textfield.TextInputLayout;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;


public class LoanFragment extends Fragment {

    TextInputLayout etLoanAmount, etSalesTax, etEditInterest, etTerm, etDownPayment, etTradeIn, etFees;
    protected TextView tvLoanTotal, tvLoanInterestVal, tvMonthlyPaymentVal, tvLoanTotalCostVal;
    protected RadioButton rbYears, rbMonths;
    protected Button btnCalculate;
    protected ImageButton btnCalculatereset;


    protected double loanAmount, loanInterest, downPayment, tradeIn, fees;
    protected int terms;
    protected Loan loan;

    protected static final NumberFormat cf = NumberFormat.getCurrencyInstance(new Locale("AR", "EG"));
    View view;

    //
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (view != null) {
            if ((ViewGroup)view.getParent() != null)
                ((ViewGroup)view.getParent()).removeView(view);
            return view;
        }

        View view = inflater.inflate(R.layout.fragment_loann, container, false);
        if (savedInstanceState != null) {
            loanAmount = savedInstanceState.getInt("loanAmount", 0);
            loanInterest = savedInstanceState.getInt("loanInterest", 0);
            downPayment = savedInstanceState.getInt("downPayment", 0);
            tradeIn = savedInstanceState.getInt("tradeIn", 0);
            fees = savedInstanceState.getInt("fees", 0);
        }
        //load views
        btnCalculate = view.findViewById(R.id.btnCalculate);
        etDownPayment = view.findViewById(R.id.etDownPayment);
        etEditInterest = view.findViewById(R.id.etEditInterest);
        etFees = view.findViewById(R.id.etFees);
        etLoanAmount = view.findViewById(R.id.etLoanAmount);
        etSalesTax = view.findViewById(R.id.etSalesTax);
        etTerm = view.findViewById(R.id.etTerm);
        etTradeIn = view.findViewById(R.id.etTradeIn);
        rbMonths = view.findViewById(R.id.rbMonths);
        rbYears = view.findViewById(R.id.rbYears);
        tvLoanInterestVal = view.findViewById(R.id.tvLoanInterestVal);
        tvLoanTotal = view.findViewById(R.id.tvLoanTotal);
        tvLoanTotalCostVal = view.findViewById(R.id.tvLoanTotalCostVal);
        tvMonthlyPaymentVal = view.findViewById(R.id.tvMonthlyPaymentVal);
        btnCalculatereset = view.findViewById(R.id.reseticon);

        // OnCLickListeners
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gatherInputs();
                loan = new Loan(loanAmount, terms, loanInterest, downPayment, tradeIn, fees);

                tvMonthlyPaymentVal.setText(cf.format(loan.getMonthlyPayment()));
                tvLoanTotal.setText(cf.format(loan.getTotalLoanPayments()));
                tvLoanInterestVal.setText(cf.format(loan.getTotalLoanInterest()));
                tvLoanTotalCostVal.setText(cf.format(loan.getTotalCost()));
            }
        });

//        btnCalculate.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                reset();
//                return true;
//            }
//        });

        btnCalculatereset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });

        rbYears.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbMonths.setChecked(false);
            }
        });
        rbMonths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbYears.setChecked(false);
            }
        });

        return view;
    }

    protected void gatherInputs() {

        String loann = etLoanAmount.getEditText().getText().toString().trim();
        String Interest = etEditInterest.getEditText().getText().toString().trim();


        if (loann != null && loann.length() > 0) {
            loanAmount = Double.parseDouble(loann);
        } else {
            Toast.makeText(getView().getContext(), "أدخل قيمة القرض", Toast.LENGTH_SHORT).show();
        }


        if (Interest != null && Interest.length() > 0) {
            loanInterest = Double.parseDouble(Interest);
        } else {
            Toast.makeText(getView().getContext(), "أدخل قيمة الفائده", Toast.LENGTH_SHORT).show();
        }


        String DownPayment = etDownPayment.getEditText().getText().toString().trim();
        if (DownPayment != null && DownPayment.length() > 0) {
            downPayment = Double.parseDouble(DownPayment);

        } else {
            downPayment = 0;
        }


        String TradeIn = etTradeIn.getEditText().getText().toString().trim();

        if (TradeIn != null && TradeIn.length() > 0) {
            tradeIn = 0;
            tradeIn = Double.parseDouble(Interest);

        } else {
            tradeIn = 0;
        }

        String Feessst = etFees.getEditText().getText().toString().trim();

        if (Feessst != null && Feessst.length() > 0) {
            fees = Double.parseDouble(Feessst);

        } else {
            fees = 0;
        }


        String Termmm = etTerm.getEditText().getText().toString().trim();

        if (Termmm != null && Termmm.length() > 0) {
            if (rbYears.isChecked()) {
                terms = Integer.parseInt(etTerm.getEditText().getText().toString().trim());
            } else if (rbMonths.isChecked()) {
                terms = Integer.parseInt(etTerm.getEditText().getText().toString().trim()) / 12;
            } else {
                Toast.makeText(getActivity(), "Please select Years or Months", Toast.LENGTH_SHORT).show();
            }
        } else {

//            etTerm.setError("Please enter the term of the mLoan.");
/////            etTerm.setError("Please enter the term of the mLoan.");
            Toast.makeText(getView().getContext(), "أدخل قيمة", Toast.LENGTH_SHORT).show();

        }

    }

    public Loan getLoan() {
        return loan;
    }

    public boolean validate() {
        if (loanAmount == 0.0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Clear values from all input fields
     */
    protected void reset() {
        etDownPayment.getEditText().setText("");
        etEditInterest.getEditText().setText("");
        etFees.getEditText().setText("");
        etLoanAmount.getEditText().setText("");
        etTerm.getEditText().setText("");
        etTradeIn.getEditText().setText("");
        tvLoanInterestVal.setText("ج.م 0.00");
        tvLoanTotal.setText("ج.م 0.00");
        tvMonthlyPaymentVal.setText("ج.م 0.00");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("loanAmount", (Serializable) loanAmount);

        outState.putDouble("loanAmount", loanAmount);
        outState.putDouble("loanInterest", loanInterest);
        outState.putDouble("downPayment", downPayment);
        outState.putDouble("tradeIn", tradeIn);
        outState.putDouble("fees", fees);

    }

//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//        if (savedInstanceState != null) {
//            //probably orientation change
//            loanAmount = (double) savedInstanceState.getSerializable("loanAmount");
//        } else {
//                if (loanAmount != null) {
//                //returning from backstack, data is fine, do nothing
//            } else {
//                //newly created, compute data
//                myData = computeData();
//            }
////        loanAmount = savedInstanceState.getDouble("loanAmount");
////        loanInterest = savedInstanceState.getDouble("loanInterest");
////        downPayment = savedInstanceState.getDouble("downPayment");
////        tradeIn = savedInstanceState.getDouble("tradeIn");
////        fees = savedInstanceState.getDouble("fees");
//
//    }
}
