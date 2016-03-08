package com.pangypeng.lostfound.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * 作者 pang
 * 时间 2016/3/8 0008 11:29
 * 文件 LostFound
 * 描述
 */
public abstract class MyAdapter<T> extends BaseAdapter {
    private Context mContext;
    private List<T> mList;
    public MyAdapter(Context context,List<T> list){
        mContext = context;
        mList = list;
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public abstract Object getItem(int position);

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);

    class ViewHolder{
        TextView tv_title,tv_phone,tv_time,tv_describe;
    }
}
