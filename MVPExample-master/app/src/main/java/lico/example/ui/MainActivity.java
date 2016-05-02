package lico.example.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.NavigationView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import lico.example.R;
import lico.example.presenter.ActivityPresenter;
import lico.example.utils.BitmapUtil;
import lico.example.view.MainView;
import okhttp3.Call;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

public class MainActivity extends ActivityPresenter<MainView>
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private OkHttpUtils mOkHttpUtils;
//    String requestUrl = "https://publicobject.com/helloworld.txt";
    String requestUrl = "http://10.50.137.70:7001/mhis-siapp/security/tokenProvide/getThirdToken.do";
    String requestUrl2 = "http://10.50.137.70:7001/mhis-siapp/setYiJiaYiAuthentication.do?data=data123";

    @Override
    protected void initData() {
        super.initData();
        getBitmap();
        mView.setNavigationItemSelected(getSupportFragmentManager(), R.id.nav_camera);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mView.setOptionsItemSelected(item.getItemId());
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return mView.setNavigationItemSelected(getSupportFragmentManager(), item.getItemId());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
//                startActivity(new Intent(this,DemoActivity.class));
//                finish();
//                initNet();
                
                initToolsNet();
                break;
            case R.id.imageView:
                Intent intent = new Intent(this, PersonalActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void initToolsNet() {
        mOkHttpUtils = OkHttpUtils.getInstance();
        OkHttpUtils.get().url(requestUrl).
                addHeader("token", "c4bf864cff6d4cbf8df4c10603c6a9d8").
//                addHeader("appid", "pingansi").
//                addHeader("appsecret","61925b2f-3fc6-4854-8c00-86926e12c4ce").
//                addHeader("token","c4bf864cff6d4cbf8df4c10603c6a9d8").
        build().
        execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {

            }

            /**
             * 表示传输层请求成功了,但是应用程序层并没没有成功
             * @param response
             */
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, "response" + response, Toast.LENGTH_SHORT).show();
                Log.e("结果", response);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mView.closeDrawer())
            return;
        super.onBackPressed();
    }

    private void getBitmap() {
        Observable.just(R.mipmap.avator)
                .map(new Func1<Integer, Bitmap>() {
                    @Override
                    public Bitmap call(Integer integer) {
                        return BitmapUtil.matrixBitmap(MainActivity.this, integer);
                    }
                })
                .map(new Func1<Bitmap, Bitmap>() {
                    @Override
                    public Bitmap call(Bitmap bitmap) {
                        return BitmapUtil.blurBitmap(MainActivity.this, bitmap, 15.5f);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Bitmap>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        mView.setAvator(bitmap);
                    }
                });
    }
}
