package com.dante.knowledge.mime.mvp_frame.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dante.knowledge.mime.mvp_frame.model.CloseView;
import com.dante.knowledge.mime.mvp_frame.view.IView;

/**
 * Created by Daemon on 2015/11/20.
 */
public abstract class FragmentPresenter<T extends IView> extends Fragment {

    public T iView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            iView = getIViewClass().newInstance();
        } catch (java.lang.InstantiationException e) {
            throw new RuntimeException("create IView error");
        } catch (IllegalAccessException e) {
            throw new RuntimeException("create IView error");
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        iView.create(inflater, container, savedInstanceState);
        Log.e("Daemon", "onCreateView");
        return iView.getRootView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("Daemon", "onViewCreated");
        iView.initWeidget();
        initData();
        initView();
        bindEvenListener();
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        iView.initWeidget();
//    }

    protected void initView() {}

    protected void initData() {}

    /**
     * 监听事件
     */
    public void bindEvenListener() {}

    public void onEventMainThread(CloseView event) {}

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (iView == null) {
            try {
                iView = getIViewClass().newInstance();
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        iView.destory();
        iView = null;
    }

    protected abstract Class<T> getIViewClass();
}
