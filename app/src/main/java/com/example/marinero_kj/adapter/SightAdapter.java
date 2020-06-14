package com.example.marinero_kj.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.marinero_kj.Main;
import com.example.marinero_kj.R;
import com.example.marinero_kj.pojo.Sight;

import java.util.ArrayList;

public class SightAdapter extends RecyclerView.Adapter<SightAdapter.SightViewHolder>{

    final private OnListItemClickListener onListItemClickListener;
    private ArrayList<Sight> sights;
    private Context context;

    public SightAdapter(ArrayList<Sight> sights, OnListItemClickListener onListItemClickListener){
        this.onListItemClickListener=onListItemClickListener;
        this.sights=sights;
    }

    @NonNull
    @Override
    public SightAdapter.SightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.sight_card, parent, false);

        context=parent.getContext();
        return new SightViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SightAdapter.SightViewHolder holder, int position) {
        Sight s=sights.get(position);

        holder.sightName.setText(s.getName());
        if(s.isHearted())
            holder.sightName.setTextColor(Color.parseColor("#607D8B")); //for some reason there is problem with resources

        if(!s.getImage().equals(""))
        Glide.with(context)
                .load(s.getImage().trim()) // image url
                .placeholder(R.mipmap.ic_launcher) // any placeholder to load at start
                .error(R.mipmap.ic_split_foreground)  // any image in case of error
                .override(400, 300)// resizing
                .centerCrop()
                .into(holder.sightImage);  // imageview object
    }

    @Override
    public int getItemCount() {
        return sights.size();
    }

    public class SightViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView sightName;
        ImageView sightImage;
        //LinearLayout layout;

        public SightViewHolder(@NonNull View itemView) {
            super(itemView);
           // layout=itemView.findViewById(R.id.container);
            sightName=itemView.findViewById(R.id.sight_title);
            sightImage=itemView.findViewById(R.id.sight_image);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            onListItemClickListener.onListItemClick(getAdapterPosition());
        }
    }

    public interface OnListItemClickListener{
        void onListItemClick(int clickedItemIndex);
    }
}
