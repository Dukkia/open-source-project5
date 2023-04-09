/* **********************************************
 * 프로젝트명 :  WebBrowserProject
 * 작성자 : 2017015041 조정동
 * 작성일 : 2023.04.09
 *프로그램 설명 :  간단 웹브라우저
 ************************************************/

package com.example.example6_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btnGo, btnBack;
    EditText edtUrl1;
    WebView webView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true); // 홈버튼 사용 설정
        getSupportActionBar().setIcon(R.drawable.web); // 앱 아이콘 변경

        btnGo = (Button) findViewById(R.id.btnGo); // Go 버튼 설정
        btnBack = (Button) findViewById(R.id.btnBack); // 뒤로가기 버튼 설정
        edtUrl1 = (EditText) findViewById(R.id.edtUrl1); // URL 입력창 설정
        webView1 = (WebView) findViewById(R.id.webView1); // 웹뷰 설정

        webView1.setWebViewClient(new CookWebViewClient()); // 웹뷰 클라이언트 설정

        WebSettings webSet = webView1.getSettings(); // 웹뷰 설정
        webSet.setBuiltInZoomControls(true); // 줌 컨트롤 사용 설정
        webSet.setJavaScriptEnabled(true); // 자바스크립트 사용 설정

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView1.loadUrl(edtUrl1.getText().toString()); // URL 로딩
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView1.goBack(); // 뒤로가기
            }
        });
    }

    class CookWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url); // url 로딩
        }
    }
}