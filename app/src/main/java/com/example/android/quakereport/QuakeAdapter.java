package com.example.android.quakereport;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by HAMZA AIT BAALI on 3/25/2018.
 */

public class QuakeAdapter extends ArrayAdapter<String>{

    ArrayList<String> mRang;
    ArrayList<String> mLocat;
    ArrayList<String> mDate;
    Context mContext;
    LayoutInflater inflater;

    public QuakeAdapter(Context mContext, ArrayList<String> mRang, ArrayList<String> mLocat, ArrayList<String> mDate){
        super(mContext, R.layout.list_itemquake, mLocat);

        this.mDate = mDate;
        this.mLocat= mLocat;
        this.mRang=mRang;
        this.mContext = mContext;
    }


    public class ViewHolder{

        TextView Rang;
        TextView Location;
        TextView Date;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_itemquake, null);
        }



        final ViewHolder viewHolder = new ViewHolder();

        viewHolder.Rang = convertView.findViewById(R.id.rang);
        TextView Range = (TextView) convertView.findViewById(R.id.rang);
        viewHolder.Location = convertView.findViewById(R.id.location);
        viewHolder.Date = convertView.findViewById(R.id.date);

        //view
        GradientDrawable magnitudeCircle = (GradientDrawable) Range.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(Double.parseDouble(mRang.get(position)));

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        //date
        long val = Long.valueOf(mDate.get(position));
        Date dateObject = new Date(val);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateToDisplay = dateFormatter.format(dateObject);

        //setText
        viewHolder.Rang.setText(mRang.get(position).toString());
        viewHolder.Location.setText(mLocat.get(position));
        viewHolder.Date.setText(dateToDisplay);


        return convertView;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
