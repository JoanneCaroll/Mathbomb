package com.example.mathbomb;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListCategory extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] category;
    private final Integer[] image;
    private TextView txtTitle;
    private ImageView imgView;

    public ListCategory(Activity context, String[] category, Integer[] image) {
        super(context, R.layout.list_single, category);
        this.context = context;
        this.category = category;
        this.image = image;
    }

    @SuppressLint({ "InflateParams", "ViewHolder" })
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_single, null, true);
        txtTitle = (TextView) rowView.findViewById(R.id.categtxt);
        imgView = (ImageView) rowView.findViewById(R.id.categimg);
        txtTitle.setText(category[position]);
        imgView.setImageResource(image[position]);
        return rowView;
    }
}