package com.example.wss_2000.Communication.SerialPortDevices.zTEKPort;


import com.example.wss_2000.Communication.Communication;
import com.example.wss_2000.MyApplication;

public class ZTEKSerialPort extends Communication {

    FT311UARTInterface uartInterface;
    private boolean isSynchronous; // 同步接收
    private boolean isReceiveData; // 接收数据标志位

    public ZTEKSerialPort(int speed, byte dataBits, byte parityBit, byte stopBit, byte flowControl, boolean synchronous) {
        uartInterface = new FT311UARTInterface(MyApplication.getMyAppContext(), speed, dataBits, parityBit, stopBit, flowControl);
        uartInterface.ResumeAccessory();
        this.isSynchronous = synchronous;
    }

    @Override
    public <T> void sendData(T data) {
        setReceiveData(false);
        uartInterface.SendData(((byte[]) data).length, (byte[]) data);
    }

    @Override
    public <T> T receiveData(T data) {
        int size;
        byte[] buffer = new byte[4096];
        size = uartInterface.ReadData(buffer);
        if (size > 0) {
            try {
                if (data instanceof byte[]) {
                    byte[] e = new byte[size];
                    System.arraycopy(buffer, 0, e, 0, size);
                    data = (T) e;
                } else {
                    data = (T) (new String(buffer, 0, size, "gb2312")).toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return data;
        }
        return null;
    }

    @Override
    public void close() {
        uartInterface.DestroyAccessory();
        uartInterface = null;

    }

    @Override
    public boolean isClose() {
        return !uartInterface.isUartEnable();
    }

    @Override
    public boolean isSynchronous() {
        return isSynchronous;
    }

    @Override
    public void setSynchronous(boolean synchronous) {
        isSynchronous = synchronous;
    }

    @Override
    public boolean isReceiveData() {
        return isReceiveData;
    }

    @Override
    public void setReceiveData(boolean receiveData) {
        isReceiveData = receiveData;
    }

    @Override
    public boolean receiveParsing(String str, Communication port, byte[] rs) {
      /*
        TODO ZTEKSerialPort
        // 接收到数据
        setReceiveData(true);
        EventBusUtil.setEventBusMessage("MainActivity", 7, 0, 0, "接收3 " + rs.length + "数据：" + bytesToHexString(rs, rs.length));
        return CommunicationPortParse(str, port, rs);*/
        return false;
    }
}
