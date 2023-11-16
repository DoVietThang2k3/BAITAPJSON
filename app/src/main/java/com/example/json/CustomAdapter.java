package com.example.json;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Custom> {
    Activity context;
    int resource;
    List<Custom> objects;
    public CustomAdapter(@NonNull Activity context, int resource, @NonNull List<Custom> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        convertView = inflater.inflate(this.resource,null);
        ImageView thumbnail = convertView.findViewById(R.id.image);
        TextView title = convertView.findViewById(R.id.title);
        TextView branch = convertView.findViewById(R.id.branch);
        TextView description = convertView.findViewById(R.id.description);
        TextView price = convertView.findViewById(R.id.price);
        Custom custom = this.objects.get(position);
        Picasso.get().load(custom.getThumbnail()).into(thumbnail);
        title.setText(custom.getTitle());
        branch.setText(custom.getBranch());
        description.setText(custom.getDescription());
        price.setText("Price: " + custom.getPrice());
        return convertView;
    }
}
