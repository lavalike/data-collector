package com.dimeno.collector.sample;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.dimeno.collector.AppCollector;
import com.dimeno.collector.common.Common;
import com.dimeno.collector.impl.AbsCallback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int CODE = 0x00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_collect_sync).setOnClickListener(this);
        findViewById(R.id.btn_collect_async).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, CODE);
        } else {
            switch (view.getId()) {
                case R.id.btn_collect_sync:
                    collectSync();
                    break;
                case R.id.btn_collect_async:
                    collectAsync();
                    break;
            }
        }
    }

    private void collectSync() {
        long start = System.currentTimeMillis();
        String path = AppCollector.get().collect(Common.getPluginDir("plugin01"));
        Toast.makeText(MainActivity.this, "同步压缩耗时 -> " + (System.currentTimeMillis() - start) + "\n压缩路径：" + path, Toast.LENGTH_SHORT).show();
    }

    private void collectAsync() {
        final long start = System.currentTimeMillis();
        AppCollector.get().collect(Common.getPluginDir("plugin01"), new AbsCallback() {
            @Override
            public void onSuccess(String path) {
                Toast.makeText(MainActivity.this, "异步压缩耗时 -> " + (System.currentTimeMillis() - start) + "\n压缩路径：" + path, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "存储权限获取成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "需要存储权限", Toast.LENGTH_SHORT).show();
        }
    }
}
