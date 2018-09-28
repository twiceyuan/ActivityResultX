package com.twiceyuan.activityresultx;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ActivityResultX {

    private static final String TAG_FRAGMENT = "activityResultHolder";

    static Map<Integer, ActivityResultHolder> sActivityResultMap = new LinkedHashMap<>();

    private static AtomicInteger requestCodeGenerator = new AtomicInteger(400);

    @SuppressWarnings("WeakerAccess")
    public static ActivityResultHolder startForResult(Activity activity, Class<? extends Activity> targetActivityClass) {
        return startForResultInternal(activity, targetActivityClass);
    }

    @SuppressWarnings({"WeakerAccess", "unused"})
    public static ActivityResultHolder startForResult(Fragment fragment, Class<? extends Activity> targetActivityClass) {
        return startForResultInternal(fragment, targetActivityClass);
    }

    private static ActivityResultHolder startForResultInternal(Object host, Class<? extends Activity> targetActivityClass) {

        ActivityResultHolder requestHolder = new ActivityResultHolder();

        int requestCode = requestCodeGenerator.getAndIncrement();

        sActivityResultMap.put(requestCode, requestHolder);

        if (host instanceof FragmentActivity) {
            ((FragmentActivity) host).getSupportFragmentManager().beginTransaction()
                    .add(ActivityResultHolderFragment.newInstance(requestCode, targetActivityClass), TAG_FRAGMENT)
                    .commit();
            return requestHolder;
        }

        if (host instanceof Fragment) {
            ((Fragment) host).getChildFragmentManager().beginTransaction()
                    .add(ActivityResultHolderFragment.newInstance(requestCode, targetActivityClass), TAG_FRAGMENT)
                    .commit();
            return requestHolder;
        }

        throw checkHostTypeFailedException(host);
    }

    private static RuntimeException checkHostTypeFailedException(Object host) {
        return new RuntimeException("Host(" + host.getClass().getCanonicalName() + ") is not supported");
    }
}
