package com.twiceyuan.activityresultx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;

public class ActivityResultHolderFragment extends android.support.v4.app.Fragment {

    public static final String ARG_TARGET_CLASS = "targetClass";
    public static final String ARG_REQUEST_CODE = "requestCode";

    private int mRequestCode;

    public static ActivityResultHolderFragment newInstance(
            int requestCode,
            @NonNull Class<? extends Activity> targetClass
    ) {
        Bundle args = new Bundle();
        args.putInt(ARG_REQUEST_CODE, requestCode);
        args.putSerializable(ARG_TARGET_CLASS, targetClass);
        ActivityResultHolderFragment fragment = new ActivityResultHolderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //noinspection unchecked,ConstantConditions
        Class<? extends Activity> targetClass =
                (Class<? extends Activity>) getArguments().getSerializable(ARG_TARGET_CLASS);
        mRequestCode = getArguments().getInt(ARG_REQUEST_CODE);

        assert targetClass != null;
        startActivityForResult(new Intent(getActivity(), targetClass), mRequestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == mRequestCode) {
            ActivityResultHolder holder = ActivityResultX.sActivityResultMap.get(mRequestCode);
            if (holder != null) {
                holder.handle(resultCode, data);
                ActivityResultX.sActivityResultMap.remove(mRequestCode);
                FragmentManager fragmentManager = getFragmentManager();
                if (fragmentManager != null) {
                    fragmentManager.beginTransaction()
                            .remove(this)
                            .commit();
                }
            }
        }
    }
}
