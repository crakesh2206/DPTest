package com.naruto.dptest;

import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Codefingers-1 on 15-03-2017.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private List<Movie> moviesList;
    ArrayList<HashMap<String, String>> dataList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView dateView,dayView,mdOpenView ;//remain here

        public MyViewHolder(View view) {
            super(view);
            dateView = (TextView) view.findViewById(R.id._date);
            mdOpenView = (TextView) view.findViewById(R.id.m_d_open);
            dayView = (TextView) view.findViewById(R.id._day);
            //remain here
        }
    }


    public CustomAdapter(ArrayList<HashMap<String, String>> dataList) {
        this.dataList= dataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        HashMap<String, String> oneDaydata = dataList.get(position);
        holder.dateView.setText(oneDaydata.get(DBController.KEY_DATE));
        //remain here
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}