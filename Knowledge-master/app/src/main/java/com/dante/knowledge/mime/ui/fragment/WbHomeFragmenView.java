package com.dante.knowledge.mime.ui.fragment;

import com.dante.knowledge.R;
import com.dante.knowledge.mime.mvp_frame.view.AppView;

import butterknife.ButterKnife;

/**
 * Created by luthor on 16/6/7.
 */
public class WbHomeFragmenView extends AppView {
    @Override
    protected int getRootLayoutId() {
        return R.layout.home_fragment;
    }

    @Override
    public void initWeidget() {
        ButterKnife.bind(this,getRootView());
    }

    @Override
    public void destory() {
        ButterKnife.unbind(this);
    }
}
