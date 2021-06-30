package com.artroom.qqlist;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.artroom.qqlist.base.BaseActivity;
import com.artroom.qqlist.base.MyApplication;
import com.artroom.qqlist.bean.UserInfoBean;
import com.artroom.qqlist.util.StrUtil;
import com.artroom.qqlist.util.ToastUtil;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class    ResetActivity extends BaseActivity {

    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.et_pwd)
    EditText et_pwd;
    @BindView(R.id.tv_login)
    TextView tv_login;

    @Override
    protected void setContent() {
        super.setContent();
        setContentView(R.layout.activity_reset);
        ButterKnife.bind(this);

    }
    @Override
    protected void initData() {
        tv_phone.setText(MyApplication.curUser);
    }

    @Override
    protected void initListener() {
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = et_pwd.getText().toString().trim();
                if (StrUtil.isEmpty(temp)){
                    ToastUtil.showToast(myActivity,"新密码不能为空");
                    return;
                }
                List<UserInfoBean> list = DataSupport.where("name = " + MyApplication.curUser ).find(UserInfoBean.class);
                UserInfoBean s= list.get(0);
                s.setPwd(temp);
                s.save();
                if (s.isSaved()){
                    ToastUtil.showToast(myActivity,"新密码设置成功");
                    finish();
                }
            }
        });
    }
}
