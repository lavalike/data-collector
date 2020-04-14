package com.dimeno.collector.task;

import com.dimeno.collector.callback.Callback;

/**
 * TaskParams
 * Created by wangzhen on 2020/4/14.
 */
public class TaskParams {
    public String path;
    public Callback callback;

    private TaskParams(Builder builder) {
        this.path = builder.path;
        this.callback = builder.callback;
    }

    public static class Builder {

        private String path;
        private Callback callback;

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Builder callback(Callback callback) {
            this.callback = callback;
            return this;
        }

        public TaskParams build() {
            return new TaskParams(this);
        }
    }
}
