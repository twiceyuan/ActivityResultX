package com.twiceyuan.activityresultx.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class ChooseResultActivity extends AppCompatActivity {

    public static String EXTRA_CHOOSE_RESULT = "result";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_result);
        findViewById(R.id.tv_selection_1).setOnClickListener(v -> chooseResult("选项 1"));
        findViewById(R.id.tv_selection_2).setOnClickListener(v -> chooseResult("选项 2"));
    }

    private void chooseResult(String result) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_CHOOSE_RESULT, result);
        setResult(RESULT_OK, intent);
        finish();
    }
}
