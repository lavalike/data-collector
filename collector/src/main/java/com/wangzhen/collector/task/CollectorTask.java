package com.wangzhen.collector.task;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.wangzhen.collector.core.DataPacker;

/**
 * CollectorTask
 * Created by wangzhen on 2020/4/14.
 */
public class CollectorTask implements Runnable {
    private TaskParams mParams;
    private Handler handler = new Handler(Looper.getMainLooper());

    public CollectorTask(TaskParams params) {
        this.mParams = params;
    }

    @Override
    public void run() {
        if (mParams == null) return;
        if (mParams.path == null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mParams.callback != null) {
                        mParams.callback.onError("path is null");
                    }
                }
            });
        }
        final String path = DataPacker.get().pack(mParams.path);
        if (TextUtils.isEmpty(path)) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mParams.callback != null) {
                        mParams.callback.onError("zip file path is empty");
                    }
                }
            });
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mParams.callback != null) {
                        mParams.callback.onSuccess(path);
                    }
                }
            });
        }
    }

    private void runOnUiThread(Runnable runnable) {
        handler.post(runnable);
    }
}
