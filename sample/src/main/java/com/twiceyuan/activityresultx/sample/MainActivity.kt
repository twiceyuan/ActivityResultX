package com.twiceyuan.activityresultx.sample

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.twiceyuan.activityresultx.ActivityResultX
import com.twiceyuan.activityresultx.sample.ChooseResultActivity.EXTRA_CHOOSE_RESULT
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_choose.setOnClickListener { chooseResult() }
    }

    private fun chooseResult() {
        ActivityResultX.startForResult(this, ChooseResultActivity::class.java)
                .onActivityResult { resultCode, data ->

                    if (resultCode == Activity.RESULT_CANCELED) {
                        tv_choose.text = "取消"
                    }

                    if (resultCode == Activity.RESULT_OK) {
                        val result = data.getStringExtra(EXTRA_CHOOSE_RESULT)
                        tv_choose.text = result
                    }
                }
    }
}
