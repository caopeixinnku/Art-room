package com.artroom.qqlist;

import android.content.ContentValues;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.artroom.qqlist.base.BaseActivity;
import com.artroom.qqlist.bean.EventBus_Tag;
import com.artroom.qqlist.bean.QQBean;
import com.artroom.qqlist.util.DateUtil;
import com.artroom.qqlist.util.StrUtil;
import com.artroom.qqlist.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.litepal.crud.DataSupport;

public class   QQActivity extends BaseActivity {

    TextView tv_commit, tv_cannal, tv_title;
    EditText et_phone, et_pwd;
    ImageView iv;
    int myTag;//标记
    String content;
    int stype = 0;//0添加，1修改
    QQBean bean;

    @Override
    protected void setContent() {
        super.setContent();
        setContentView(R.layout.activity_qq);
        registerEventBus();

    }
    public void registerEventBus() {
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().register(this);
    }
    @Override
    protected void initData() {

        //init view
        iv = findViewById(R.id.iv);
        et_phone = findViewById(R.id.et_phone);
        et_pwd = findViewById(R.id.et_pwd);
        tv_commit = findViewById(R.id.tv_commit);
        tv_cannal = findViewById(R.id.tv_cannal);
        tv_title = findViewById(R.id.tv_title);
        //set view
        stype = getIntent().getIntExtra("stype", 0);
        bean = (QQBean) getIntent().getSerializableExtra("bean");
        if (stype == 0) {
            tv_title.setText("添加");
        } else {
            tv_title.setText("修改");
            et_phone.setText(bean.getName());
            et_pwd.setText(bean.getContents());
            iv.setImageResource(bean.getmPic());
            pic = bean.getmPic();

        }
        //取消
        tv_cannal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //确定
        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempPhone = et_phone.getText().toString().trim();
                String tempPwd = et_pwd.getText().toString().trim();
                if (StrUtil.isEmpty(tempPhone) || StrUtil.isEmpty(tempPwd)) {
                    ToastUtil.showToast(QQActivity.this, "名称不能为空");
                    return;
                }
                if (StrUtil.isEmpty(tempPwd) || StrUtil.isEmpty(tempPwd)) {
                    ToastUtil.showToast(QQActivity.this, "内容不能为空");
                    return;
                }

                if (stype == 0) {//add
                    QQBean tempBean = new QQBean();
                    tempBean.setName(tempPhone);
                    tempBean.setContents(tempPwd);
                    tempBean.setTimes(DateUtil.getTodayData_3());
                    tempBean.setmPic(pic);

                    tempBean.save();
                    if (tempBean.isSaved()) {
                        ToastUtil.showToast(QQActivity.this, "添加成功");
                    }
                } else {//updata
                    ContentValues values = new ContentValues();
                    values.put("name", tempPhone);
                    values.put("contents", tempPwd);
                    values.put("mPic", pic);
                    DataSupport.updateAll(QQBean.class, values, "times = ?", bean.getTimes());
                    ToastUtil.showToast(QQActivity.this, "修改成功");
                }

                EventBus.getDefault().post(new EventBus_Tag(1));
                finish();
            }
        });

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QQActivity.this, ChoicePicActivity.class));
            }
        });

    }

    private int pic = R.mipmap.ic_launcher_round;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventBus_Tag event) {
        switch (event.getTag()) {
            case 2:
                iv.setImageResource(event.getPosition());
                pic = event.getPosition();
                break;
        }
    }


    @Override
    protected void initListener() {

    }


}
