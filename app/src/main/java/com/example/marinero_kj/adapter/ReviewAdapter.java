package com.example.marinero_kj.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marinero_kj.R;
import com.example.marinero_kj.pojo.Review;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private List<Review> reviews;

    public ReviewAdapter(List<Review> reviewList){
        this.reviews=reviewList;
    }

    @NonNull
    @Override
    public ReviewAdapter.ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.review_card, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ReviewViewHolder holder, int position) {
        holder.username.setText(reviews.get(position).getUsername()+": ");
        holder.comment.setText(reviews.get(position).getComment());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder{

        TextView username;
        TextView comment;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            username= itemView.findViewById(R.id.review_card_username);
            comment= itemView.findViewById(R.id.review_card_comment);
        }
    }
}
