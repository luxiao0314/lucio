package com.dante.knowledge.mime.mvp_frame.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * View层接口
 * Created by Daemon on 2015/11/20.
 */
public interface IView {

    void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * @return 返回view对象
     */
    View getRootView();

    void initWeidget();

    void destory();
}
