package com.enation.javashop.utils.logger;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 预置Log日志 序列化类
 */

public class DiskCache implements Cache {

    /**
     * 时间格式化
     */
    private SimpleDateFormat dateFormat ;

    private final long FILE_MAX_SIZE = 1024*1024*100;

    /**
     * 文件打开模式 可写可读 写完立即存盘
     */
    private final String FILEMODE = "rwd";

    /**
     * Logger本地化文件路径
     */
    private File loggerPath;

    /**
     * Logger本地文件名
     */
    private final String InfoFileName = "LoggerInfo.txt";

    private final String ErrorFileName = "LoggerError.txt";

    private final String DebugFileName = "LoggerDebug.txt";

    /**
     * LoggerLocalFile
     */
    private File infoLoggerFile;
    private File errorLoggerFile;
    private File debugLoggerFile;

    /**
     * 处理IO任务的定长线程池 节省资源占用
     */
    private ExecutorService threadPool;

    /**
     * 构造方法
     * @param context 上下文
     */
    public DiskCache(Context context) {
        this.threadPool = Executors.newFixedThreadPool(1);
        this.loggerPath = new File(context.getExternalCacheDir().getPath() + "/Log/");
        this.infoLoggerFile = new File(loggerPath, InfoFileName);
        this.errorLoggerFile = new File(loggerPath, ErrorFileName);
        this.debugLoggerFile = new File(loggerPath, DebugFileName);
        this.dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    }

    /**
     * 写入本地
     * @param content 写入本地的文本
     */
    @Override
    public void writeToLocal(@LogType.Control final int type, final String tag, final String content) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    checkFile();
                    File loggerFile = null;
                    switch (type){
                        case LogType.DEBUG:
                            loggerFile  = debugLoggerFile;
                            break;
                        case LogType.INFO:
                            loggerFile  = infoLoggerFile;
                            break;
                        case LogType.ERROR:
                            loggerFile  = errorLoggerFile;
                            break;
                    }
                    RandomAccessFile raf = new RandomAccessFile(loggerFile, FILEMODE);
                    raf.seek(loggerFile.length());
                    raf.write(mosaic(type,tag,content).getBytes());
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 拼接本地化日志
     * @param type     类型
     * @param tag      标记
     * @param content  内容
     * @return
     */
    private String mosaic(int type, String tag, String content){
       String typeString ="";

        switch (type) {
            case LogType.ERROR:
                typeString ="ERROR";
                break;
            case LogType.DEBUG:
                typeString ="DEBUG";
                break;
            case LogType.INFO:
                typeString ="INFO ";
                break;
        }

       return " Time:"+dateFormat.format(System.currentTimeMillis())+"  【"+typeString+"】  TAG:" + tag + "  Message:" + content + "             " + "\r\n";
    }

    /**
     *  清空日志缓存
     */
    @Override
    public void clearLocal() {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                errorLoggerFile.delete();
                debugLoggerFile.delete();
                infoLoggerFile.delete();
            }
        });
    }

    /**
     * 检查文件是否存在
     */
    private void checkFile() {
                try {
                    if (!loggerPath.exists()) loggerPath.mkdirs();
                    checkLogerFile(errorLoggerFile);
                    checkLogerFile(debugLoggerFile);
                    checkLogerFile(infoLoggerFile);
                } catch (IOException e) {
                    Log.e("LogUtils","本地化文件创建失败");
                }
    }

    /**
     * 检查文件是否存在 没有则创建
     * @param loggerFile   日志文件
     * @throws IOException IO异常
     */
    private void checkLogerFile(File loggerFile) throws IOException {
        if (!loggerFile.exists()) {
            loggerFile.createNewFile();
        }else if (loggerFile.length()>FILE_MAX_SIZE){
            loggerFile.delete();
            loggerFile.createNewFile();
        }
    }

}
