package com.haixue.highmvp.mvp;

import android.util.SparseArray;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C) 2015 - 2018 HAIXUE Inc., All Rights Reserved.
 *
 * @author: sunyuanming@haixue.com
 * @date: 2018/8/1
 */
public class PresenterStore<P extends BasePresenter> {
    private static final String DEFAULT_KEY="PresenterStore.DefaultKey";
    private Map<String,P> map=new HashMap<>();

    public final void put(String key,P presenter){
        P oldPresenter=map.put(DEFAULT_KEY+":"+key,presenter);
        if(oldPresenter!=null) oldPresenter.onCleared();
    }
    public final P get(String key){
        return map.get(DEFAULT_KEY+":"+key);
    }
    public final void clear(){
        for(P presenter :map.values()){
            presenter.onCleared();
        }
        map.clear();
    }

    public final  int getSize(){
        return map.size();
    }
    public final Map<String,P> getMap(){
        return map;
    }
}
