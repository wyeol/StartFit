package com.finaltest.startfit;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.finaltest.startfit.listener.OnPostListener2;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import static com.finaltest.startfit.Util.isStorageUrl2;
import static com.finaltest.startfit.Util.showToast;
import static com.finaltest.startfit.Util.storageUrlToName2;

public class FirebaseHelper2 {
    private Activity activity;
    private OnPostListener2 onPostListener;
    private int successCount;

    public FirebaseHelper2(Activity activity) {
        this.activity = activity;
    }

    public void setOnPostListener2(OnPostListener2 onPostListener2){
        this.onPostListener = onPostListener2;
    }

    public void storageDelete(final PostInfo2 postInfo2){
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        final String id = postInfo2.getId();
        ArrayList<String> contentsList = postInfo2.getContents();
        for (int i = 0; i < contentsList.size(); i++) {
            String contents = contentsList.get(i);
            if (isStorageUrl2(contents)) {
                successCount++;
                StorageReference desertRef = storageRef.child("posts2/" + id + "/" + storageUrlToName2(contents));
                desertRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        successCount--;
                        storeDelete(id, postInfo2);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        showToast(activity, "Error");
                    }
                });
            }
        }
        storeDelete(id, postInfo2);
    }

    private void storeDelete(final String id, final PostInfo2 postInfo2) {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        if (successCount == 0) {
            firebaseFirestore.collection("posts2").document(id)
                    .delete()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            showToast(activity, "게시글을 삭제하였습니다.");
                            onPostListener.onDelete(postInfo2);
                            //postsUpdate();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            showToast(activity, "게시글을 삭제하지 못하였습니다.");
                        }
                    });
        }
    }
}
