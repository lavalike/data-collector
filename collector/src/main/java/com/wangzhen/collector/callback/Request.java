package com.wangzhen.collector.callback;

import com.wangzhen.collector.task.TaskParams;

/**
 * Request
 * Created by wangzhen on 2020/4/14.
 */
public interface Request {
    String execute(String path);

    void enqueue(TaskParams params);
}
