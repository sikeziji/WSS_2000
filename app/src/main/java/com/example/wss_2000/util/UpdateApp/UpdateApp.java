package com.example.wss_2000.util.UpdateApp;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class UpdateApp {

    /**
     * @param filePath 文件路径
     * @param appName  文件名称
     * @return 文件信息
     */
    public static String getLatestAPKName(String filePath, String appName) {
        File[] files = null;
        File localFile = new File(filePath);

        if (localFile.isDirectory()) {
            files = localFile.listFiles();
        }
        return getLatestFileName(files, appName);
    }

    /**
     * @param files   文件信息
     * @param appName app名称
     * @return 文件名称
     */
    private static String getLatestFileName(File[] files, String appName) {
        String lastestApkName = null;
        if (files == null) {
            return null;
        }
        for (File f : files) {
            if (f.getName().contains(appName)) {
                if (lastestApkName == null) {
                    lastestApkName = f.getName();
                    continue;
                }
                /*根据时间判断找到最新的文件名*/
                //按照文件最后修改日期倒序排序
                Arrays.sort(files, new Comparator<File>() {
                    @Override
                    public int compare(File file1, File file2) {
                        return (int) (file2.lastModified() - file1.lastModified());
                    }
                });
                lastestApkName = files[0].getName();
            }
        }
        return lastestApkName;
    }

}
