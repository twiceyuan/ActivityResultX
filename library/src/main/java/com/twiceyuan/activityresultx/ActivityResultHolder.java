package com.twiceyuan.activityresultx;

import android.content.Intent;

public class ActivityResultHolder {

    private ActivityResultCallback mActivityResultCallback;

    void handle(int resultCode, Intent data) {
        if (mActivityResultCallback != null) {
            mActivityResultCallback.onResult(resultCode, data);
        }
    }

    public void onActivityResult(ActivityResultCallback activityResultCallback) {
        mActivityResultCallback = activityResultCallback;
    }
}
