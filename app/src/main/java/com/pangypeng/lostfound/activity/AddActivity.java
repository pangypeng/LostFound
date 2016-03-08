package com.pangypeng.lostfound.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pangypeng.lostfound.R;
import com.pangypeng.lostfound.bean.Found;
import com.pangypeng.lostfound.bean.Lost;

import cn.bmob.v3.listener.SaveListener;

/**
 * 作者 pang
 * 时间 2016/3/8 0008 8:30
 * 文件 LostFound
 * 描述
 */
public class AddActivity extends BaseActivity implements View.OnClickListener {

    EditText edit_title, edit_phone, edit_describe;
    Button btn_back, btn_true;
    TextView tv_add;

    String from = "";
    String old_title = "";
    String old_phone = "";
    String old_describe = "";

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_add);
    }

    @Override
    public void initViews() {
        edit_title = (EditText) findViewById(R.id.edit_title);
        edit_phone = (EditText) findViewById(R.id.edit_phone);
        edit_describe = (EditText) findViewById(R.id.edit_desctibe);

        btn_back = (Button) findViewById(R.id.btn_back);
        btn_true = (Button) findViewById(R.id.btn_true);

        tv_add = (TextView) findViewById(R.id.tv_add);
    }

    @Override
    public void initListener() {
        btn_back.setOnClickListener(this);
        btn_true.setOnClickListener(this);
    }

    @Override
    public void initData() {
        from = getIntent().getStringExtra("from");
        old_title = getIntent().getStringExtra("title");
        old_phone = getIntent().getStringExtra("phone");
        old_describe = getIntent().getStringExtra("describe");

        edit_title.setText(old_title);
        edit_phone.setText(old_phone);
        edit_describe.setText(old_describe);

        if(from.equals("Lost")){
            tv_add.setText("添加失物信息");
        }else{
            tv_add.setText("添加招领信息");
        }
    }

    @Override
    public void onClick(View v) {
        if(v == btn_true){
            addByType();
        }else if(v == btn_back){
            finish();
        }
    }
    String title = "";
    String phone = "";
    String describe = "";

    /**
     * 根据类型添加
     */
    private void addByType() {
        title = edit_title.getText().toString();
        phone = edit_phone.getText().toString();
        describe = edit_describe.getText().toString();

        if(TextUtils.isEmpty(title)){
            showToast("请输入标题");
            return;
        }
        if(TextUtils.isEmpty(phone)){
            showToast("请输入电话号码");
            return;
        }
        if(TextUtils.isEmpty(describe)){
            showToast("请填写描述");
            return;
        }
        if(from.equals("Lost")){
            addLost();
        }else{
            addFound();
        }
    }

    /**
     * 添加招领信息
     */
    private void addFound() {
        Found found = new Found();
        found.setTitle(title);
        found.setPhone(phone);
        found.setDescription(describe);
        found.save(this, new SaveListener() {
            @Override
            public void onSuccess() {
                showToast("招领信息添加成功");
                setResult(RESULT_OK);
                finish();
            }

            @Override
            public void onFailure(int i, String s) {
                showToast("招领信息添加失败："+s);
            }
        });
    }

    /**
     * 添加失物信息
     */
    private void addLost() {
        Lost lost = new Lost();
        lost.setTitle(title);
        lost.setPhone(phone);
        lost.setDescription(describe);
        lost.save(this, new SaveListener() {
            @Override
            public void onSuccess() {
                showToast("失物信息添加成功");
                setResult(RESULT_OK);
                finish();
            }

            @Override
            public void onFailure(int i, String s) {
                showToast("失物信息添加失败："+s);
            }
        });
    }
}
