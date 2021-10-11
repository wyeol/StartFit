package com.finaltest.startfit.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.finaltest.startfit.R;
import com.finaltest.startfit.UserInfo;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.MainViewHolder> {
    private ArrayList<UserInfo> mDataset;
    private Activity activity;

    static class MainViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        MainViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    public UserListAdapter(Activity activity, ArrayList<UserInfo> myDataset) {
        this.mDataset = myDataset;
        this.activity = activity;
    }

    @Override
    public int getItemViewType(int position){
        return position;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_list, parent, false);
        final MainViewHolder mainViewHolder = new MainViewHolder(cardView);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });

        return mainViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MainViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        ImageView photoImageVIew = cardView.findViewById(R.id.photoImageVIew);
        TextView nameTextView = cardView.findViewById(R.id.nameTextView);


        UserInfo userInfo = mDataset.get(position);
        if(mDataset.get(position).getPhotoUrl() != null){
            Glide.with(activity).load(mDataset.get(position).getPhotoUrl()).centerCrop().override(500).into(photoImageVIew);
        }
        nameTextView.setText(userInfo.getName());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}