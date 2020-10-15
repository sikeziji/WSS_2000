package com.example.wss_2000.Communication.TCP;

import android.os.Environment;
import android.util.Log;


import com.example.wss_2000.MyApplication;
import com.example.wss_2000.util.FileUtils;
import com.example.wss_2000.util.PackageUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import static java.lang.Thread.sleep;

/**
 * 项目名称    BaseProject
 * 类描述
 * 创建人      hp
 * 创建时间    2019/11/22
 */
public class SocketConnect {

    private Socket socket = null;
    private String sIp;
    private int iPort;
    private InputStream is = null;
    private OutputStream os = null;
    private Thread connectThread;       // 连接线程
    private Thread connectOnceThread = null;//第一次连接到时候未连接上，进行执行尝试
    private Thread keepAliveWatchDogThread = null;// 保活线程
    private long checkDelay = 10;
    private long keepAliveDelay = 10000;//10秒
    private long lastSendTime = System.currentTimeMillis();
    private boolean isCloseAlive = false;
    private static String sAppName;
    private static String sFileRunningLogPath;


    public SocketConnect(String sIp, int iPort) {
        this.sIp = sIp;
        this.iPort = iPort;
        if (sAppName == null) {
            sAppName = PackageUtils.getAppName(MyApplication.getMyAppContext());
            sFileRunningLogPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                    + File.separator + sAppName + "/communication/";
        }

        // 连接线程
        connectThread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (!Client()) {
                    if (connectOnceThread == null) {
                        connectOnceThread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                while (!isCloseAlive) {
                                    try {
                                        if (socket != null || Client()) {
                                            connectOnceThread = null;
                                            startKeepAliveWatchDogThread();
                                            break;
                                        }
                                        try {
                                            sleep(5000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    } catch (Exception e) {
                                        Log.i("Tcp exception", e.toString());
                                    }
                                }
                            }
                        });
                        connectOnceThread.start();
                    }
                } else {
                    startKeepAliveWatchDogThread();
                }
                connectThread = null;
            }
        });
        connectThread.start();
    }


    /*
    保活线程
    检测TCP连接
    * */
    private void startKeepAliveWatchDogThread() {
        if (keepAliveWatchDogThread == null) {
            isCloseAlive = false;
            keepAliveWatchDogThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!isCloseAlive) {
                        if (System.currentTimeMillis() - lastSendTime > keepAliveDelay) {
                            if (isServerClose(socket)) {
                                try {
                                    socket = new Socket(sIp, iPort);
                                    System.out.println("正在重连....");

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            lastSendTime = System.currentTimeMillis();
                        } else {
                            try {
                                sleep(checkDelay);
                            } catch (InterruptedException e) {
                                Log.e("Tcp Keep", sIp + ":" + iPort + e.toString());
                            }
                        }
                    }
                    keepAliveWatchDogThread = null;
                }
            });
            keepAliveWatchDogThread.start();
        }
    }


    /**
     * 判断是否断开连接，断开返回true,没有返回false
     *
     * @param socket socket
     * @return 长连接报文 发送成功失败
     */
    private Boolean isServerClose(Socket socket) {
        try {
            socket.sendUrgentData(0xff);//发送1个字节的紧急数据，默认情况下，服务器端没有开启紧急数据处理，不影响正常通信
            /*os = socket.getOutputStream();
            // 步骤2：写入需要发送的数据到输出流对象中
            os.write(0xff);
            // 特别注意：数据的结尾加上换行符才可让服务器端的readline()停止阻塞
            // 步骤3：发送数据到服务端
            os.flush();
*/
            return false;
        } catch (Exception se) {
            return true;
        }
    }

    /*
     * 连接服务器
     * **/
    <T> Boolean Client() {
        boolean connect = false;
        try {
            //IP地址和端口号（对应服务端），我这的IP是本地路由器的IP地址
            socket = new Socket(sIp, iPort);
            // 判断客户端和服务器是否连接成功
            connect = socket.isConnected();

        } catch (UnknownHostException e1) {
            Log.e("Tcp Client", "Client:Socket UnknownHostException " + sIp + ":" + iPort + e1.toString());
        } catch (IOException e) {
            Log.e("Tcp Client", "Client:Socket IOException " + sIp + ":" + iPort + e.toString());
        } catch (Exception e) {
            Log.e("Tcp Client", "Client:Socket Exception " + sIp + ":" + iPort + e.toString());
        }
        return connect;
    }


    /*
    发送数据
    * */
    <T> void sendData(T data) {
        try {
            try {
                if (socket == null || data == null) {
                    Log.i("Tcp Send", "Client:Socket null " + sIp + ":" + iPort);
                    return;
                }
                if (socket.isConnected()) {
                    // 步骤1：从Socket 获得输出流对象OutputStream
                    // 该对象作用：发送数据
                    os = socket.getOutputStream();
                    // 步骤2：写入需要发送的数据到输出流对象中
                    os.write((byte[]) data);
                    // 特别注意：数据的结尾加上换行符才可让服务器端的readline()停止阻塞
                    // 步骤3：发送数据到服务端
                    os.flush();
                } else {
                    System.out.println("socket 连接错误，请重试....");
                }
            } catch (SocketException se) {
                Log.e("Tcp Send", "Client:Socket SocketException " + sIp + ":" + iPort + se.toString());
                FileUtils.saveCommunicationRunInfoToFile(sFileRunningLogPath, "Tcp Send " + "Client:Socket SocketException " + sIp + ":" + iPort + se.toString());
            } catch (Exception e) {
                Log.e("Tcp Send", "Client:Socket Exception " + sIp + ":" + iPort + e.toString());
            } finally {
                //关闭Socket
                //socket.close();
                Log.i("Tcp Send", "Client:Socket closed " + sIp + ":" + iPort);
            }
        } catch (Exception e) {
            Log.e("Tcp Send", "Client:Socket Exception " + sIp + ":" + iPort + e.toString());
        }
    }


    /*
    接收服务器数据
    * */
    <T> T Receive(T data) {
        try {
            if (socket != null) {
                is = socket.getInputStream();

                byte[] buf = new byte[4096];
                if (socket.isConnected()) {
                    int size = is.read(buf);

                    if (size > 0) {
                        try {
                            if (data instanceof byte[]) {
                                byte[] e = new byte[size];
                                System.arraycopy(buf, 0, e, 0, size);
                                data = (T) e;
                            } else {
                                data = (T) (new String(buf, 0, size, "gb2312")).toString();
                            }
                        } catch (UnsupportedEncodingException var5) {
                            Log.e("tcp Receive", var5.toString());
                        }
                        return data;
                    } else {
                        return null;
                    }
                }
            }
        } catch (Exception e) {
            Log.e("tcp Receive", e.toString());
        }
        return null;
    }

    <T> T disconnect() {
        try {
            isCloseAlive = true;
            if (socket != null) {
                if (!socket.isClosed()) {
                    if (!socket.isInputShutdown()) {
                        socket.shutdownInput();
                    }
                    if (!socket.isOutputShutdown()) {
                        socket.shutdownOutput();
                    }
                    if (is != null) {
                        is.close();
                        is = null;
                    }
                    if (os != null) {
                        os.close();
                        os = null;
                    }
                    socket.close();
                }
                socket = null;
                Log.e("tcp disconnect", "onDisconnected");
            }
        } catch (Exception e) {
            Log.e("tcp disconnect", "onError");
        }
        return null;
    }


}

