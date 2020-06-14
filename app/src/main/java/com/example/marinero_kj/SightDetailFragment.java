package com.example.marinero_kj;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.marinero_kj.R;
import com.example.marinero_kj.pojo.Sight;
import com.example.marinero_kj.viewModel.SightDetailViewModel;
import com.example.marinero_kj.viewModel.SightListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SightDetailFragment extends Fragment {

    TextView description;
    FloatingActionButton favoriteButton;
    Button internetButton;
    WebView webView;

    private SightDetailViewModel viewModel;

    private static final String SIGHT="sight";
    private static Sight sight;
    private static String descriptionValue;

    public static SightDetailFragment newInstance(Sight sight) {
        SightDetailFragment fragment = new SightDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(SIGHT, sight);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(SightDetailViewModel.class);

        if(getArguments()!=null) {
            sight = (Sight)getArguments().getSerializable(SIGHT);
        }
        else {
            descriptionValue="error";
        }
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sight_detail, container, false);

        if(sight==null){
            description = view.findViewById(R.id.sight_detail_text);
            description.setText(descriptionValue);
        }

        else {
            description = view.findViewById(R.id.sight_detail_text);
            description.setText(sight.getDescription());

            favoriteButton = view.findViewById(R.id.fragment_sight_detail_fab);
            if(sight.isHearted())
                favoriteButton.setImageResource(R.drawable.ic_fab_fav_fill);
            favoriteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { processHearted(); }});

            internetButton = view.findViewById(R.id.sight_detail_internet_button);
            internetButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                String action = Intent.ACTION_VIEW;
                Uri uri = Uri.parse(sight.getUrl());

                Intent intent = new Intent(action, uri);
                startActivity(intent);

                    /*Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:43.509462, 16.437377"));
                    startActivity(intent);*/

                }
            });
        }
        return view;
    }

    private void processHearted(){
        if(!sight.isHearted()){
            sight.setHearted(true);
            viewModel.updateSight(sight);
            favoriteButton.setImageResource(R.drawable.ic_fab_fav_fill);

            Toast.makeText(getActivity(), R.string.like_town, Toast.LENGTH_LONG).show();
        }
        else{
            sight.setHearted(false);
            viewModel.updateSight(sight);
            favoriteButton.setImageResource(R.drawable.ic_fab_fav);
        }
    }
}