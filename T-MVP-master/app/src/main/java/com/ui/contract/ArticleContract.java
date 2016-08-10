package com.ui.contract;

import com.base.ex.ExBaseContract;
import com.data.Pointer;
import com.data.entity.Image;
import com.data.entity._User;

import rx.Observable;


/**
 * Created by baixiaokang on 16/4/22.
 */
public interface ArticleContract {
    interface Model extends ExBaseContract.ModelImpl {
        Observable createComment(String content, Pointer article, Pointer user);
    }


    interface View extends ExBaseContract.ViewImpl {
        void commentSuc();
        void commentFail();
        void showLoginAction();
    }

    abstract class Presenter extends ExBaseContract.PresenterImpl<View, Model> {
        public abstract void createComment(String content, Image article, _User user);
        @Override
        public void onStart() {}
    }
}

