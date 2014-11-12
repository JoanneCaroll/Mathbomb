package com.example.mathbomb;

import java.util.Date;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
public class CustomListHighScore extends ArrayAdapter<String>{
	private final Activity context;
	private final String[] score;
	private final String[] date;
	
	public CustomListHighScore(Activity context,	String[] score , String[] date) {
		super(context, R.layout.highscore_single, score);
		this.context = context;
		this.score = score;
		this.date = date;
	}
	
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView= inflater.inflate(R.layout.highscore_single, null, true);
		TextView txtScore = (TextView) rowView.findViewById(R.id.score);
		TextView txtDate = (TextView) rowView.findViewById(R.id.date);
		txtScore.setText(score[position]);
		txtDate.setText(date[position]);
		return rowView;
	}
}