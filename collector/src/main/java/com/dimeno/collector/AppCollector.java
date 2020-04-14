package com.dimeno.collector;

import androidx.annotation.Nullable;

import com.dimeno.collector.callback.Callback;
import com.dimeno.collector.callback.Collector;
import com.dimeno.collector.core.DataPacker;

/**
 * AppCollector
 * Created by wangzhen on 2020/4/14.
 */
public final class AppCollector implements Collector {

    private static Collector sInstance;

    public static Collector get() {
        if (sInstance == null) {
            sInstance = new AppCollector();
        }
        return sInstance;
    }

    @Nullable
    @Override
    public String collect(String path) {
        return DataPacker.get().pack(path);
    }

    @Override
    public void collect(String path, Callback callback) {
        
    }
}
