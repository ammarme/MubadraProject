package com.ammar.mubadraproject.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ammar.mubadraproject.R;
import com.ammar.mubadraproject.model.AmortizationItem;

import java.text.NumberFormat;
import java.util.Locale;



public class AmortizationAdapter extends BaseAdapter {
    private Activity mContext;
    private AmortizationItem[] mList;
    private NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("AR", "EG"));

    public AmortizationAdapter(Activity context, AmortizationItem[] list) {
        mContext = context;
        mList = list;
    }

    //
    @Override
    public int getCount() {
        return mList.length;
    }

    @Override
    public Object getItem(int position) {
        return mList[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AmortizationListView amortizationListView;
        View v = convertView;

        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.item_amortization_list, null);
            amortizationListView = new AmortizationListView(v);
            v.setTag(amortizationListView);
        } else {
            amortizationListView = (AmortizationListView) v.getTag();
        }

        amortizationListView.tvMonth.setText("" + mList[position].getMonth());
        amortizationListView.tvBeginningBalance.setText(nf.format(mList[position].getBeginningBalance()));
        amortizationListView.tvInterest.setText(nf.format(mList[position].getInterest()));
        amortizationListView.tvPrincipal.setText(nf.format(mList[position].getPrincipal()));
        amortizationListView.tvEndingBalance.setText(nf.format(mList[position].getEndingBalance()));

        return v;
    }

    class AmortizationListView {
        public TextView tvMonth, tvBeginningBalance, tvInterest, tvPrincipal, tvEndingBalance;

        public AmortizationListView(View base) {
            tvMonth = base.findViewById(R.id.tvMonth);
            tvBeginningBalance = base.findViewById(R.id.tvBeginningBalance);
            tvInterest = base.findViewById(R.id.tvInterest);
            tvPrincipal = base.findViewById(R.id.tvPrincipal);
            tvEndingBalance = base.findViewById(R.id.tvEndingBalance);
        }
    }
}


