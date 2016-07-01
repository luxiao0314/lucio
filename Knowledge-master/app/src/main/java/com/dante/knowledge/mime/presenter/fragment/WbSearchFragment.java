package com.dante.knowledge.mime.presenter.fragment;

import com.dante.knowledge.mime.mvp_frame.presenter.FragmentPresenter;
import com.dante.knowledge.mime.ui.fragment.WbSearchFragmenView;

/**
 * Created by luthor on 16/6/7.
 */
public class WbSearchFragment extends FragmentPresenter<WbSearchFragmenView> {
    @Override
    protected Class<WbSearchFragmenView> getIViewClass() {
        return WbSearchFragmenView.class;
    }
}
