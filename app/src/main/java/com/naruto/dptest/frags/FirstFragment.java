package com.naruto.dptest.frags;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.naruto.dptest.DBController;
import com.naruto.dptest.R;

import java.util.HashMap;

/**
 * Created by Codefingers-1 on 29-03-2017.
 */

public class FirstFragment extends Fragment {
    // Store instance variables
    private String title;
    private int page;
    HashMap<String, String> stringStringHashMap;
    public FirstFragment(HashMap<String, String> stringStringHashMap){
        this.stringStringHashMap = stringStringHashMap;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listitem, container, false);
        TextView tvDay= (TextView) view.findViewById(R.id._day);
        TextView tvDate = (TextView) view.findViewById(R.id._date);

        TextView tvMDopen = (TextView) view.findViewById(R.id.m_d_open);
        TextView tvMDopen_digit = (TextView) view.findViewById(R.id.m_d_open_digt);
        TextView tvMDclose = (TextView) view.findViewById(R.id.m_d_close);
        TextView tvMDclose_digit = (TextView) view.findViewById(R.id.m_d_close_digt);

        TextView tvKLopen = (TextView) view.findViewById(R.id.k_l_open);
        TextView tvKLopendigit = (TextView) view.findViewById(R.id.k_l_open_digt);
        TextView tvKLclose = (TextView) view.findViewById(R.id.k_l_close);
        TextView tvKLclose_digit = (TextView) view.findViewById(R.id.k_l_close_digt);

        TextView tvMNopen = (TextView) view.findViewById(R.id.m_n_open);
        TextView tvMNopen_digit = (TextView) view.findViewById(R.id.m_n_open_digt);
        TextView tvMNclose = (TextView) view.findViewById(R.id.m_n_close);
        TextView tvMNclose_digit = (TextView) view.findViewById(R.id.m_n_close_digt);

        TextView tvMMopen = (TextView) view.findViewById(R.id.m_m_open);
        TextView tvMMopen_digit = (TextView) view.findViewById(R.id.m_m_open_digt);
        TextView tvMMclose = (TextView) view.findViewById(R.id.m_m_close);
        TextView tvMMclose_digit = (TextView) view.findViewById(R.id.m_m_close_digt);


        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/StencilStd.otf");




        tvDay.setText(stringStringHashMap.get(DBController.KEY_DAYOFMONTH));
        tvDate.setText(stringStringHashMap.get(DBController.KEY_DATE));

        tvMDopen.setText(stringStringHashMap.get(DBController.KEY_MDOPEN));
        tvMDopen_digit.setText(stringStringHashMap.get(DBController.KEY_MDOPEN_DIGIT));
     //   tvMDclose.setText(stringStringHashMap.get(DBController.KEY_MDCLOSE));
          tvMDclose.setText("2\n5\n6");
        tvMDclose_digit.setText(stringStringHashMap.get(DBController.KEY_MDCLOSE_DIGIT));

        tvKLopen.setText(stringStringHashMap.get(DBController.KEY_KL_OPEN));
        tvKLopendigit.setText(stringStringHashMap.get(DBController.KEY_KL_OPEN_DIGIT));
        tvKLclose.setText(stringStringHashMap.get(DBController.KEY_KL_CLOSE));
        tvKLclose_digit.setText(stringStringHashMap.get(DBController.KEY_KL_CLOSE_DIGIT));

        tvMNopen.setText(stringStringHashMap.get(DBController.KEY_MNOPEN));
        tvMNopen_digit.setText(stringStringHashMap.get(DBController.KEY_MNOPEN_DIGIT));
        tvMNclose.setText(stringStringHashMap.get(DBController.KEY_MNCLOSE));
        tvMNclose_digit.setText(stringStringHashMap.get(DBController.KEY_MNCLOSE_DIGIT));

        tvMMopen.setText(stringStringHashMap.get(DBController.KEY_MUM_OPEN));
        tvMMopen_digit.setText(stringStringHashMap.get(DBController.KEY_MUM_OPEN_DIGIT));
        tvMMclose.setText(stringStringHashMap.get(DBController.KEY_MUM_CLOSE));
        tvMMclose_digit.setText(stringStringHashMap.get(DBController.KEY_MUM_CLOSE_DIGIT));

        tvMDopen.setTypeface(custom_font);
        tvMDclose.setTypeface(custom_font);
        tvMDopen_digit.setTypeface(custom_font);
        tvMDclose_digit.setTypeface(custom_font);
        tvMDopen_digit.setTextColor(getResources().getColor(R.color.pink));
        tvMDclose_digit.setTextColor(getResources().getColor(R.color.pink));
        tvKLopen.setTypeface(custom_font);
        tvKLclose.setTypeface(custom_font);
        tvKLopendigit.setTypeface(custom_font);
        tvKLclose_digit.setTypeface(custom_font);
        tvKLopendigit.setTextColor(getResources().getColor(R.color.perpal));
        tvKLclose_digit.setTextColor(getResources().getColor(R.color.perpal));

        tvMNopen.setTypeface(custom_font);
        tvMNclose.setTypeface(custom_font);
        tvMNopen_digit.setTypeface(custom_font);
        tvMNclose_digit.setTypeface(custom_font);
        tvMNopen_digit.setTextColor(getResources().getColor(R.color.pink));
        tvMNclose_digit.setTextColor(getResources().getColor(R.color.pink));

        tvMMopen.setTypeface(custom_font);
        tvMMclose.setTypeface(custom_font);
        tvMMopen_digit.setTypeface(custom_font);
        tvMMclose_digit.setTypeface(custom_font);
        tvMMopen_digit.setTextColor(getResources().getColor(R.color.perpal));
        tvMMclose_digit.setTextColor(getResources().getColor(R.color.perpal));

//        YoYo.with(Techniques.Swing).duration(1000).repeat(5).playOn(tvMDopen_digit);
//        YoYo.with(Techniques.Swing).duration(1000).repeat(5).playOn(tvMDclose_digit);
//
//        YoYo.with(Techniques.Swing).duration(1000).repeat(5).playOn(tvKLopendigit);
//        YoYo.with(Techniques.Swing).duration(1000).repeat(5).playOn(tvKLclose_digit);
//
//        YoYo.with(Techniques.Swing).duration(1000).repeat(5).playOn(tvMNopen_digit);
//        YoYo.with(Techniques.Swing).duration(1000).repeat(5).playOn(tvMNclose_digit);
//
//        YoYo.with(Techniques.Swing).duration(1000).repeat(5).playOn(tvMMopen_digit);
//        YoYo.with(Techniques.Swing).duration(1000).repeat(5).playOn(tvMMclose_digit);






        return view;
    }
}
