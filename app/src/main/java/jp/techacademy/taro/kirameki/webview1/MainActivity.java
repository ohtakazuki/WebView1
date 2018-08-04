package jp.techacademy.taro.kirameki.webview1;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // WebViewに表示する
        WebView webView = findViewById(R.id.webview);

        // ----------------------------------------
        // 実践テクニック：細かい挙動を制御する
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                // ページの読み込みを開始した時に動く関数
                Log.d("WebView", "onPageStarted");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // ページの読み込みを終了した時に動く関数
                Log.d("WebView", "onPageFinished");
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 不用意にブラウザが起動するのを止める
                return false;
            }
        });
        // JavaScriptを有効にする（重要！）
        webView.getSettings().setJavaScriptEnabled(true);
        // ----------------------------------------

        // webページを表示する
        showWebPage();

        // ボタンを押したら、URLを再表示する
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showWebPage();
            }
        });
    }

    /**
     * Webページを表示する
     */
    private void showWebPage() {
        EditText editText = findViewById(R.id.editText);
        WebView webView = findViewById(R.id.webview);
        webView.loadUrl(editText.getText().toString());
    }
}
