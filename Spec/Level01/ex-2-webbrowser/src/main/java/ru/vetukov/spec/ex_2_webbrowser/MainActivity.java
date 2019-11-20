package ru.vetukov.spec.ex_2_webbrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public static final int W = ViewGroup.LayoutParams.WRAP_CONTENT;
    public static final int M = ViewGroup.LayoutParams.MATCH_PARENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final EditText etAddress = new EditText(this);
        etAddress.setLayoutParams(new ViewGroup.LayoutParams(M, W));
        etAddress.setText("https://");

        final Button btnEnter = new Button(this);
        btnEnter.setLayoutParams(new ViewGroup.LayoutParams(M, W));
        btnEnter.setText("Press me!");

        final WebView wvBrowser = new WebView(this);
        wvBrowser.setLayoutParams(new ViewGroup.LayoutParams(M, M));
        wvBrowser.setWebViewClient(new WebViewClient());

        final LinearLayout ll = new LinearLayout(this);
        ll.setLayoutParams(new ViewGroup.LayoutParams(M, M));
        ll.setOrientation(LinearLayout.VERTICAL);

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = etAddress.getText().toString();
                try {
                    URL u = new URL(url);
                    wvBrowser.loadUrl(url);
                } catch (MalformedURLException e) {

                }
            }
        });

        ll.addView(etAddress);
        ll.addView(btnEnter);
        ll.addView(wvBrowser);

        setContentView(ll);

    }
}
