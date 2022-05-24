package com.thc.clipboard;

import android.content.ClipboardManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String RECEIVER_KEY = "receiver_key";
    private static final String LOG_KEY = "Clipboard";
    private ClipboardManager mClipboardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mClipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        android2Mac();
        mac2android();
    }

    private void mac2android() {
        EditText mEditResult = findViewById(R.id.edt_result);
        String stringExtra = getIntent().getStringExtra(RECEIVER_KEY);
        if (!TextUtils.isEmpty(stringExtra)) {
            Log.d(LOG_KEY, stringExtra);
            mClipboardManager.setText(stringExtra);
            mEditResult.setText(stringExtra);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getClipBoardContent();
            }
        }, 500);
    }

    private void getClipBoardContent() {
        //监听剪切板
        if (null != mClipboardManager) {
            CharSequence text = mClipboardManager.getText();
            Log.d(LOG_KEY, getSharedPreferences("ndkey", MODE_PRIVATE).getString("pwd", "") + text.toString());
        }
    }

    private void android2Mac() {
        EditText edtPwd = findViewById(R.id.edt_pwd);
        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSharedPreferences("ndkey", MODE_PRIVATE).edit().putString("pwd", edtPwd.getText().toString()).apply();
                Toast.makeText(MainActivity.this, "pwd saved", Toast.LENGTH_SHORT).show();
            }
        });
    }
}