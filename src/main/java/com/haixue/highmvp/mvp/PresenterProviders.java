package com.haixue.highmvp.mvp;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.haixue.highmvp.annotation.CreatePresenter;
import com.haixue.highmvp.annotation.PresenterVariable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Copyright (C) 2015 - 2018 HAIXUE Inc., All Rights Reserved.
 *
 * @author: sunyuanming@haixue.com
 * @date: 2018/8/1
 */
public class PresenterProviders {
    private PresenterStore mPresenterStore=new PresenterStore();
    private Activity mActivity;
    private Fragment mFragment;
    private Class<?> mClass;

    public static PresenterProviders inject(Activity mActivity){
        return new PresenterProviders(mActivity,null);
    }
    public static PresenterProviders inject(Fragment mFragment){
        return new PresenterProviders(null,mFragment);
    }

    private PresenterProviders(Activity mActivity,Fragment mFragment){
        if(mActivity!=null){
            this.mActivity=mActivity;
            this.mClass=mActivity.getClass();
        }
        if(mFragment!=null){
            this.mFragment=mFragment;
            this.mClass=mFragment.getClass();
        }
        resolveCreatePresenter();
        resolvePresenterVariable();
    }

    private <P extends BasePresenter> PresenterProviders resolveCreatePresenter(){
        CreatePresenter createPresenter=mClass.getAnnotation(CreatePresenter.class);
        if(createPresenter!=null){
            Class<P>[] classes = (Class<P>[]) createPresenter.presenter();
            for(Class<P> classZ :classes){
                String canonicalName=classZ.getCanonicalName();
                try {
                    mPresenterStore.put(canonicalName,classZ.newInstance());
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
        return this;
    }
    private <P extends BasePresenter> PresenterProviders resolvePresenterVariable(){
        for(Field field :mClass.getDeclaredFields()){
            Annotation[] anns = field.getDeclaredAnnotations();
            if (anns.length < 1) {
                continue;
            }
            if (anns[0] instanceof PresenterVariable) {
                String canonicalName = field.getType().getName();
                P presenterInstance = (P) mPresenterStore.get(canonicalName);
                if (presenterInstance != null) {
                    try {
                        field.setAccessible(true);
                        field.set(mFragment != null ? mFragment : mActivity, presenterInstance);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return this;
    }

    private static Application checkApplication(Activity mActivity){
       Application application=mActivity.getApplication();
       if(application==null) throw  new IllegalStateException("activity is not attach application");
        return application;
    }
    private static Activity checkActivity(Fragment mFragment){
        Activity activity=mFragment.getActivity();
        if(activity==null) throw new IllegalStateException("fragment is not attach activity");
        return activity;
    }

    public <P extends BasePresenter> P getPresenter(int index) {
        CreatePresenter createPresenter = mClass.getAnnotation(CreatePresenter.class);
        if (createPresenter == null) {
            return null;
        }
        if (createPresenter.presenter().length == 0) {
            return null;
        }
        if (index >= 0 && index < createPresenter.presenter().length) {
            String key = createPresenter.presenter()[index].getCanonicalName();
            BasePresenter presenter = mPresenterStore.get(key);
            if (presenter != null) {
                return (P) presenter;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public PresenterStore getPresenterStore() {
        return mPresenterStore;
    }
}
