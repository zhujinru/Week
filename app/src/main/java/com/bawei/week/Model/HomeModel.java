package com.bawei.week.Model;

import com.bawei.week.Model.Bean.GsonBean;
import com.bawei.week.contract.HomeContract;
import com.bawei.week.util.NetUtil;
import com.google.gson.Gson;

public class HomeModel implements HomeContract.IModel {
    @Override
    public void getHomeData(IModelCallBack iModelCallBack) {
        NetUtil.getInstance().getJsonGet("http://blog.zhaoliang5156.cn/api/shop/fulishe1.json", new NetUtil.MyCallBack() {
            @Override
            public void onGetJson(String json) {
                GsonBean gsonBean = new Gson().fromJson(json, GsonBean.class);
                iModelCallBack.onHomesuccess(gsonBean);
            }

            @Override
            public void onError(Throwable throwable) {
               iModelCallBack.onHomeFailure(throwable);
            }
        });
    }
}
