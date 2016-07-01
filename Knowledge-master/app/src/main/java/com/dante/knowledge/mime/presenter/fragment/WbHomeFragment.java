package com.dante.knowledge.mime.presenter.fragment;

import com.dante.knowledge.mime.mvp_frame.presenter.FragmentPresenter;
import com.dante.knowledge.mime.ui.fragment.WbHomeFragmenView;

/**
 * Created by luthor on 16/6/7.
 */
public class WbHomeFragment extends FragmentPresenter<WbHomeFragmenView> {
    @Override
    protected Class<WbHomeFragmenView> getIViewClass() {
        return WbHomeFragmenView.class;
    }
}
