package com.ui.other;

import android.content.Intent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;

import com.base.ex.ExBaseActivity;
import com.base.Constants;
import com.util.AnimationUtil;
import com.util.SpUtil;
import com.util.StatusBarUtil;
import com.ui.home.HomeActivity;
import com.ui.main.R;
import com.view.widget.FireView;

import butterknife.Bind;

/**
 * Created by baixiaokang on 16/4/28.
 */
public class FlashActivity extends ExBaseActivity {

    @Bind(R.id.fl_main)
    FrameLayout fl_main;
    @Bind(R.id.view)
    View view;


    @Override
    public int getLayoutId() {
        return R.layout.activity_flash;
    }

    @Override
    public void initView() {
        StatusBarUtil.setTranslucentBackground(this);
        FireView mFireView = new FireView(this);
        fl_main.addView(mFireView,
                new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT));

        AlphaAnimation anim = new AlphaAnimation(0.8f, 0.1f);
        anim.setDuration(5000);
        view.startAnimation(anim);
        //动画结束之后跳转到主页面
        AnimationUtil.setAnimationListener(anim, () -> {

            if (SpUtil.getBoolean(Constants.IS_FIRST_COME,true))
            startActivity(new Intent(mContext, HomeActivity.class));
            SpUtil.putBoolean(Constants.IS_FIRST_COME,false);
            finish();
        });
    }

   /* @Override
    public void initPresenter() {
    }*/
}
