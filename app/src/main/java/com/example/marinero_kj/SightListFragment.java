package com.example.marinero_kj;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.marinero_kj.adapter.SightAdapter;
import com.example.marinero_kj.pojo.Sight;
import com.example.marinero_kj.viewModel.SightListViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SightListFragment extends Fragment implements SightAdapter.OnListItemClickListener {

    private RecyclerView mRecyclerView;
    private SightAdapter adapter;
    private ArrayList<Sight> sights;
    private SightListViewModel viewModel;

    private static final String USERNAME="username";
    private String username;

    public static SightListFragment newInstance(String username) {
        SightListFragment fragment = new SightListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(USERNAME, username);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(SightListViewModel.class);
        String name="unknown";

        if(getArguments()!=null)
            name= getArguments().getString(USERNAME);
        username=name;

        sights=new ArrayList<>();
        List<Sight> s=viewModel.getAllSights().getValue();
        if(s!=null && !s.isEmpty())
        for(Sight sigh:s)
            sights.add(sigh);
        else populateSights();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sight_list_fragment, container, false);

        mRecyclerView = view.findViewById(R.id.rv);
        mRecyclerView.hasFixedSize();
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)); //so its a grid, not a list

        viewModel.getAllSights().observe(getViewLifecycleOwner(), new Observer<List<Sight>>() {

            @Override
            public void onChanged(List<Sight> s) {
                if (!s.isEmpty()) {
                    sights.clear();
                    for(Sight sigh:s)
                        sights.add(sigh);
                }
                else populateSights();
            }
        });

        adapter = new SightAdapter(sights, this);
        mRecyclerView.setAdapter(adapter);
        return view;
    }

 //36.7201600,-4.4203400
    private void populateSights() {
        Sight s=new Sight("Split","Najlipši grad na svitu","https://www.google.com/search?q=Split", true);
        s.setLongitude(36.7201600);
        s.setLatitude(-4.4203400);
        viewModel.addSight(s);

        s=new Sight("Pula","Pula je tamo negdi gore","https://www.google.com/search?q=Pula", true);
        s.setLongitude(35.6401600);
        s.setLatitude(-4.2303400);
        s.setImage("https://d2bgjx2gb489de.cloudfront.net/gbb-blogs/wp-content/uploads/2019/06/24091536/Pula.jpg");
        viewModel.addSight(s);

        s=new Sight("Zadar","Zadar je shema grad, blizu kuce", "https://www.google.com/search?q=Zadar",true);
        s.setLongitude(36.5001600);
        s.setLatitude(-4.5103400);
        s.setImage("https://image.arrivalguides.com/1500x600/00/d2b767524cce6ebe3656c729040a4a57.jpg");
        viewModel.addSight(s);

        s=new Sight("Dubrovnik","Tamo di se snima game of thrones", "https://www.google.com/search?q=Dubrovnik",true);
        s.setLongitude(37.5601600);
        s.setLatitude(-4.6003400);
        s.setImage("https://i.insider.com/5dc321623afd37186c3d3ee5?width=1600&format=jpeg&auto=webp");
        viewModel.addSight(s);

        s=new Sight("Šibenik","Tamo se Nikica rodija", "https://www.google.com/search?q=Šibenik",true);
        s.setLongitude(37.5601600);
        s.setLatitude(-4.6003400);
        s.setImage("https://www.sibenik.in/upload/novosti/2019/08/2019-08-07/110805/boattour.jpg");
        viewModel.addSight(s);

        s=new Sight("Malinksa","Jako slatko misto.", "https://www.google.com/search?q=Malinska",true);
        s.setLongitude(36.5001600);
        s.setLatitude(-4.5103400);
        s.setImage("https://d2msmins3v3lzf.cloudfront.net/ADNorrmxJW3lQTSwjx0KO8EM");
        viewModel.addSight(s);

        s=new Sight("Opatija","Jako skupo misto.", "https://www.google.com/search?q=Opatija",true);
        s.setLongitude(36.5001600);
        s.setLatitude(-4.5103400);
        s.setImage("https://img.theculturetrip.com/768x432/wp-content/uploads/2018/09/shutterstock_639223624.jpg");
        viewModel.addSight(s);

    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(getActivity(), CityDetail.class);
        intent.putExtra("sight",sights.get(clickedItemIndex));
        intent.putExtra("username",username);
        startActivity(intent);
    }
}
