package com.example.shibeonlined05k14;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AnimalAdapter extends BaseAdapter {

    private Context context;
    private List<Animal> dataSource;

    private LayoutInflater layoutInflater;

    public AnimalAdapter(Context context, List<Animal> dataSource) {
        this.context = context;
        this.dataSource = dataSource;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewHolder = layoutInflater.inflate(R.layout.item_animal, parent, false);
        // View va bind id
        TextView tvAnimal = viewHolder.findViewById(R.id.tvAnimal);
        ImageView imgAnimal = viewHolder.findViewById(R.id.imgAnimal);
        // Do du lieu vao view
        Animal animal = dataSource.get(position);
        tvAnimal.setText(animal.getName());
        Glide.with(context).load(animal.getImageUrl()).into(imgAnimal);

        return viewHolder;
    }
}
