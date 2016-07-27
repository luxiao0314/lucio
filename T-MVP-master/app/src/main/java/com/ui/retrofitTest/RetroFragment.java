package com.ui.retrofitTest;

import com.base.BaseFragment;
import com.ui.main.R;

/**
 * Created by luthor on 16/7/27.
 */
public class RetroFragment extends BaseFragment implements RetrofitContract.View{

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initView() {

    }
}
