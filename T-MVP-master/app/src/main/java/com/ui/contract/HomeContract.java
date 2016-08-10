package com.ui.contract;

import com.base.ex.ExBaseContract;
import com.data.entity._User;

/**
 * Created by baixiaokang on 16/4/22.
 */
public interface HomeContract {
    interface Model extends ExBaseContract.ModelImpl {
        String[] getTabs();
    }


    interface View extends ExBaseContract.ViewImpl {
        void showTabList(String[] mTabs);

        void initUserInfo(_User user);
    }

    abstract class Presenter extends ExBaseContract.PresenterImpl<View, Model> {
        public abstract void getTabList();

        public abstract void getUserInfo();
    }
}
