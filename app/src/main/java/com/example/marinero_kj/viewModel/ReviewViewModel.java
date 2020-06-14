package com.example.marinero_kj.viewModel;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.marinero_kj.persistance.realtime.FirebaseQuery;
import com.example.marinero_kj.pojo.Review;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ReviewViewModel extends ViewModel {
    private static final DatabaseReference REVIEWS_REF =
            FirebaseDatabase.getInstance().getReference("/review");
    private FirebaseQuery liveData;
    private LiveData<List<Review>> reviewLiveData;

    private String temp_key;


    public LiveData<List<Review>> getReviews(String sight){
        liveData= new FirebaseQuery(REVIEWS_REF.child(sight));
        reviewLiveData= Transformations.map(liveData, new Deserialize());
       return reviewLiveData;

    }

    public void addReview(String sight, Review r){
        DatabaseReference db= REVIEWS_REF.child(sight);

        Map<String, Object> map= new HashMap<>();
        temp_key=db.push().getKey();
        db.updateChildren(map);

        db=db.child(temp_key);
        Map<String, Object> values= new HashMap<>();
        values.put("username", r.getUsername());
        values.put("comment", r.getComment());
        db.updateChildren(values);
    }

    private List<Review> transformToReview(DataSnapshot snapshot){
        List<Review> reviews= new ArrayList<Review>();

        String username,comment;
        if(snapshot!=null){
        Iterator i= snapshot.getChildren().iterator();

        while(i.hasNext()){
            username= (String)((DataSnapshot)i.next()).getValue();
            comment= (String)((DataSnapshot)i.next()).getValue();
            reviews.add(new Review(username, comment));
            }
        }
        return  reviews;
    }

    private class Deserialize implements Function<DataSnapshot, List<Review>> {
        @Override
        public List<Review> apply(DataSnapshot dataSnapshot) {
            List<Review> reviews= new ArrayList<>();
            HashMap<String, Object> map;
            String username,comment;

            if(dataSnapshot!=null){
                Iterator i= dataSnapshot.getChildren().iterator();

                while(i.hasNext()){
                    map=(HashMap)((DataSnapshot)(i.next())).getValue();
                    username=(String)map.get("username");
                    comment=(String)map.get("comment");
                    reviews.add(new Review(username, comment));
                }
            }
            return reviews;
        }
    }

}
