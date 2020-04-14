package com.dimeno.collector;

import androidx.annotation.Nullable;

import com.dimeno.collector.callback.Callback;
import com.dimeno.collector.callback.Collector;
import com.dimeno.collector.callback.Request;
import com.dimeno.collector.manager.TaskManager;
import com.dimeno.collector.task.TaskParams;

/**
 * AppCollector
 * Created by wangzhen on 2020/4/14.
 */
public final class AppCollector implements Collector {

    private static Collector sInstance = new AppCollector();
    private Request mRequest;

    private AppCollector() {
        mRequest = TaskManager.get();
    }

    public static Collector get() {
        return sInstance;
    }

    @Nullable
    @Override
    public String collect(String path) {
        return mRequest.execute(path);
    }

    @Override
    public void collect(String path, Callback callback) {
        mRequest.enqueue(new TaskParams.Builder().path(path).callback(callback).build());
    }
}
