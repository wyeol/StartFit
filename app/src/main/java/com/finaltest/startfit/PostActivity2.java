package com.finaltest.startfit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import com.finaltest.startfit.listener.OnPostListener2;
import com.finaltest.startfit.view.ReadContentsVIew2;

public class PostActivity2 extends BasicActivity {
    private PostInfo2 postInfo2;
    private FirebaseHelper2 firebaseHelper2;
    private ReadContentsVIew2 readContentsVIew;
    private LinearLayout contentsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post2);

        postInfo2 = (PostInfo2) getIntent().getSerializableExtra("postInfo2");
        contentsLayout = findViewById(R.id.contentsLayout2);
        readContentsVIew = findViewById(R.id.readContentsView2);

        firebaseHelper2 = new FirebaseHelper2(this);
        firebaseHelper2.setOnPostListener2(onPostListener);
        uiUpdate();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 3:
                if (resultCode == Activity.RESULT_OK) {
                    postInfo2 = (PostInfo2)data.getSerializableExtra("postinfo2");
                    contentsLayout.removeAllViews();
                    uiUpdate();
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.post2, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete2:
                firebaseHelper2.storageDelete(postInfo2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    OnPostListener2 onPostListener = new OnPostListener2() {
//        @Override
//        public void onDelete(PostInfo postInfo) {
//            Log.e("로그 ","삭제 성공");
//        }

        @Override
        public void onDelete(PostInfo2 postInfo) {
            Log.e("로그 ","삭제 성공");
        }

        @Override
        public void onModify() {
            Log.e("로그 ","수정 성공");
        }
    };

    private void uiUpdate(){
        setToolbarTitle(postInfo2.getTitle());
        readContentsVIew.setPostInfo(postInfo2);
    }

    private void myStartActivity(Class c, PostInfo2 postInfo2) {
        Intent intent = new Intent(this, c);
        intent.putExtra("postInfo2", postInfo2);
        startActivityForResult(intent, 3);
    }
}
