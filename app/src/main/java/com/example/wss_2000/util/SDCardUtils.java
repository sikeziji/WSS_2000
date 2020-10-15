package com.example.wss_2000.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称    BaseProject
 * 类描述
 * 创建人      hp
 * 创建时间    2019/11/4
 */
public class SDCardUtils {

    private SDCardUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 判断SDCard是否可用
     *
     * @return
     */
    public static boolean isSDCardEnable() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);

    }

    /**
     * 获取 SD卡 路径
     *
     * @param mContext    context
     * @param is_removale true: 外置SD卡路径
     * @return
     */
    public static List<String> getStoragePath(Context mContext, boolean is_removale) {
        List<String> rs = new ArrayList();
        @SuppressLint("WrongConstant") StorageManager mStorageManager = (StorageManager) mContext.getSystemService("storage");
        Class storageVolumeClazz = null;

        try {
            storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
            Method getVolumeList = mStorageManager.getClass().getMethod("getVolumeList");
            Method getPath = storageVolumeClazz.getMethod("getPath");
            Method isRemovable = storageVolumeClazz.getMethod("isRemovable");
            Method isEmulated = storageVolumeClazz.getMethod("isEmulated");
            Object result = getVolumeList.invoke(mStorageManager);
            int length = Array.getLength(result);

            for (int i = 0; i < length; ++i) {
                Object storageVolumeElement = Array.get(result, i);
                String path = (String) getPath.invoke(storageVolumeElement);
                boolean removable = (Boolean) isRemovable.invoke(storageVolumeElement);
                if (is_removale == removable) {
                    File sdFile = new File(path);
                    if (sdFile.canWrite()) {
                        rs.add(path);
                    }
                }
            }
        } catch (ClassNotFoundException var16) {
            var16.printStackTrace();
        } catch (InvocationTargetException var17) {
            var17.printStackTrace();
        } catch (NoSuchMethodException var18) {
            var18.printStackTrace();
        } catch (IllegalAccessException var19) {
            var19.printStackTrace();
        }

        return rs;
    }

    /**
     * 获取SD卡路径
     *
     * @return
     */
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator;
    }

    /**
     * 获取SD卡的剩余容量 单位byte
     *
     * @return
     */
    public static long getSDCardAllSize() {
        if (isSDCardEnable()) {
            StatFs stat = new StatFs(getSDCardPath());
            // 获取空闲的数据块的数量
            long availableBlocks = stat.getAvailableBlocks();
            // 获取单个数据块的大小（byte）
            long blockSize = stat.getBlockSize();
            return blockSize * availableBlocks;
        }
        return 0;
    }

    /**
     * 获取指定路径所在空间的剩余可用容量字节数，单位byte
     *
     * @param filePath
     * @return 容量字节 SDCard可用空间，内部存储可用空间
     */
    public static long getFreeBytes(String filePath) {
        // 如果是sd卡的下的路径，则获取sd卡可用容量
        if (filePath.startsWith(getSDCardPath())) {
            filePath = getSDCardPath();
        } else {// 如果是内部存储的路径，则获取内存存储的可用容量
            filePath = Environment.getDataDirectory().getAbsolutePath();
        }
        StatFs stat = new StatFs(filePath);
        long availableBlocks = (long) stat.getAvailableBlocks() - 4;
        return stat.getBlockSize() * availableBlocks;
    }


}
