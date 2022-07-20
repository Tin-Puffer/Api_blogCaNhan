package com.example.myapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Model.Blog;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterBlog extends ArrayAdapter<Blog> {
    Activity context;
    int resource;
    @NonNull List<Blog> objects;
    public AdapterBlog(@NonNull Activity context, int resource, @NonNull List<Blog> objects) {
        super(context, resource, objects);
        this.objects = objects;
        this.context= context;
        this.resource=resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater=this.context.getLayoutInflater();
        convertView= layoutInflater.inflate(this.resource,null);
        TextView title= convertView.findViewById(com.example.myapplication.R.id.prd_name);
        TextView decript= convertView.findViewById(com.example.myapplication.R.id.prd_amount);
        TextView time= convertView.findViewById(com.example.myapplication.R.id.prd_price);
        Blog blog= this.objects.get(position);
        title.setText(blog.getTitle());
        decript.setText((blog.getDecript()));
        time.setText(blog.getTime());
        return convertView;
    }
}
