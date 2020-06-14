package com.example.marinero_kj;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.marinero_kj.pojo.Sunrise;
import com.example.marinero_kj.viewModel.SunriseViewModel;

public class SunriseFragment extends Fragment {

    TextView sunrise;
    TextView sunset;
    ImageView imageView;
    ProgressBar progressBar;

    SunriseViewModel viewModel;

    private static final String LONGITUDE="long";
    private static final String LATITUDE="lat";

    public static SunriseFragment newInstance(double longitude, double latitude) {
        SunriseFragment fragment = new SunriseFragment();
        Bundle bundle = new Bundle();
        bundle.putDouble(LONGITUDE, longitude);
        bundle.putDouble(LATITUDE, latitude);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(SunriseViewModel.class);
        double lon=-1,lat=-1;

        if(getArguments()!=null){
            lon= getArguments().getDouble(LONGITUDE);
            lat= getArguments().getDouble(LATITUDE);}

        viewModel.updateSunrise(lon, lat);  //viewModel.updateSunrise(36.7201600,-4.4203400 );
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sunrise_fragment, container, false);

        imageView=view.findViewById(R.id.image);
        sunrise=view.findViewById(R.id.sunrise_fragment_sunrise);
        sunset=view.findViewById(R.id.sunrise_fragment_sunset);
        progressBar=view.findViewById(R.id.sunrise_fragment_progress);

        viewModel.getSunrise().observe(getActivity(), new Observer<Sunrise>() {
            @Override
            public void onChanged(Sunrise s) {
                sunrise.setText("sunrise: "+s.getSunrise());
                sunset.setText("sunset: "+s.getSunset());
            }
        });

        viewModel.getIsLoading().observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                int visibility= aBoolean? View.VISIBLE:View.INVISIBLE;
                progressBar.setVisibility(visibility);
            }
        });

        return view;
    }

}
