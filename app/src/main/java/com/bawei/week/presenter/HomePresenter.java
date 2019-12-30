package com.bawei.week.presenter;

import com.bawei.week.Model.Bean.GsonBean;
import com.bawei.week.Model.HomeModel;
import com.bawei.week.base.BasePresenter;
import com.bawei.week.contract.HomeContract;

public class HomePresenter extends BasePresenter<HomeContract.IView> implements HomeContract.IPresenter {

    private HomeModel homeModel;

    @Override
    protected void initModel() {
        homeModel = new HomeModel();
    }

    @Override
    public void getHomeData() {
       homeModel.getHomeData(new HomeContract.IModel.IModelCallBack() {
           @Override
           public void onHomesuccess(GsonBean gsonBean) {
               view.onHomesuccess(gsonBean);
           }

           @Override
           public void onHomeFailure(Throwable throwable) {
               view.onHomeFailure(throwable);
           }
       });
    }
}
