package com.pangypeng.lostfound.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Window;
import android.widget.Toast;

import com.pangypeng.lostfound.config.Constants;

import cn.bmob.v3.Bmob;

/**
 * 作者 pang
 * 时间 2016/3/7 0007 10:10
 * 文件 LostFound
 * 描述
 */
public  abstract class BaseActivity extends Activity {
    protected int mScreenWidth;
    protected int mScreenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 初始化Bmob SDK
        Bmob.initialize(this, Constants.BMOB_APPID);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 获取当前屏幕的宽高
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mScreenWidth = metrics.widthPixels;
        mScreenHeight = metrics.heightPixels;

        setContentView();
        initViews();
        initListener();
        initData();
    }

    /**
     * 设置布局文件
     */
    public abstract void setContentView();

    /**
     * 初始化布局文件中的控件
     */
    public abstract void initViews();

    /**
     * 初始化控件的监听器
     */
    public abstract void initListener();

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     *
     */
    Toast mToast;
    public void showToast(String text){
        if(!TextUtils.isEmpty(text)){
            if(mToast == null){
                mToast = Toast.makeText(this,text,Toast.LENGTH_SHORT);
            }else{
                mToast.setText(text);
            }
            mToast.show();
        }
    }

    /**
     * 获取状态栏的高度
     * @return
     */
    public int getStateBarHight(){
        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int stateBarHeight = rect.top;
        return stateBarHeight;
    }

    /**
     *
     * @param context
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context,float dipValue){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(scale*dipValue+0.5f);
    }
}
