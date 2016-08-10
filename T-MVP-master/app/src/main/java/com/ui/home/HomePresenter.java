package com.ui.home;

import com.C;
import com.util.SpUtil;
import com.data.entity._User;

/**
 * Created by baixiaokang on 16/4/22.
 */
public class HomePresenter extends HomeContract.Presenter {

    @Override
    public void getTabList() {
        mView.showTabList(mModel.getTabs());
    }

    @Override
    public void getUserInfo() {
        _User user = SpUtil.getUser();
        if (user != null)
            mView.initUserInfo(user);
    }

    @Override
    public void onStart() {
        getTabList();   //获取tag列表
        getUserInfo();
        mRxManager.on(C.EVENT_LOGIN, arg -> mView.initUserInfo((_User) arg));
    }
}
