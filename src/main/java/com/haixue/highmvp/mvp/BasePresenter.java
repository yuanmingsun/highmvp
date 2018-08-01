package com.haixue.highmvp.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Copyright (C) 2015 - 2018 HAIXUE Inc., All Rights Reserved.
 *
 * @author: sunyuanming@haixue.com
 * @date: 2018/8/1
 */
public class BasePresenter<V> {
    private Context mContext;
    protected V mView;

    public void attachView(Context mContext,V mView){
        this.mContext=mContext;
        this.mView=mView;
    }
    public void onCleared(){}
    public void detachView(){
        this.mView=null;
    }
    public boolean isAttachView(){
        return this.mView!=null;
    }

    public void onCreatePresenter(@Nullable Bundle savedInstanceState){

    }
    public void onDestroyPrensenter(){
        this.mContext=null;
        this.detachView();

    }
    public void onSaveInstanceState(Bundle bundle){}
}
