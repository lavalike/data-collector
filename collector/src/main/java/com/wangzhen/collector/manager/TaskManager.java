package com.wangzhen.collector.manager;

import com.wangzhen.collector.callback.Request;
import com.wangzhen.collector.core.DataPacker;
import com.wangzhen.collector.task.CollectorTask;
import com.wangzhen.collector.task.TaskParams;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TaskManager
 * Created by wangzhen on 2020/4/14.
 */
public final class TaskManager implements Request {
    private ExecutorService mExecutorService;
    private List<SoftReference<CollectorTask>> mCaches = new ArrayList<>();

    public static Request get() {
        return Holder.INSTANCE;
    }

    static class Holder {
        static Request INSTANCE = new TaskManager();
    }

    @Override
    public String execute(String path) {
        return DataPacker.get().pack(path);
    }

    @Override
    public void enqueue(TaskParams params) {
        CollectorTask task = new CollectorTask(params);
        mCaches.add(new SoftReference<>(task));
        getExecutor().submit(task);
    }

    private ExecutorService getExecutor() {
        if (mExecutorService == null) {
            mExecutorService = Executors.newCachedThreadPool();
        }
        return mExecutorService;
    }

}
