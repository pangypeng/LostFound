package com.pangypeng.lostfound.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;

/**
 * 作者 pang
 * 时间 2016/3/7 0007 13:51
 * 文件 LostFound
 * 描述
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public abstract class BasePopupWindow extends PopupWindow {
    protected View mContentView;

    public BasePopupWindow() {
        super();
    }

    public BasePopupWindow(Context context) {
        super(context);
    }

    public BasePopupWindow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BasePopupWindow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BasePopupWindow(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    // -----------------

    public BasePopupWindow(View contentView) {
        super(contentView);
    }

    public BasePopupWindow(int width, int height) {
        super(width, height);
    }

    public BasePopupWindow(View contentView, int width, int height) {
        super(contentView, width, height);
        mContentView = contentView;
        setBackgroundDrawable(new BitmapDrawable());
        setTouchable(true);
        setOutsideTouchable(true);
        setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_OUTSIDE){
                    dismiss();
                    return true;
                }
                return false;
            }
        });
        initViews();
        initEvents();
        init();
    }

    public BasePopupWindow(View contentView, int width, int height, boolean focusable) {
        super(contentView, width, height, focusable);
    }

    public abstract void initViews();
    public abstract void initEvents();
    public abstract void init();

    public View findViewById(int id){
        return mContentView.findViewById(id);
    }

    protected OnSubmitClickListener mOnSubmitClickListener;

    /**
     * 添加点击监听
     * @param listener
     */
    public void setOnSubmitClickListener(OnSubmitClickListener listener){
        mOnSubmitClickListener = listener;
    }

    /**
     * 自定义监听接口
     */
    public interface OnSubmitClickListener{
        void onClick();
    }
}
