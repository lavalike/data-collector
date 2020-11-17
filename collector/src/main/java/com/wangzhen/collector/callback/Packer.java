package com.wangzhen.collector.callback;

/**
 * packer
 * Created by wangzhen on 2020/4/14.
 */
public interface Packer {
    /**
     * pack all file under the path
     *
     * @param path path
     * @return packed file path
     */
    String pack(String path);
}
