package com.bawei.week.contract;

import com.bawei.week.Model.Bean.GsonBean;

public interface HomeContract {
     interface IView{
        void onHomesuccess(GsonBean gsonBean);
        void onHomeFailure(Throwable throwable);
    }
    interface IPresenter{
         void getHomeData();
    }
    interface IModel{
        void getHomeData(IModelCallBack iModelCallBack);
        interface IModelCallBack{
            void onHomesuccess(GsonBean gsonBean);
            void onHomeFailure(Throwable throwable);
        }
    }
}
