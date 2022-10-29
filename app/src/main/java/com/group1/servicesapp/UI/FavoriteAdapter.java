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
import com.group1.servicesapp.objects.Favorite;

import java.util.ArrayList;

public class FavoriteAdapter extends ArrayAdapter<Favorite> {
    private static final String TAG="FavoriteAdapter";
    private Context nContext;
    private int nResource;
    public FavoriteAdapter(Context context, int resource, ArrayList<Favorite> objects) {
        super(context, resource, objects);
        nContext=context;
        nResource=resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String uName = getItem(position).getUserName();
        String service = getItem(position).getServiceName();

        Favorite favorite =new Favorite(uName,service);
        LayoutInflater inflater =LayoutInflater.from(nContext);
        convertView=inflater.inflate(nResource,parent,false);

        TextView tvFavoriteServiceName = (TextView) convertView.findViewById(R.id.favoriteServiceName);

        tvFavoriteServiceName.setText(service);


        return convertView;
    }
}
