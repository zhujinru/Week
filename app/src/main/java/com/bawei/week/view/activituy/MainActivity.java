package com.bawei.week.view.activituy;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.week.Model.Bean.GsonBean;
import com.bawei.week.R;
import com.bawei.week.base.BaseActivity;
import com.bawei.week.contract.HomeContract;
import com.bawei.week.presenter.HomePresenter;
import com.bawei.week.view.adapter.MyAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<HomePresenter> implements HomeContract.IView {

    @BindView(R.id.a_recy)
    RecyclerView aRecy;

    @Override
    protected HomePresenter providerPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initData() {
       mPresenter.getHomeData();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onHomesuccess(GsonBean gsonBean) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        aRecy.setLayoutManager(gridLayoutManager);
        List<GsonBean.DataBean> data = gsonBean.getData();
        MyAdapter myAdapter = new MyAdapter(data);
        aRecy.setAdapter(myAdapter);
        myAdapter.setOnItemClickLisetner(new MyAdapter.onItemClickLisetner() {
            @Override
            public void onItemClick(int i) {
                 startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });
    }

    @Override
    public void onHomeFailure(Throwable throwable) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
