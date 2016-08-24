package com.example.raj.mytest.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.raj.mytest.Model.AndroidVersion;
import com.example.raj.mytest.R;

import java.util.ArrayList;

/**
 * Created by Raj on 8/22/2016.
 */

public class AndroidVersionAdapter extends RecyclerView.Adapter<AndroidVersionAdapter.ViewHolder> {
    private ArrayList<AndroidVersion> android;

    public AndroidVersionAdapter(ArrayList<AndroidVersion> android) {
        this.android = android;
    }

    @Override
    public AndroidVersionAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_android, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AndroidVersionAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tv_name.setText(android.get(i).getName());
        viewHolder.tv_version.setText(android.get(i).getVer());
        viewHolder.tv_api_level.setText(android.get(i).getApi());
    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_version, tv_api_level;

        public ViewHolder(View view) {
            super(view);

            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_version = (TextView) view.findViewById(R.id.tv_version);
            tv_api_level = (TextView) view.findViewById(R.id.tv_api_level);

        }
    }

}
