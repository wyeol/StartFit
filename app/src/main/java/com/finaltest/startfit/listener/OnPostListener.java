package com.finaltest.startfit.listener;


import com.finaltest.startfit.PostInfo;

public interface OnPostListener {
    void onDelete(PostInfo postInfo);
    void onModify();
}
