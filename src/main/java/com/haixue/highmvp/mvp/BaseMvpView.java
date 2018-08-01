package com.haixue.highmvp.mvp;

/**
 * Copyright (C) 2015 - 2018 HAIXUE Inc., All Rights Reserved.
 *
 * @author: sunyuanming@haixue.com
 * @date: 2018/8/1
 */
public interface BaseMvpView {
    /**
     * 错误回调
     * @param error
     */
    void showError(String error);

    void complete();

    /**
     * 展示进度
     * @param isShow
     */
    void showProgress(boolean isShow);

}
