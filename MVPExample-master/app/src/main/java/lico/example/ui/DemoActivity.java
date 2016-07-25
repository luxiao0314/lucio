package lico.example.ui;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import java.util.HashMap;
import java.util.Map;

import lico.example.R;

/**
 * Created by luthor on 16/4/27.
 */
public class DemoActivity extends Activity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_activity);
        initView();
    }

    String url = "http://jumpbox.medtap.cn:8888/pingansi/refer";

    private void initView() {
        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);

        url = "http://jumpbox.medtap.cn:8888/pingansi/refer";
        Map<String, String> headers = new HashMap<>();
        headers.put("access_appid", "pingansi");
        headers.put("access_timestamp", "1461737983125");
        headers.put("access_siganature", "a214402eb37bbc80e27a8afa880276435d49d146");
        mWebView.loadUrl(url, headers);

//        mWebView.setWebViewClient(new WebViewClient() {
//            //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//
//        });

    }
}
