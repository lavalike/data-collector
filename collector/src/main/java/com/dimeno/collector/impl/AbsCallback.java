package com.dimeno.collector.impl;

import com.dimeno.collector.callback.Callback;

/**
 * default implementation of {@link Callback}
 * Created by wangzhen on 2020/4/14.
 */
public abstract class AbsCallback implements Callback {
    @Override
    public void onError(String error) {

    }
}
