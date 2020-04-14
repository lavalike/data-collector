package com.dimeno.collector.callback;

/**
 * Callback
 * Created by wangzhen on 2020/4/14.
 */
public interface Callback {
    /**
     * collect success
     *
     * @param path collected file path
     */
    void onSuccess(String path);

    /**
     * collect fail
     *
     * @param error error
     */
    void onError(String error);
}
