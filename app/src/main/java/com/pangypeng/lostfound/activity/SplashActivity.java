package com.pangypeng.lostfound.activity;


import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.pangypeng.lostfound.MainActivity;
import com.pangypeng.lostfound.R;

/**
 * 作者 pang
 * 时间 2016/3/7 0007 10:39
 * 文件 LostFound
 * 描述
 */
public class SplashActivity extends BaseActivity {
    private static final int GO_HOME = 100;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GO_HOME:
                    goHome();
                    break;
            }
        }
    };

    private void goHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        mHandler.sendEmptyMessageDelayed(GO_HOME,3000);
    }
}
