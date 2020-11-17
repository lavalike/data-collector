package com.wangzhen.collector.core;

import com.wangzhen.collector.callback.Packer;
import com.wangzhen.collector.common.Common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * data packer
 * Created by wangzhen on 2020/4/14.
 */
public class DataPacker implements Packer {

    public static Packer get() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        static Packer INSTANCE = new DataPacker();
    }

    @Override
    public String pack(String path) {
        return internalPack(path);
    }

    private String internalPack(String path) {
        File file = new File(path);
        if (file.exists()) {
            if (file.isFile()) {
                return path;
            } else {
                if (!isEmpty(file)) {
                    try {
                        String zipFile = Common.getZipFile();
                        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile));
                        zip(file, file.getName(), zos);
                        zos.finish();
                        zos.close();
                        return zipFile;
                    } catch (Exception ignore) {

                    }
                }
            }
        }
        return null;
    }

    private boolean isEmpty(File file) {
        File[] files = file.listFiles();
        return files == null || files.length == 0;
    }

    private void zip(File file, String name, ZipOutputStream zos) throws Exception {
        if (zos == null) {
            return;
        }
        if (file.isFile()) {
            FileInputStream inputStream = new FileInputStream(file);
            zos.putNextEntry(new ZipEntry(name));
            int len;
            byte[] buffer = new byte[4096];
            while ((len = inputStream.read(buffer)) != -1) {
                zos.write(buffer, 0, len);
            }
            zos.closeEntry();
        } else {
            File[] list = file.listFiles();

            //create dir
            name += File.separator;
            zos.putNextEntry(new ZipEntry(name));
            zos.closeEntry();

            if (list != null) {
                for (File item : list) {
                    zip(item, name + item.getName(), zos);
                }
            }
        }
    }
}
