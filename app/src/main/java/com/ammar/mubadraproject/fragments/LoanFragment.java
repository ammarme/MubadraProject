package com.ammar.mubadraproject.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
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

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;


public class LoanFragment extends Fragment {
    protected EditText etTerm, etEditInterest, etLoanAmount, etDownPayment, etSalesTax, etTradeIn, etFees;
    protected TextView tvLoanTotal, tvLoanInterestVal, tvMonthlyPaymentVal, tvLoanTotalCostVal;
    protected RadioButton rbYears, rbMonths;
    protected Button btnCalculate;
    ImageButton imageButton;
    protected double loanAmount, loanInterest, salesTax, downPayment, tradeIn, fees;
    protected int terms;
    protected Loan loan;
    protected static final NumberFormat cf = NumberFormat.getCurrencyInstance(new Locale("AR", "EG"));

//    protected static final NumberFormat cf = NumberFormat.getCurrencyInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loannconstrain, container, false);

        //load views
        imageButton = view.findViewById(R.id.resetbtn);
        btnCalculate = view.findViewById(R.id.btnCalculate);
        etDownPayment = view.findViewById(R.id.etDownPayment);
        etEditInterest = view.findViewById(R.id.etEditInterest);
        etFees = view.findViewById(R.id.etFees);
        etLoanAmount = view.findViewById(R.id.etLoanAmount);
        etTerm = view.findViewById(R.id.etTerm);
        rbMonths = view.findViewById(R.id.rbMonths);
        rbYears = view.findViewById(R.id.rbYears);
        tvLoanInterestVal = view.findViewById(R.id.tvLoanInterestVal);
        tvLoanTotal = view.findViewById(R.id.tvLoanTotal);
        tvLoanTotalCostVal = view.findViewById(R.id.tvLoanTotalCostVal);
        tvMonthlyPaymentVal = view.findViewById(R.id.tvMonthlyPaymentVal);

        // OnCLickListeners

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gatherInputs();
                loan = new Loan(loanAmount, terms, loanInterest, downPayment, tradeIn, fees);

                tvMonthlyPaymentVal.setText(cf.format(loan.getMonthlyPayment()));
//                tvLoanTotal.setText(cf.format(loan.getTotalLoanPayments()));
                tvLoanInterestVal.setText(cf.format(loan.getTotalLoanInterest()));
                tvLoanTotalCostVal.setText(cf.format(loan.getTotalCost()));
                Toast.makeText(view.getContext(),   cf.format(loan.getTotalCost()) +"القيمة الاجماليه للقرض\n"+"اضغط علي الايقونه بالاعلي لعرض جدول المدفوعات السنوي\n ", Toast.LENGTH_LONG).show();

            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });
        btnCalculate.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(view.getContext(), "شكرا لك ", Toast.LENGTH_SHORT).show();
                return true;
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
        if (etLoanAmount.getText().toString().length() == 0) {
            etLoanAmount.setError("Please enter the amount of the mLoan.");
        } else {
            loanAmount = Double.parseDouble(etLoanAmount.getText().toString());
        }


        if (etEditInterest.getText().toString().length() == 0) {
            etEditInterest.setError("Please enter the interest rate for the mLoan.");
        } else {
            loanInterest = Double.parseDouble(etEditInterest.getText().toString());
        }


        if (etDownPayment.getText().toString().trim().length() == 0) {
            downPayment = 0;
        } else {
            downPayment = Double.parseDouble(etDownPayment.getText().toString());
        }


        if (etFees.getText().toString().trim().length() == 0) {
            fees = 0;
        } else {
            fees = Double.parseDouble(etFees.getText().toString());
        }

        if (etTerm.getText().toString().length() == 0) {
            etTerm.setError("Please enter the term of the mLoan.");
        } else {
            if (rbYears.isChecked()) {
                terms = Integer.parseInt(etTerm.getText().toString());
            } else if (rbMonths.isChecked()) {
                terms = Integer.parseInt(etTerm.getText().toString()) / 12;
            } else {
                Toast.makeText(getActivity(), "Please select Years or Months", Toast.LENGTH_SHORT).show();
            }
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
        etDownPayment.setText("");
        etEditInterest.setText("");
        etFees.setText("");
        etLoanAmount.setText("");
        etTerm.setText("");
        tvLoanInterestVal.setText("$0.00");
//        tvLoanTotal.setText("$0.00");
        tvMonthlyPaymentVal.setText("$0.00");
    }
}
