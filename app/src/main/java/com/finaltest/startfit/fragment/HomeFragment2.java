package com.finaltest.startfit.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.finaltest.startfit.PostInfo2;
import com.finaltest.startfit.R;
import com.finaltest.startfit.WritePostActivity2;
import com.finaltest.startfit.adapter.HomeAdapter2;
import com.finaltest.startfit.listener.OnPostListener2;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;

public class HomeFragment2 extends Fragment {
    private static final String TAG = "HomeFragment2";
    private FirebaseFirestore firebaseFirestore;
    private HomeAdapter2 homeAdapter;
    private ArrayList<PostInfo2> postList2;
    private boolean updating;
    private boolean topScrolled;
    private FirebaseAuth mFirebaseAuth;

    public HomeFragment2() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home2, container, false);

        firebaseFirestore = FirebaseFirestore.getInstance();
        postList2 = new ArrayList<>();
        homeAdapter = new HomeAdapter2(getActivity(), postList2);
        homeAdapter.setOnPostListener2(onPostListener2);

        final RecyclerView recyclerView = view.findViewById(R.id.recyclerView2);
        view.findViewById(R.id.floatingActionButton2).setOnClickListener(onClickListener);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(homeAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                int firstVisibleItemPosition = ((LinearLayoutManager)layoutManager).findFirstVisibleItemPosition();

                if(newState == 1 && firstVisibleItemPosition == 0){
                    topScrolled = true;
                }
                if(newState == 0 && topScrolled){
                    postsUpdate(true);
                    topScrolled = false;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy){
                super.onScrolled(recyclerView, dx, dy);

                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = ((LinearLayoutManager)layoutManager).findFirstVisibleItemPosition();
                int lastVisibleItemPosition = ((LinearLayoutManager)layoutManager).findLastVisibleItemPosition();

                if(totalItemCount - 3 <= lastVisibleItemPosition && !updating){
                    postsUpdate(false);
                }

                if(0 < firstVisibleItemPosition){
                    topScrolled = false;
                }
            }
        });

        postsUpdate(false);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onPause(){
        super.onPause();
        homeAdapter.playerStop();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.floatingActionButton2:
                    myStartActivity(WritePostActivity2.class);
                    break;
            }
        }
    };

    OnPostListener2 onPostListener2 = new OnPostListener2() {



//        @Override
//        public void onDelete(PostInfo postInfo) {
//            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//
////
//            postList2.remove(postInfo);
//            homeAdapter.notifyDataSetChanged();
//
//            Log.e("로그: ","삭제 성공");
//        }

        @Override
        public void onDelete(PostInfo2 postInfo2) {
            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

//
            postList2.remove(postInfo2);
            homeAdapter.notifyDataSetChanged();

            Log.e("로그: ","삭제 성공");
        }

        @Override
        public void onModify() {
            Log.e("로그: ","수정 성공");
        }
    };

    private void postsUpdate(final boolean clear) {
        updating = true;
        Date date = postList2.size() == 0 || clear ? new Date() : postList2.get(postList2.size() - 1).getCreatedAt();
        CollectionReference collectionReference = firebaseFirestore.collection("posts2");
        collectionReference.orderBy("createdAt", Query.Direction.DESCENDING).whereLessThan("createdAt", date).limit(10).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if(clear){
                                postList2.clear();
                            }
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                postList2.add(new PostInfo2(
                                        document.getData().get("title").toString(),
                                        (ArrayList<String>) document.getData().get("contents"),
                                        (ArrayList<String>) document.getData().get("formats"),
                                        document.getData().get("publisher").toString(),
                                        new Date(document.getDate("createdAt").getTime()),
                                        document.getId()
                                       ));
                            }
                            homeAdapter.notifyDataSetChanged();
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                        updating = false;
                    }
                });
    }


    private void myStartActivity(Class c) {
        Intent intent = new Intent(getActivity(), c);
        startActivityForResult(intent, 1);
    }
}

