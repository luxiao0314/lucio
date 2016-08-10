package com.base.ex;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.base.RxManager;
import com.ui.main.R;

/**
 * @Description 协议接口基类
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 16/7/31 21:25
 * @Version v2.6.0
 */
public interface ExBaseContract {

    /**
     * 将公用的方法抽取在基类中
     * @param <V>   View的泛型
     * @param <M>   Model的泛型
     */
    abstract class PresenterImpl<V, M extends ModelImpl> {
        public Context context;
        public M mModel;
        public V mView;
        public RxManager mRxManager = new RxManager();
        private Activity mActivity;

        public void setViewModel(V view, M model, Activity mActivity) {
            this.mView = view;
            this.mModel = model;
            this.mActivity = mActivity;
            this.onStart();
        }

        /**
         * 判断activity页面是否销毁:主要是销毁之后,数据不在回调,避免内存泄漏和null指针异常
         * @return  true:表示未销毁,false:表示已经销毁
         */
        public boolean isActive(){
            if (mView != null) {
                return true;
            }
            return false;
        }

        /**
         * activity跳转
         * @param intent
         */
        public void startActivity(Intent intent){
            mActivity.startActivity(intent);
            mActivity.overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        }

        /**
         * 结束activity
         */
        public void finish(){
            mActivity.finish();
            mActivity.overridePendingTransition(R.anim.slide_pre_in, R.anim.slide_pre_out);
        }

        public Intent getIntent(){
            return mActivity.getIntent();
        }

        public abstract void onStart();

        public void onDestroy() {
            if (mView != null) mView = null;
            mRxManager.clear();
        }
    }

    /**
     * View的接口基类
     */
    interface ViewImpl {}

    /**
     * Model接口基类
     */
    interface ModelImpl {}
}
