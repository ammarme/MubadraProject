package com.ammar.mubadraproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.ammar.mubadraproject.fragments.AboutUsFragment;
import com.ammar.mubadraproject.fragments.AmortizationFragment;
import com.ammar.mubadraproject.fragments.BlankFragment;
import com.ammar.mubadraproject.fragments.LoanFragment;
import com.ammar.mubadraproject.fragments.NoDataFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    ActionBarDrawerToggle actionBarDrawerToggle;
    LoanFragment loanFragment;
    AmortizationFragment amortizationFragment;
    NoDataFragment noDataFragment;
    AboutUsFragment aboutUsFragment;
    BlankFragment blankFragment;
///

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.mItem1);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


                switch (menuItem.getItemId()) {
                    case R.id.mItem1:
                        loanFragment = new LoanFragment();
                        openFragment(loanFragment);

                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.mItem2:

                        blankFragment = new BlankFragment();
                        openFragment(blankFragment);
                        overridePendingTransition(0, 0);
                        return true;


                    case R.id.mItem3:


                        aboutUsFragment = new AboutUsFragment();
                        openFragment(aboutUsFragment);
                        overridePendingTransition(0, 0);
                        return true;

                }
                return false;
            }
        });


        loanFragment = new LoanFragment();
        openFragment(loanFragment);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.amortization:
                if (loanFragment.validate()) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("mLoan", loanFragment.getLoan());

                    amortizationFragment = new AmortizationFragment();
                    amortizationFragment.setArguments(bundle);
                    openFragment(amortizationFragment);
                } else {
                    noDataFragment = new NoDataFragment();
                    openFragment(noDataFragment);
                }
                return true;

            case R.id.home:
                openFragment(loanFragment);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


    }

    private void openFragment(final Fragment fragment) {
        FragmentManager mgr = getFragmentManager();
        FragmentTransaction xact = mgr.beginTransaction();
        xact.replace(R.id.fragment_container, fragment);
        xact.addToBackStack(null);
        xact.commit();
    }











}
