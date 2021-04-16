
package com.ammar.mubadraproject.fragments;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.res.ResourcesCompat;

import com.ammar.mubadraproject.R;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutUsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);


        Element versionElement = new Element();
        versionElement.setTitle("Version 1.0.1");
        final Typeface face = ResourcesCompat.getFont(getContext(), R.font.des);

        View aboutPage = new AboutPage(view.getContext())
                .isRTL(false)
                .enableDarkMode(false)
                .setCustomFont(face) // or Typeface
                .setImage(R.mipmap.ic_launcher)
                .setDescription("مبادره البنك المركزي للتمويل العقاري الجديده ٢٠٢١( بفائده ٣٪\u061C فقط ) لمحدودي ومتوسطي الدخل ( موظفين - اعمال حره ) تقسيط حتى ٣٠ سنه ( بمقدم يبدأ من ١٠٪\u061C يقسط على ٣ سنوات ) والوحده السكنيه من اختيارك انت في اي مكان ")
                .addItem(versionElement)
                .addGroup("Connect with us")
                .addEmail("ammar.yasser.yossef.altabaa@gmail.com")
                .addWebsite("https://play.google.com/store/apps/details?id=com.createegypthatly.hatly/")

                .addPlayStore("com.ammar.mubadraproject")

                .create();
        return aboutPage;

    }


}