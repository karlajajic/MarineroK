package com.example.marinero_kj;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.marinero_kj.adapter.ReviewAdapter;
import com.example.marinero_kj.pojo.Review;
import com.example.marinero_kj.viewModel.ReviewViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class ReviewFragment extends Fragment {
    private EditText userInput;
    private RecyclerView recyclerView;
    private Button button;
    private List<Review> reviews;

    private String sightName;
    private static final String SIGHT_NAME="name";
    private String username;
    private static final String USERNAME="user";

    private ReviewViewModel viewModel;
    private ReviewAdapter adapter;

    public static ReviewFragment newInstance(String name, String username) {
        ReviewFragment fragment = new ReviewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(SIGHT_NAME, name);
        bundle.putString(USERNAME, username);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(ReviewViewModel.class);
        String name="error";
        String user="unknown";

        if(getArguments()!=null) {
            name = getArguments().getString(SIGHT_NAME);
            user=getArguments().getString(USERNAME);
        }
        sightName=name;
        username=user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.fragment_review, container, false);

        recyclerView= view.findViewById(R.id.review_recycler_view);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext()));

        userInput= view.findViewById(R.id.reviews_activity_enter);
        reviews= new ArrayList<>();

        LiveData<List<Review>> data= viewModel.getReviews(sightName);
        data.observe(this, new Observer<List<Review>>() {
            @Override
            public void onChanged(List<Review> r) {
                if(r!=null || r.size()==0){
                    reviews.clear();
                    for(int i=0;i<r.size();i++){
                        reviews.add(r.get(i));
                    }
                }
                else{
                    reviews.add(new Review(String.valueOf(R.string.review_list_error), ""));
                }

                adapter= new ReviewAdapter(reviews);
                recyclerView.setAdapter(adapter);
            }
        });

        button=view.findViewById(R.id.review_save_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.addReview(sightName, new Review(username, userInput.getText().toString()));
                Snackbar.make(view, R.string.review_add, Snackbar.LENGTH_LONG).show();
                userInput.setText("");
            }
        });

        return view;
    }

}
