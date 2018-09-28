package com.twiceyuan.activityresultx;

import android.content.Intent;

public interface ActivityResultCallback {
    void onResult(int resultCode, Intent data);
}
