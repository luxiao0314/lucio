package com.ui.retrofitTest;

import com.base.BaseModel;
import com.base.BasePresenter;
import com.base.BaseView;

/**
 * Created by luthor on 16/7/27.
 */
public interface RetrofitContract {
    interface Model extends BaseModel {}

    interface View extends BaseView {}

    abstract class Presenter extends BasePresenter<Model, View> {}
}
