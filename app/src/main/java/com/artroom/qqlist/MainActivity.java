package com.artroom.qqlist;

import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


import com.artroom.qqlist.base.BaseActivity;
import com.artroom.qqlist.bean.EventBus_Tag;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class   MainActivity extends BaseActivity {

    @BindView(R.id.tv_main)
    TextView tv_main;
    @BindView(R.id.tv_me)
    TextView tv_me;

    public Fragment getoFragment() {
//        if (null == oFragment) {
            oFragment = new HomeFragment();
//        }
        return oFragment;
    }

    public Fragment gettFragment() {
//        if (null == tFragment) {
           tFragment = new MeFragment();
//        }
        return tFragment;
    }

    private Fragment oFragment, tFragment;

    @Override
    protected void setContent() {
        super.setContent();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        EventBus.getDefault().unregister(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initData() {
        DisplayFragment(0);
    }

    @Override
    protected void initListener() {
        tv_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayFragment(0);
            }
        });

        tv_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayFragment(1);
            }
        });
    }

    public void DisplayFragment(int position) {
//        myAdapter.setSelPosi(position);
//        myAdapter.notifyDataSetChanged();
        switch (position) {

            case 0:
                changeFragment(R.id.fragment, getoFragment());
                break;
            case 1:
                changeFragment(R.id.fragment, gettFragment());
                break;


        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventBus_Tag event) {
        switch (event.getTag()) {
            case 2:
//                DisplayFragment(1);
                break;


        }
    }

}
