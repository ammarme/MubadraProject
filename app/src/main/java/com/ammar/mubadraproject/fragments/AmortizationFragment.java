package com.ammar.mubadraproject.fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.ammar.mubadraproject.Adapter.AmortizationAdapter;
import com.ammar.mubadraproject.R;
import com.ammar.mubadraproject.model.Loan;


public class AmortizationFragment extends Fragment
{
    protected Loan mLoan;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        mLoan = (Loan)bundle.getSerializable("mLoan");
    }
//
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_amortization, container, false);
        view.setBackgroundColor(Color.WHITE);

        ListView list = view.findViewById(R.id.amortization_list);

        final AmortizationAdapter amortizationAdapter = new AmortizationAdapter(getActivity(), mLoan.getAmortizationItems());
        list.setAdapter(amortizationAdapter);

        return view;
    }
}
