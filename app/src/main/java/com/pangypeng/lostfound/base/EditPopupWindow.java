package com.pangypeng.lostfound.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.pangypeng.lostfound.R;
import com.pangypeng.lostfound.inter.IPopupItemClick;

/**
 * 作者 pang
 * 时间 2016/3/7 0007 14:09
 * 文件 LostFound
 * 描述
 */
public class EditPopupWindow extends BasePopupWindow implements View.OnClickListener {

    private TextView mEdit;
    private TextView mDelete;

    public EditPopupWindow(Context context,int width,int height){
        super(LayoutInflater.from(context).inflate(R.layout.pop_device,null),
                dpTpPx(context,width),dpTpPx(context,height));
        setAnimationStyle(R.style.PopupAnimation);
    }

    private static int dpTpPx(Context context,int dpValue){
        return (int)(context.getResources().getDisplayMetrics().density*dpValue + 0.5f);
    }

    @Override
    public void initViews() {
        mEdit = (TextView) findViewById(R.id.tv_pop_edit);
        mDelete = (TextView) findViewById(R.id.tv_pop_delete);
    }

    @Override
    public void initEvents() {
        mEdit.setOnClickListener(this);
        mDelete.setOnClickListener(this);
    }

    @Override
    public void init() {

    }

    private IPopupItemClick mOnPopupItemClickListener;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_pop_edit:
                if(mOnPopupItemClickListener != null){
                    mOnPopupItemClickListener.onEdit(v);
                }
                break;
            case R.id.tv_pop_delete:
                if(mOnPopupItemClickListener != null){
                    mOnPopupItemClickListener.onDelete(v);
                }
                break;
        }
        dismiss();
    }

    public void setOnPopupItemClickListener(IPopupItemClick click){
        mOnPopupItemClickListener = click;
    }
}
