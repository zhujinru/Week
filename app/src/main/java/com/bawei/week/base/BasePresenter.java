package com.bawei.week.base;

public abstract class BasePresenter<V> {
    public V view;

    public BasePresenter() {
        initModel();
    }

    public void attach(V view) {
        this.view = view;
    }
public void dettach(){
        view= null;
}
    protected abstract void initModel();
}
