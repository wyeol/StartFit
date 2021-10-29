package com.finaltest.startfit.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.finaltest.startfit.FirebaseHelper2;
import com.finaltest.startfit.PostActivity2;
import com.finaltest.startfit.PostInfo2;
import com.finaltest.startfit.R;

import com.finaltest.startfit.WritePostActivity2;

import com.finaltest.startfit.listener.OnPostListener2;

import com.finaltest.startfit.view.ReadContentsVIew2;
import com.google.android.exoplayer2.SimpleExoPlayer;

import java.util.ArrayList;

public class HomeAdapter2 extends RecyclerView.Adapter<HomeAdapter2.MainViewHolder> {
    private ArrayList<PostInfo2> mDataset;
    private Activity activity;
    private FirebaseHelper2 firebaseHelper;
    private ArrayList<ArrayList<SimpleExoPlayer>> playerArrayListArrayList = new ArrayList<>();
    private final int MORE_INDEX = 2;

    static class MainViewHolder extends RecyclerView.ViewHolder {
        CardView cardView2;
        MainViewHolder(CardView v) {
            super(v);
            cardView2 = v;
        }
    }

    public HomeAdapter2(Activity activity, ArrayList<PostInfo2> myDataset) {
        this.mDataset = myDataset;
        this.activity = activity;

        firebaseHelper = new FirebaseHelper2(activity);
    }

    public void setOnPostListener2(OnPostListener2 onPostListener2){
        firebaseHelper.setOnPostListener2(onPostListener2);
    }

    @Override
    public int getItemViewType(int position){
        return position;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post2, parent, false);
        final MainViewHolder mainViewHolder = new MainViewHolder(cardView);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, PostActivity2.class);
                intent.putExtra("postInfo2", mDataset.get(mainViewHolder.getAdapterPosition()));
                activity.startActivity(intent);
            }
        });

        cardView.findViewById(R.id.menu2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v, mainViewHolder.getAdapterPosition());
            }
        });

        return mainViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MainViewHolder holder, int position) {
        CardView cardView = holder.cardView2;
        TextView titleTextView = cardView.findViewById(R.id.titleTextView2);
        TextView priceTextView = cardView.findViewById(R.id.priceEditText);
        PostInfo2 postInfo2 = mDataset.get(position);
        titleTextView.setText(postInfo2.getTitle());


        ReadContentsVIew2 readContentsVIew2 = cardView.findViewById(R.id.readContentsView2);
        LinearLayout contentsLayout = cardView.findViewById(R.id.contentsLayout2);

        if (contentsLayout.getTag() == null || !contentsLayout.getTag().equals(postInfo2)) {
            contentsLayout.setTag(postInfo2);
            contentsLayout.removeAllViews();

            readContentsVIew2.setMoreIndex(MORE_INDEX);
            readContentsVIew2.setPostInfo(postInfo2);

            ArrayList<SimpleExoPlayer> playerArrayList = readContentsVIew2.getPlayerArrayList();
            if(playerArrayList != null){
                playerArrayListArrayList.add(playerArrayList);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    private void showPopup(View v, final int position) {
        PopupMenu popup = new PopupMenu(activity, v);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.delete2:
                        firebaseHelper.storageDelete(mDataset.get(position));
                        return true;
                    default:
                        return false;
                }
            }
        });

        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.post2, popup.getMenu());
        popup.show();
    }

    private void myStartActivity(Class c, PostInfo2 postInfo2) {
        Intent intent = new Intent(activity, c);
        intent.putExtra("postInfo2", postInfo2);
        activity.startActivity(intent);
    }

    public void playerStop(){
        for(int i = 0; i < playerArrayListArrayList.size(); i++){
            ArrayList<SimpleExoPlayer> playerArrayList = playerArrayListArrayList.get(i);
            for(int ii = 0; ii < playerArrayList.size(); ii++){
                SimpleExoPlayer player = playerArrayList.get(ii);
                if(player.getPlayWhenReady()){
                    player.setPlayWhenReady(false);
                }
            }
        }
    }
}