package com.culmyca.ec_2020_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.culmyca.ec_2020_app.R;

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private Integer[] imageResID = {R.drawable.u1,R.drawable.u2,R.drawable.u3,R.drawable.u4,R.drawable.u5,R.drawable.u6,R.drawable.u7,R.drawable.u8};//

    public ViewPagerAdapter(Context context) {
        this.context=context;

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
       // return super.instantiateItem(container, position);
        layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.layoutfor_viewpager,null);
        ImageView im = v.findViewById(R.id.imageViewlayout);
        im.setImageResource(imageResID[position]);

        ViewPager viewPager =(ViewPager) container;
        viewPager.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        ViewPager viewPager =(ViewPager) container;
        View v =(View) object;
        viewPager.removeView(v);
    }

    @Override
    public int getCount() {
        return imageResID.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
}
