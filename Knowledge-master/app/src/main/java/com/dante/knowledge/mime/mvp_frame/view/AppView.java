package com.dante.knowledge.mime.mvp_frame.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * view层基类,所有的view都继承appView
 *
 * @author LUXIAO418@pingan.com.cn
 * @date created at 16/6/7 15:29
 */
public abstract class AppView implements IView {

    private final SparseArray<View> mViews = new SparseArray<View>();
    private View rootView;

    @Override
    public void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int rootLayoutId = getRootLayoutId();
        rootView = inflater.inflate(rootLayoutId, container, false);
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void initWeidget() {

    }

    @Override
    public void destory() {

    }

    /**
     * 获取布局ID,由子类实现
     */
    protected abstract int getRootLayoutId();

    /**
     * 一些简化代码的
     */
    public <T extends View> T get$(int id) {
        return bindView(id);
    }

    public <T extends View> T bindView(int id) {
        T view = (T) mViews.get(id);
        if (view == null) {
            view = (T) rootView.findViewById(id);
            mViews.put(id, view);
        }
        return view;
    }

    /**
     * 设置点击事件
     * @param listener
     * @param ids
     */
    public void setOnClickListener(View.OnClickListener listener, int... ids) {
        if (ids == null) {
            return;
        }
        for (int id : ids) {
            get$(id).setOnClickListener(listener);
        }
    }

    public void showSnackbar(String msg) {
        Snackbar.make(rootView, msg, Snackbar.LENGTH_LONG)
                .show();
    }

    public <T extends Activity> T getActivity() {
        return (T) rootView.getContext();
    }
}
