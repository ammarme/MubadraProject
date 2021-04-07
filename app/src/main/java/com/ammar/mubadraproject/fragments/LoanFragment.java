package com.ammar.mubadraproject.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ammar.mubadraproject.R;
import com.ammar.mubadraproject.model.Loan;

import java.text.NumberFormat;
import java.util.Locale;


public class LoanFragment extends Fragment {
    protected EditText etTerm, etEditInterest, etLoanAmount, etDownPayment, etSalesTax, etTradeIn, etFees;
    protected TextView tvLoanTotal, tvLoanInterestVal, tvMonthlyPaymentVal, tvLoanTotalCostVal;
    protected RadioButton rbYears, rbMonths;
    protected Button btnCalculate;
    protected ImageButton btnCalculatereset;


    protected double loanAmount, loanInterest, salesTax, downPayment, tradeIn, fees;
    protected int terms;
    protected Loan loan;

    protected static final NumberFormat cf = NumberFormat.getCurrencyInstance(new Locale("AR", "EG"));

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loan, container, false);

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
                loan = new Loan(loanAmount, terms, loanInterest, downPayment, tradeIn, salesTax, fees);

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

        if (etSalesTax.getText().toString().length() == 0) {
            etSalesTax.setError("Please enter the sales tax applied for the purchase.");
        } else {
            salesTax = Double.parseDouble(etSalesTax.getText().toString());
        }

        if (etDownPayment.getText().toString().trim().length() == 0) {
            downPayment = 0;
        } else {
            downPayment = Double.parseDouble(etDownPayment.getText().toString());
        }

        if (etTradeIn.getText().toString().trim().length() == 0) {
            tradeIn = 0;
        } else {
            tradeIn = Double.parseDouble(etTradeIn.getText().toString());
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
        etSalesTax.setText("");
        etTerm.setText("");
        etTradeIn.setText("");
        tvLoanInterestVal.setText("EGP 0.00");
        tvLoanTotal.setText("EGP 0.00");
        tvMonthlyPaymentVal.setText("EGP 0.00");
    }
}
