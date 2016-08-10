package com.ui.contract;

import com.base.ex.ExBaseContract;
import com.data.CreatedResult;
import com.data.entity._User;

import java.io.File;

import rx.Observable;

/**
 * Created by baixiaokang on 16/5/5.
 */
public interface UserContract {
    interface Model extends ExBaseContract.ModelImpl {
        Observable<CreatedResult> upFile(File file);

        Observable upUser( _User user);
    }


    interface View extends ExBaseContract.ViewImpl {

        void showMsg(String msg);
       void  initUser(_User user);
    }

    abstract class Presenter extends ExBaseContract.PresenterImpl<View, Model> {

        public abstract void upLoadFace(File f);

        public abstract void upUserInfo(String face);
    }
}
