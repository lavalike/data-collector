package com.dimeno.collector.callback;

import com.dimeno.collector.task.TaskParams;

/**
 * Request
 * Created by wangzhen on 2020/4/14.
 */
public interface Request {
    String execute(String path);

    void enqueue(TaskParams params);
}
