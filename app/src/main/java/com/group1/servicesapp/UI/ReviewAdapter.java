package com.group1.servicesapp.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.group1.servicesapp.R;
import com.group1.servicesapp.objects.Review;

import java.util.ArrayList;

public class ReviewAdapter extends ArrayAdapter<Review> {
private static final String TAG="ReviewAdapter";
private Context nContext;
    private int nResource;
    public ReviewAdapter(Context context, int resource, ArrayList<Review> objects) {
        super(context, resource, objects);
        nContext=context;
        nResource=resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String uName = getItem(position).getUserName();
        String service = getItem(position).getServiceName();
        String rating = getItem(position).getRating();
        String message = getItem(position).getMessage();

        Review review =new Review(uName,service,rating,message);
        LayoutInflater inflater =LayoutInflater.from(nContext);
        convertView=inflater.inflate(nResource,parent,false);

        TextView tvUserName = (TextView) convertView.findViewById(R.id.textViewUserName);
        TextView tvRating = (TextView) convertView.findViewById(R.id.textViewRating);
        TextView tvMessage = (TextView) convertView.findViewById(R.id.textViewMessage);

        tvUserName.setText(uName);
        tvRating.setText(rating);
        tvMessage.setText(message);

        return convertView;
    }
}
