package com.main.intro.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.e_learning.R;
import com.main.intro.data.models.Slide;
import com.main.intro.data.source.local.SliderData;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.myViewHoder> {
    ArrayList items = new ArrayList<Slide>();
    @Override
    public myViewHoder onCreateViewHolder(ViewGroup parent) {
        // we must make customisation in item_slider to view to make findviewByID
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slider,null);
        return new myViewHoder(view);
    }

    @Override
    public void onBindViewHolder(myViewHoder viewHolder, int position) {
        // set data
      Slide item = SliderData.slide[position];
      viewHolder.imageSlider.setImageResource(item.image);
//      viewHolder.titleSlider.setText(item.title);
      viewHolder.descriptionSlider.setText(item.description);

    }

    // how many page
    @Override
    public int getCount() {
        return 3;
    }
    public static class myViewHoder extends ViewHolder{
        ImageView imageSlider;
        TextView titleSlider;
        TextView descriptionSlider;
        public myViewHoder(View itemView) {
            super(itemView);
            //.... set findviewById
            imageSlider=itemView.findViewById(R.id.slider_image);
         //   titleSlider = itemView.findViewById(R.id.slider_title);
            descriptionSlider = itemView.findViewById(R.id.slider_description);
        }
    }
}
