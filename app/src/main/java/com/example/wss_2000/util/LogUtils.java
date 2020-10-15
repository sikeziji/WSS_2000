package com.example.wss_2000.util;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 项目名称    BaseProject
 * 类描述
 * 创建人      hp
 * 创建时间    2019/12/26
 */
public class LogUtils {

    private static final String Tag = "LogUtils";
    private volatile static LogUtils fLogUtils;
    private String TOP_BORDER = "═══════════════════════════════════════════════════════════════════════════════════════════════════════════";
    private String BOTTOM_BORDER = "═══════════════════════════════════════════════════════════════════════════════════════════════════════════";
    private String logDir = "";//设置文件存储目录
    private boolean debug = true;//是否打印log
    private boolean savesd = false;//是否存log到sd卡
    private boolean savecrash = true;//是否存crash信息到sd卡
    private int CHUNK_SIZE = 120; //设置字节数
    private long logSize = 1 * 1024 * 1024L;//设置log文件大小 k
    private ExecutorService execu = Executors.newFixedThreadPool(1);

    private LogUtils() {
        initLogFile();
    }

    public static LogUtils getInstance() {
        if (fLogUtils == null) {
            synchronized (LogUtils.class) {
                if (fLogUtils == null) {
                    fLogUtils = new LogUtils();
                }
            }
        }
        return fLogUtils;
    }

    private void initLogFile() {
        logDir = FileUtils.getRootDir() + "/log";
        FileUtils.makeDir(logDir);
    }

    public void v(@NonNull Object objMsg) {
        v(Tag, objMsg);
    }

    public void d(@NonNull Object objMsg) {
        d(Tag, objMsg);
    }

    public void i(@NonNull Object objMsg) {
        i(Tag, objMsg);
    }

    public void w(@NonNull Object objMsg) {
        w(Tag, objMsg);
    }

    public void e(@NonNull Object objMsg) {
        e(Tag, objMsg);
    }

    public void v(String tag, @NonNull Object objMsg) {
        String stacks = targetStackTraceMSg();
        if (debug) {
            Log.v(tag, msgFormat(stacks, objMsg));
        }
        if (savesd) {
            saveToSd(stacks, objMsg);
        }
    }

    public void d(String tag, @NonNull Object objMsg) {
        String stacks = targetStackTraceMSg();
        if (debug) {
            Log.d(tag, msgFormat(stacks, objMsg));
        }
        if (savesd) {
            saveToSd(stacks, objMsg);
        }
    }

    public void i(String tag, @NonNull Object objMsg) {
        String stacks = targetStackTraceMSg();
        if (debug) {
            Log.i(tag, msgFormat(stacks, objMsg));
        }
        if (savesd) {
            saveToSd(stacks, objMsg);
        }
    }

    public void w(String tag, @NonNull Object objMsg) {
        String stacks = targetStackTraceMSg();
        if (debug) {
            Log.w(tag, msgFormat(stacks, objMsg));
        }
        if (savesd) {
            saveToSd(stacks, objMsg);
        }
    }


    public void e(String tag, @NonNull Object objMsg) {
        String stacks = targetStackTraceMSg();
        if (debug) {
            Log.e(tag, msgFormat(stacks, objMsg));
        }
        if (savesd) {
            saveToSd(stacks, objMsg);
        }
    }


    /**
     * 是否开启bebug模式
     *
     * @param debug
     * @return
     */
    public LogUtils debug(boolean debug) {
        this.debug = debug;
        return this;
    }


    public boolean isDebug() {
        return debug;
    }

    /**
     * 是否保存到sd卡
     *
     * @param savesd
     * @return
     */
    public LogUtils saveSD(boolean savesd) {
        this.savesd = savesd;
        return this;
    }


    /**
     * 设置log文件大小
     *
     * @param logSize
     * @return
     */
    public LogUtils setLogSize(int logSize) {
        this.logSize = logSize;
        return this;
    }

    /**
     * 设置log文件目录
     *
     * @param logDir
     * @return
     */
    public LogUtils setlogDir(String logDir) {
        if (!logDir.isEmpty()) {
            this.logDir = logDir;
        }
        return this;
    }

    public String getLogFileDir() {
        return logDir;
    }


    private String targetStackTraceMSg() {
        StackTraceElement targetStackTraceElement = getTargetStackTraceElement();
        if (targetStackTraceElement != null) {
            return "at " + targetStackTraceElement.getClassName() + "." + targetStackTraceElement.getMethodName() +
                    "(" + targetStackTraceElement.getFileName() + ":" + targetStackTraceElement.getLineNumber() + ")";

        } else {
            return "";
        }
    }

    private StackTraceElement getTargetStackTraceElement() {
        StackTraceElement targetStackTrace = null;
        boolean shouldTrace = false;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        for (StackTraceElement stackTraceElement : stackTrace) {
            boolean isLogMethod = stackTraceElement.getClassName().equals(LogUtils.class.getName());
            if (shouldTrace && !isLogMethod) {
                targetStackTrace = stackTraceElement;
                break;
            }
            shouldTrace = isLogMethod;
        }
        return targetStackTrace;
    }

    private String msgFormat(String stackstr, Object objMsg) {
        String msg = "";
        if (objMsg instanceof String) {
            msg = objMsg.toString();
        }
        byte[] bytes = new byte[0];
        try {
            bytes = msg.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int length = bytes.length;
        String newMsg = TOP_BORDER + "\n" + DateUtils.dateToString(new Date()) + "\n" + stackstr;
        if (length > CHUNK_SIZE) {
            int i = 0;
            while (i < length) {
                int count = Math.min(length - i, CHUNK_SIZE);
                String tempStr = new String(bytes, i, count);
                newMsg += "\n" + tempStr;
                i += CHUNK_SIZE;
            }
        } else {
            newMsg += "\n" + msg;
        }
        newMsg += "\n" + BOTTOM_BORDER;
        return newMsg;

    }

    private void saveToSd(final String stackstr, final Object objMsg) {
        execu.submit(new Runnable() {
            @Override
            public void run() {
                String data = DateUtils.dateToString(new Date(), "yyyy-MM-dd");
                File[] files = FileUtils.orderByDate(new File(logDir), true);
                List<File> filels = FileUtils.filter(files, data);
                String filepath;
                if (filels.size() > 0) {
                    Long length = FileUtils.getLength(filels.get(0));
                    if (length > logSize) {
                        int index = Integer.parseInt(filels.get(0).getName().replace("log_" + data + "_", "").replace(".html", ""));
                        int id = index + 1;
                        filepath = logDir + "/" + "log_" + data + "_" + id + ".html";
                        FileUtils.createFile(filepath);
                    } else {
                        filepath = filels.get(0).getAbsolutePath();
                    }
                } else {
                    filepath = logDir + "/" + "log_" + data + "_1.html";
                    FileUtils.createFile(filepath);
                }
                String msg = "";
                if (objMsg instanceof String) {
                    msg = objMsg.toString();
                }
                FileUtils.appendText(filepath, "<div class=\"dotted\">" + "\n<div class=\"exp\">\n" + DateUtils.dateToString(new Date()) + "\n</div><div>\n" + stackstr + "\n</div><div class=\"redcolor\">\n" + msg.replaceAll("\n", "<br />") + "\n</div></div>", true);
            }
        });


    }


}
