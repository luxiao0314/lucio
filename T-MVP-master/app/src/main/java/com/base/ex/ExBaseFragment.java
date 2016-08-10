package com.base.ex;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.BaseViewModel;
import com.base.Constants;
import com.base.LocalBroadcast;
import com.base.MarkAble;
import com.util.TUtil;

import java.io.Serializable;

import butterknife.ButterKnife;

/**
 * fragment基类，封装了自动获取viewmodel以及广播监听
 */
public abstract class ExBaseFragment<T extends ExBaseContract.PresenterImpl, E extends ExBaseContract.ModelImpl> extends Fragment implements MarkAble {
    protected MyBroadCastReceiver mLocalBroadCastReceiver;

    protected BaseViewModel mViewData;
    private FragmentActivity mContext;
    private T mPresenter;
    private E mModel;

    @Override
    public String getInstanceTag() {
        return this.getClass().getSimpleName() + this.hashCode();
    }

    protected class MyBroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            int code = intent.getIntExtra(Constants.MESSAGE_CODE, Constants.MESSAGE_SUCCESS);
            String content = intent.getStringExtra(Constants.MESSAGE_CONTENT);
            Serializable data = intent.getSerializableExtra(Constants.MESSAGE_DATA);
            onMessageReceive(action, code, content, data);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mViewData = (BaseViewModel) savedInstanceState.getSerializable(Constants.PAGE_BASE_EXCHANGEMODEL);
        } else if (getArguments() != null) {
            mViewData = (BaseViewModel) getArguments().getSerializable(Constants.PAGE_BASE_EXCHANGEMODEL);
        }
        mContext = getActivity();
        ButterKnife.bind(mContext);
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        this.initView();
        if (this instanceof ExBaseContract.ViewImpl) mPresenter.setViewModel(this, mModel,getActivity());
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), null);
        return view;
    }

    /**
     * 列出页面需要接收的广播类型
     *
     * @return
     */
    protected String[] listReceiveActions(){
        return new String[0];
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mViewData != null) {
            outState.putSerializable(Constants.PAGE_BASE_EXCHANGEMODEL, mViewData);
        }
        super.onSaveInstanceState(outState);

    }

    /**
     * boardcast响应方法
     *
     * @param action
     * @param code
     * @param message
     * @param data
     */
    protected void onMessageReceive(String action, int code, String message, Serializable data) {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mLocalBroadCastReceiver != null) {
            LocalBroadcast.unregisterLocalReceiver(mLocalBroadCastReceiver);
        }
        mLocalBroadCastReceiver = new MyBroadCastReceiver();
        LocalBroadcast.registerLocalReceiver(mLocalBroadCastReceiver, listReceiveActions());
    }

    @Override
    public void onDestroy() {
        LocalBroadcast.unregisterLocalReceiver(mLocalBroadCastReceiver);
        if (mPresenter != null) mPresenter.onDestroy();
        super.onDestroy();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

}
