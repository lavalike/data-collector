package com.wangzhen.collector.common;

import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * Common
 * Created by wangzhen on 2020/4/14.
 */
public class Common {
    private static final String BASE_DIR = "/dimeno";
    private static final String PLUGIN_BASE_DIR = BASE_DIR + "/plugins";
    private static final String ZIP_BASE_DIR = BASE_DIR + "/zip";

    /**
     * create and return the base dir
     *
     * @return path
     */
    private static String getBaseDir() {
        File file = new File(Environment.getExternalStorageDirectory(), BASE_DIR);
        if (!file.exists()) {
            boolean ignore = file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    /**
     * create and return the plugin base dir
     *
     * @return path
     */
    private static String getPluginBaseDir() {
        File file = new File(Environment.getExternalStorageDirectory(), PLUGIN_BASE_DIR);
        if (!file.exists()) {
            boolean ignore = file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    /**
     * create and return the single plugin dir
     *
     * @param pluginName plugin name
     * @return path
     */
    public static String getPluginDir(String pluginName) {
        File file = new File(getPluginBaseDir(), pluginName);
        if (!file.exists()) {
            boolean ignore = file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    /**
     * create and return the zip base dir
     *
     * @return path
     */
    public static String getZipBaseDir() {
        File file = new File(Environment.getExternalStorageDirectory(), ZIP_BASE_DIR);
        if (!file.exists()) {
            boolean ignore = file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    /**
     * create and return a zip file path
     *
     * @return path
     */
    public static String getZipFile() {
        File file = new File(getZipBaseDir(), System.currentTimeMillis() + ".zip");
        try {
            boolean ignore = file.createNewFile();
        } catch (IOException ignore) {

        }
        return file.getAbsolutePath();
    }

}
