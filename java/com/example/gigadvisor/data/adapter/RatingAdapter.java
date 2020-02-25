package com.example.gigadvisor.data.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.gigadvisor.R;
import com.example.gigadvisor.data.model.Rating;

import java.util.List;

public class RatingAdapter extends ArrayAdapter<Rating> {

    private List <Rating> ratingList;
    private Context context;

    public RatingAdapter(List<Rating> r, Context c){
        super(c, R.layout.list_rating, r);
        this.ratingList= r;
        this.context=c;
    }

    public View getView (int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.list_rating,null,true);

        TextView titleRating = (TextView) view.findViewById(R.id.titleViewRating);
        TextView dateRating =(TextView) view.findViewById(R.id.dataViewrating);
        TextView descriptionRating = (TextView) view.findViewById(R.id.descriptionViewRating);

        Rating rating = ratingList.get(position);

        titleRating.setText(rating.getTitolo());
        dateRating.setText(rating.getData());
        descriptionRating.setText(rating.getDescrizione());

        return view;

    }
}
