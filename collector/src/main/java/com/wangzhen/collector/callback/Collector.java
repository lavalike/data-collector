package com.wangzhen.collector.callback;

/**
 * collector
 * Created by wangzhen on 2020/4/14.
 */
public interface Collector {
    /**
     * collect data synchronously
     *
     * @param path path
     * @return if the path does not exist or no files under path, return null, if the path is a file path, return the file path, else return zip file path of all files under path.
     */
    String collect(String path);

    /**
     * collect data asynchronously
     *
     * @param path     path
     * @param callback callback
     */
    void collect(String path, Callback callback);
}
