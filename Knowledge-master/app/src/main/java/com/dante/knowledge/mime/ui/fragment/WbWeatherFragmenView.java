package com.dante.knowledge.mime.ui.fragment;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dante.knowledge.R;
import com.dante.knowledge.mime.model.weather.ShowApiWeather;
import com.dante.knowledge.mime.mvp_frame.view.AppView;
import com.dante.knowledge.mime.ui.LoadingView;
import com.dante.knowledge.mime.ui.widget.ProgressLayout;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.Holder;
import com.orhanobut.dialogplus.ViewHolder;
import com.rey.material.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by luthor on 16/6/7.
 */
public class WbWeatherFragmenView extends AppView implements LoadingView{
    private ImageView iv_weather;
    private TextView tv_weather, tv_aqi, tv_sd, tv_wind_direction, tv_wind_power, tv_temperature_time,
            tv_temperature;

    private LinearLayout ll_dialog_holder;

    @Bind(R.id.progress_layout)
    ProgressLayout mProgressLayout;
    @Bind(R.id.et_location)
    EditText et_location;

    @Override
    protected int getRootLayoutId() {
        return R.layout.weather_fragment;
    }

    /**
     * 获取输入的地名
     * @return
     */
    public String getInputLocation(){
        return et_location.getText().toString();
    }

    /**
     * 显示当前天气弹窗
     */
    public void showNowWeatherDialog(ShowApiWeather weather) {
        ll_dialog_holder = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.dialog_weather, null);
        Holder holder = new ViewHolder(ll_dialog_holder);
        findHolderChildView(holder);
//        GlideUtil.loadImage(getActivity(), weather.now.weather_pic, iv_weather);
        Glide.with(getActivity())
                .load(weather.now.weather_pic)
                .placeholder(R.mipmap.ic_holding)
                .error(R.mipmap.ic_error)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(iv_weather);
        tv_weather.setText(weather.now.weather);
        tv_temperature.setText(weather.now.temperature + "℃");
        tv_temperature_time.setText(weather.now.temperature_time);
        tv_aqi.setText(String.format(getActivity().getResources().getString(R.string.weather_dialog_aqi),
                weather.now.aqi));
        tv_sd.setText(String.format(getActivity().getResources().getString(R.string.weather_dialog_sd),
                weather.now.sd));
        tv_wind_direction.setText(String.format(getActivity().getResources().getString(R.string.weather_dialog_wind_direction),
                weather.now.wind_direction));
        tv_wind_power.setText(String.format(getActivity().getResources().getString(R.string.weather_dialog_wind_power),
                weather.now.wind_power));
        showOnlyContentDialog(holder, Gravity.BOTTOM, false);
    }

    /**
     * 查找弹窗的holder的子控件
     *
     * @param holder
     */
    private void findHolderChildView(Holder holder) {
        iv_weather = (ImageView) holder.getInflatedView().findViewById(R.id.iv_weather);
        tv_weather = (TextView) holder.getInflatedView().findViewById(R.id.tv_weather);
        tv_temperature = (TextView) holder.getInflatedView().findViewById(R.id.tv_temperature);
        tv_temperature_time = (TextView) holder.getInflatedView().findViewById(R.id.tv_temperature_time);
        tv_aqi = (TextView) holder.getInflatedView().findViewById(R.id.tv_aqi);
        tv_sd = (TextView) holder.getInflatedView().findViewById(R.id.tv_sd);
        tv_wind_direction = (TextView) holder.getInflatedView().findViewById(R.id.tv_wind_direction);
        tv_wind_power = (TextView) holder.getInflatedView().findViewById(R.id.tv_wind_power);
    }

    /**
     * 仅显示内容的dialog
     *
     * @param holder
     * @param gravity         显示位置（居中，底部，顶部）
     * @param expanded        是否支持展开（有列表时适用）
     */
    private void showOnlyContentDialog(Holder holder, int gravity,
                                       boolean expanded) {
        final DialogPlus dialog = DialogPlus.newDialog(getActivity())
                .setContentHolder(holder)
                .setGravity(gravity)
                .setExpanded(expanded)
                .setCancelable(true)
                .create();
        dialog.show();
    }
    /**
     * 关闭软键盘
     */
    public void closeSoftInput(){
        InputMethodManager inputMethodManager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(et_location.getWindowToken(), 0);
    }

    @Override
    public void showLoading() {
        mProgressLayout.showLoading();
    }

    @Override
    public void showContent() {
        if (!mProgressLayout.isContent()) {
            mProgressLayout.showContent();
        }
    }

    @Override
    public void showError(int messageId, View.OnClickListener listener) {

    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void destory() {
        ButterKnife.unbind(this);
    }

    @Override
    public void initWeidget() {
        ButterKnife.bind(this,getRootView());
    }

}
