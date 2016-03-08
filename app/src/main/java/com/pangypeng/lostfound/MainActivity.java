package com.pangypeng.lostfound;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pangypeng.lostfound.activity.AddActivity;
import com.pangypeng.lostfound.activity.BaseActivity;
import com.pangypeng.lostfound.adapter.MyAdapter;
import com.pangypeng.lostfound.base.EditPopupWindow;
import com.pangypeng.lostfound.bean.Found;
import com.pangypeng.lostfound.bean.Lost;
import com.pangypeng.lostfound.config.Constants;
import com.pangypeng.lostfound.inter.IPopupItemClick;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.FindListener;

public class MainActivity extends BaseActivity implements
        View.OnClickListener,IPopupItemClick,AdapterView.OnItemLongClickListener{
    // ActionBar
    RelativeLayout layout_action;
    LinearLayout layout_all;
    TextView tv_lost;
    Button btn_add;
    // Content
    ListView list_lost;
    // include_no_data
    LinearLayout layout_no;
    TextView tv_no;
    // include_progress
    RelativeLayout progress;

    private MyAdapter<Found> foundMyAdapter = null;
    private MyAdapter<Lost> lostMyAdapter = null;

    private Button layout_found;
    private Button layout_lost;
    @Override
    public void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initViews() {
        // ActionBar
        layout_action = (RelativeLayout) findViewById(R.id.layout_action);
        layout_all = (LinearLayout) findViewById(R.id.layout_all);
        tv_lost = (TextView) findViewById(R.id.tv_lost);
        tv_lost.setTag("Lost");
        btn_add = (Button) findViewById(R.id.btn_add);
        // Content
        list_lost = (ListView) findViewById(R.id.list_lost);
        // include_no_data
        layout_no = (LinearLayout) findViewById(R.id.layout_no);
        tv_no = (TextView) findViewById(R.id.tv_no);
        // includer_prgress
        progress = (RelativeLayout) findViewById(R.id.progress);

        initEditPopup();
    }

    EditPopupWindow mEditPopupWindow;
    /**
     * 编辑弹窗
     */
    private void initEditPopup() {
        mEditPopupWindow = new EditPopupWindow(this,200,48);
        mEditPopupWindow.setOnPopupItemClickListener(this);
    }

    @Override
    public void initListener() {
        layout_all.setOnClickListener(this);
        btn_add.setOnClickListener(this);

        list_lost.setOnItemLongClickListener(this);
    }

    @Override
    public void initData() {
        
        if(tv_lost.getTag().equals("Lost")){
            queryLosts();
        }else{
            queryFounds();
        }
    }

    private void showView() {
        progress.setVisibility(View.VISIBLE);
        list_lost.setVisibility(View.VISIBLE);
        layout_no.setVisibility(View.GONE);
    }

    TextView tv_title,tv_phone,tv_time,tv_describe;

    private void queryLosts() {
        showView();
        foundMyAdapter = null;
        BmobQuery<Lost> query = new BmobQuery<>();
        query.order("-createdAt");
        query.findObjects(this, new FindListener<Lost>() {
            @Override
            public void onSuccess(final List<Lost> list) {

                progress.setVisibility(View.GONE);
                if(list == null || list.size() == 0){
                    list_lost.setVisibility(View.GONE);
                    layout_no.setVisibility(View.VISIBLE);
                }

                lostMyAdapter = new MyAdapter<Lost>(MainActivity.this,list) {
                    @Override
                    public Object getItem(int position) {
                        return list.get(position);
                    }

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        ViewHolder holder = null;
                        if(convertView == null){
                            holder = new ViewHolder();
                            convertView = View.inflate(MainActivity.this,R.layout.item_list,null);
                            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
                            holder.tv_phone = (TextView) convertView.findViewById(R.id.tv_phone);
                            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
                            holder.tv_describe = (TextView) convertView.findViewById(R.id.tv_describe);
                            convertView.setTag(holder);
                        }else{
                            holder = (ViewHolder)convertView.getTag();
                        }

                        holder.tv_title.setText(list.get(position).getTitle());
                        holder.tv_describe.setText(list.get(position).getDescription());
                        holder.tv_time.setText(list.get(position).getCreatedAt());
                        holder.tv_phone.setText(list.get(position).getPhone());
                        return convertView;
                    }
                };

                list_lost.setAdapter(lostMyAdapter);
            }

            @Override
            public void onError(int i, String s) {
                // TODO
            }
        });
    }

    private void queryFounds() {
        showView();
        lostMyAdapter = null;
        BmobQuery<Found> query = new BmobQuery<>();
        query.order("-createdAt");
        query.findObjects(this, new FindListener<Found>() {
            @Override
            public void onSuccess(final List<Found> list) {
                progress.setVisibility(View.GONE);
                if(list.size() == 0 || list == null){

                    list_lost.setVisibility(View.GONE);
                    layout_no.setVisibility(View.VISIBLE);
                }

                foundMyAdapter = new MyAdapter<Found>(MainActivity.this,list) {

                    @Override
                    public Object getItem(int position) {
                        return list.get(position);
                    }

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        ViewHolder holder = null;
                        if(convertView == null){
                            holder = new ViewHolder();
                            convertView = View.inflate(MainActivity.this,R.layout.item_list,null);
                            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
                            holder.tv_phone = (TextView) convertView.findViewById(R.id.tv_phone);
                            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
                            holder.tv_describe = (TextView) convertView.findViewById(R.id.tv_describe);
                            convertView.setTag(holder);
                        }else{
                            holder = (ViewHolder)convertView.getTag();
                        }

                        holder.tv_title.setText(list.get(position).getTitle());
                        holder.tv_describe.setText(list.get(position).getDescription());
                        holder.tv_time.setText(list.get(position).getCreatedAt());
                        holder.tv_phone.setText(list.get(position).getPhone());
                        return convertView;
                    }
                };
                list_lost.setAdapter(foundMyAdapter);

            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }



    @Override
    public void onClick(View v) {
        if(v==layout_all){
            showListPop();
        } else if(v==btn_add){
            Intent intent = new Intent(this, AddActivity.class);
            intent.putExtra("from",tv_lost.getTag().toString());
            startActivityForResult(intent, Constants.QEQUESTCODE_ADD);
        } else if(v == layout_found){
            changeText(v);
            queryFounds();
            mPopupWindow.dismiss();
        }else if(v==layout_lost){
            changeText(v);
            queryLosts();
            mPopupWindow.dismiss();
        }
    }

    private void changeText(View v) {
        if(v == layout_found){
            tv_lost.setText("Found");
            tv_lost.setTag("Found");
        }else if(v == layout_lost){
            tv_lost.setText("Lost");
            tv_lost.setTag("Lost");
        }
    }

    private PopupWindow mPopupWindow;
    /**
     * 弹窗下拉菜单
     */
    private void showListPop() {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_list,null);
        layout_found = (Button) view.findViewById(R.id.layout_found);
        layout_lost = (Button) view.findViewById(R.id.layout_lost);

        layout_found.setOnClickListener(this);
        layout_lost.setOnClickListener(this);
        mPopupWindow = new PopupWindow(view,mScreenWidth,600);
        mPopupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_OUTSIDE){
                    mPopupWindow.dismiss();
                    return true;
                }
                return false;
            }
        });

        mPopupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        mPopupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setAnimationStyle(R.style.MenuPop);
        mPopupWindow.showAsDropDown(layout_action,0,-dip2px(this,2.0f));
    }

    @Override
    public void onEdit(View view) {
        String tag = tv_lost.getTag().toString();
        Intent intent = new Intent(this,AddActivity.class);
        String title = "";
        String phone = "";
        String describe = "";
        if(tag.equals("Lost")){
            title = ((Lost)lostMyAdapter.getItem(mPosition)).getTitle();
            phone = ((Lost)lostMyAdapter.getItem(mPosition)).getPhone();
            describe = ((Lost)lostMyAdapter.getItem(mPosition)).getDescription();
        }else{
            title = ((Found)foundMyAdapter.getItem(mPosition)).getTitle();
            phone = ((Found)foundMyAdapter.getItem(mPosition)).getPhone();
            describe = ((Found)foundMyAdapter.getItem(mPosition)).getDescription();
        }
        intent.putExtra("title",title);
        intent.putExtra("phone",phone);
        intent.putExtra("describe",describe);
        intent.putExtra("from",tag);
        startActivityForResult(intent,Constants.QEQUESTCODE_ADD);
    }

    @Override
    public void onDelete(View view) {
        String tag = tv_lost.getTag().toString();
        if(tag.equals("Lost")){
            deleteLost();
        }else{
            deleteFound();
        }
    }

    private void deleteLost() {
        Lost lost = new Lost();
        Lost l = (Lost) lostMyAdapter.getItem(mPosition);
        lost.setObjectId(l.getObjectId());
        lost.delete(this, new DeleteListener() {
            @Override
            public void onSuccess() {
                queryLosts();
            }

            @Override
            public void onFailure(int i, String s) {

            }
        });
    }

    private void deleteFound() {
        Found found = new Found();
        Found f = (Found)foundMyAdapter.getItem(mPosition);
        found.setObjectId(f.getObjectId());
        found.delete(this, new DeleteListener() {
            @Override
            public void onSuccess() {
                queryFounds();
            }

            @Override
            public void onFailure(int i, String s) {

            }
        });
    }

    int mPosition;
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        mPosition = position;
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        mEditPopupWindow.showAtLocation(parent, Gravity.RIGHT|Gravity.TOP,location[0],
                getStateBarHight()+location[1]);
//        System.out.println(location[0]+"-------------"+location[1]+"-----------"+id+"-------------"+position);
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_OK){
            return;
        }
        switch (requestCode){
            case Constants.QEQUESTCODE_ADD:
                String tag = tv_lost.getTag().toString();
                if(tag.equals("Lost")){
                    queryLosts();
                }else{
                    queryFounds();
                }
                break;
        }
    }

    class ViewHolder{
        TextView tv_title,tv_phone,tv_time,tv_describe;
    }
}
