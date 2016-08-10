package com.ui.contract;

import com.base.ex.ExBaseContract;

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 16/8/10 23:51
 * @Version v2.6.0
 */
public interface MainContract {

    interface Model extends ExBaseContract.ModelImpl {
    }


    interface View extends ExBaseContract.ViewImpl {
    }

    abstract class Presenter extends ExBaseContract.PresenterImpl<View, Model> {
    }
}
