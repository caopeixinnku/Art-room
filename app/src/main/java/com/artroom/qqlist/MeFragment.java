package com.artroom.qqlist;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.artroom.qqlist.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class   MeFragment extends BaseFragment {

    @BindView(R.id.tv_reset)
    TextView tv_reset;
    @BindView(R.id.tv_exit)
    TextView tv_exit;

    public MeFragment() {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        ButterKnife.bind(this, view);

        initData();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initData() {


        tv_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ResetActivity.class));

            }
        });
        tv_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });

    }



}
