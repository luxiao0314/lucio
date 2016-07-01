package com.dante.knowledge.mime.presenter.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dante.knowledge.R;
import com.dante.knowledge.mime.model.OnNetRequestListener;
import com.dante.knowledge.mime.model.weather.ShowApiWeather;
import com.dante.knowledge.mime.model.weather.WeatherModelImpl;
import com.dante.knowledge.mime.mvp_frame.presenter.FragmentPresenter;
import com.dante.knowledge.mime.ui.fragment.WbWeatherFragmenView;
import com.dante.knowledge.utils.choice_city.ChoiceCityActivity;

/**
 * Created by luthor on 16/6/7.
 */
public class WbWeatherFragment extends FragmentPresenter<WbWeatherFragmenView> implements View.OnClickListener{
    private WeatherModelImpl mWeatherModel;
    public static final String NEED_MORE_DAY = "1";
    public static final String NEED_INDEX = "1";
    public static final String NEED_ALARM = "1";
    public static final String NEED_3_HOUR_FORCAST = "1";


    @Override
    protected Class<WbWeatherFragmenView> getIViewClass() {
        return WbWeatherFragmenView.class;
    }

    @Override
    protected void initData() {
        super.initData();
        mWeatherModel = new WeatherModelImpl();
    }

    /**
     * 监听事件
     */
    public void bindEvenListener() {
        super.bindEvenListener();
        iView.setOnClickListener(this, R.id.bt_weather);
        iView.setOnClickListener(this, R.id.bt_cities);
        Log.e("WeatherFragment","bindEvenListener");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_weather:
                netWeather();
                break;
            case R.id.bt_cities:
                startActivity(new Intent(getActivity(),ChoiceCityActivity.class));
                break;
        }
    }

    private void netWeather() {
        if(TextUtils.isEmpty(iView.getInputLocation())){
            iView.showSnackbar("输入为空");
            Toast.makeText(getActivity(), "输入为空", Toast.LENGTH_SHORT).show();
            return;
        }
        mWeatherModel.netLoadWeatherWithLocation(iView.getInputLocation(), NEED_MORE_DAY,
                NEED_INDEX, NEED_ALARM, NEED_3_HOUR_FORCAST, new OnNetRequestListener<ShowApiWeather>() {
                    @Override
                    public void onStart() {
                        iView.showLoading();
                    }

                    @Override
                    public void onFinish() {
                        iView.showContent();
                    }

                    @Override
                    public void onSuccess(ShowApiWeather weather) {
//                        Logger.d("onSuccess");
                        iView.closeSoftInput();
                        iView.showNowWeatherDialog(weather);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        iView.showSnackbar("请求错误");
//                        Logger.d("onFailure");
                    }
                });
    }
}
