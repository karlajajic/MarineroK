package com.example.marinero_kj;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.marinero_kj.pojo.Sight;
import com.example.marinero_kj.adapter.CityDetailFragmentPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.ImageView;
import android.widget.TextView;


public class CityDetail extends AppCompatActivity {

    private Sight sight;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_detail);

        String username="unknown";

        if(getIntent().hasExtra("sight")) {
            Bundle bundle = getIntent().getExtras();
            sight = (Sight)bundle.getSerializable("sight");
            username= (String)bundle.getString("username");
            TextView textView = findViewById(R.id.city_detail_city_name);
            textView.setText(sight.getName());

            imageView=findViewById(R.id.city_detail_image);

            Glide.with(this)
                    .load(sight.getImage().trim()) // image url
                    .placeholder(R.mipmap.ic_launcher) // any placeholder to load at start
                    .error(R.mipmap.ic_split_foreground)  // any image in case of error
                    .override(400, 200)// resizing
                    .centerCrop()
                    .into(imageView);  // imageview object
        }

        CityDetailFragmentPagerAdapter sectionsPagerAdapter = new CityDetailFragmentPagerAdapter(this, getSupportFragmentManager(), sight, username);
        ViewPager viewPager = findViewById(R.id.city_detail_view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.city_detail_tabs);
        tabs.setupWithViewPager(viewPager);

    }
}