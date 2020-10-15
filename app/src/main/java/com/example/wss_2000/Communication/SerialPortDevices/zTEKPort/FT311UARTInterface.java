//User must modify the below package with their package name
package com.example.wss_2000.Communication.SerialPortDevices.zTEKPort;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbAccessory;
import android.hardware.usb.UsbManager;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.widget.Toast;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


/******************************FT311 GPIO interface class******************************************/
public class FT311UARTInterface extends Activity {

    private static final String ACTION_USB_PERMISSION = "com.UARTLoopback.USB_PERMISSION";
    public UsbManager usbmanager;
    public UsbAccessory usbaccessory;
    public PendingIntent mPermissionIntent;
    public ParcelFileDescriptor fileDescriptor = null;
    public FileInputStream inputStream;
    public FileOutputStream outputStream;
    public boolean mPermissionRequestPending = false;

    public boolean accessory_attached = false;
    public boolean uartEnable = false;
    public Context global_context;

    public static String ManufacturerString = "mManufacturer=FTDI";
    public static String ModelString1 = "mModel=FTDIUARTDemo";
    public static String ModelString2 = "mModel=Android Accessory FT312D";
    public static String VersionString = "mVersion=1.0";
    int baud;
    byte dataBits;
    byte stopBits;
    byte parity;
    byte flowControl;

    /*constructor*/
    public FT311UARTInterface(Context context, int baud, byte dataBits, byte stopBits, byte parity, byte flowControl) {
        super();
        global_context = context;
        /*shall we start a thread here or what*/
        /*
         **********************USB handling******************************************/
        usbmanager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
        mPermissionIntent = PendingIntent.getBroadcast(context, 0, new Intent(ACTION_USB_PERMISSION), 0);
        IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
        filter.addAction(UsbManager.ACTION_USB_ACCESSORY_DETACHED);
        context.registerReceiver(mUsbReceiver, filter);
        inputStream = null;
        outputStream = null;

        this.baud = baud;
        this.dataBits = dataBits;
        this.stopBits = stopBits;
        this.parity = parity;
        this.flowControl = flowControl;
    }


    /**
     * @param baud        波特率
     * @param dataBits    数据位
     * @param stopBits    停止位
     * @param parity      校验方式
     * @param flowControl 流控制
     */
    public void SetConfig(int baud, byte dataBits, byte stopBits, byte parity, byte flowControl) {
        byte[] writeusbdata = new byte[8];
        /*prepare the baud rate buffer*/
        writeusbdata[0] = (byte) baud;
        writeusbdata[1] = (byte) (baud >> 8);
        writeusbdata[2] = (byte) (baud >> 16);
        writeusbdata[3] = (byte) (baud >> 24);
        /*data bits*/
        writeusbdata[4] = dataBits;
        /*stop bits*/
        writeusbdata[5] = stopBits;
        /*parity*/
        writeusbdata[6] = parity;
        /*flow control*/
        writeusbdata[7] = flowControl;
        /*send the UART configuration packet*/
        SendPacket(writeusbdata, (int) 8);
    }


    /*write data*/
    public void SendData(int numBytes, byte[] buffer) {
        if (numBytes != 64) {
            SendPacket(buffer, numBytes);

        } else {
            byte temp = buffer[63];
            SendPacket(buffer, 63);
            buffer[0] = temp;
            SendPacket(buffer, 1);
        }
    }

    /*read data*/
    public int ReadData(byte[] buffer) {
        int size = 0;
        try {
            if (inputStream != null) {
                size = inputStream.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return size;
    }

    /*method to send on USB*/
    private void SendPacket(byte[] writeUsbData, int numBytes) {
        try {
            if (outputStream != null) {
                outputStream.write(writeUsbData, 0, numBytes);
            }else {
                //setEventBusMessage("MainActivity",7,0,0,"outputStream == null");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*resume accessory 在activity resume时候调用*/
    public int ResumeAccessory() {
        // Intent intent = getIntent();
        if (inputStream != null && outputStream != null) {

            return 1;
        }

        UsbAccessory[] accessories = usbmanager.getAccessoryList();
        if (accessories != null) {
            Toast.makeText(global_context, "Accessory Attached", Toast.LENGTH_SHORT).show();
        } else {
            accessory_attached = false;
            return 2;
        }

        UsbAccessory accessory = accessories[0];
        if (accessory != null) {
            if (!accessory.toString().contains(ManufacturerString)) {
                Toast.makeText(global_context, "Manufacturer is not matched!", Toast.LENGTH_SHORT).show();
                return 1;
            }

            if (!accessory.toString().contains(ModelString1) && !accessory.toString().contains(ModelString2)) {
                Toast.makeText(global_context, "Model is not matched!", Toast.LENGTH_SHORT).show();
                return 1;
            }

            if (!accessory.toString().contains(VersionString)) {
                Toast.makeText(global_context, "Version is not matched!", Toast.LENGTH_SHORT).show();
                return 1;
            }

            Toast.makeText(global_context, "Manufacturer, Model & Version are matched!", Toast.LENGTH_LONG).show();
            accessory_attached = true;

            if (usbmanager.hasPermission(accessory)) {
                OpenAccessory(accessory);
            } else {
                synchronized (mUsbReceiver) {
                    if (!mPermissionRequestPending) {
                        Toast.makeText(global_context, "Request USB Permission", Toast.LENGTH_SHORT).show();
                        usbmanager.requestPermission(accessory,
                                mPermissionIntent);
                        mPermissionRequestPending = true;
                    }
                }
            }
        }

        return 0;
    }

    /*destroy accessory 在activity destroy时候调用*/
    public void DestroyAccessory() {

        byte[] writeUsbData = new byte[1];
        SetConfig(9600, (byte) 1, (byte) 8, (byte) 0, (byte) 0);  // send default setting data for config
        try {
            Thread.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // send dummy data for instream.read going
        SendPacket(writeUsbData, 1);
        try {
            Thread.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        CloseAccessory();
    }

    /*********************helper routines*************************************************/

    public void OpenAccessory(UsbAccessory accessory) {
        fileDescriptor = usbmanager.openAccessory(accessory);
        if (fileDescriptor != null) {
            usbaccessory = accessory;
            FileDescriptor fd = fileDescriptor.getFileDescriptor();
            inputStream = new FileInputStream(fd);
            outputStream = new FileOutputStream(fd);
            /*check if any of them are null*/
            if (inputStream == null || outputStream == null) {
                return;
            }
            uartEnable = true;
            SetConfig(baud, (byte) dataBits, (byte) stopBits, (byte) 0, (byte) 0);
        }
    }

    private void CloseAccessory() {
        try {
            if (fileDescriptor != null)
                fileDescriptor.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (inputStream != null)
                inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (outputStream != null)
                outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        fileDescriptor = null;
        inputStream = null;
        outputStream = null;
        uartEnable = false;
        System.exit(0);
    }


    /***********USB broadcast receiver*******************************************/
    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ACTION_USB_PERMISSION.equals(action)) {
                synchronized (this) {
                    UsbAccessory accessory = (UsbAccessory) intent.getParcelableExtra(UsbManager.EXTRA_ACCESSORY);
                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                        Toast.makeText(global_context, "Allow USB Permission", Toast.LENGTH_SHORT).show();
                        OpenAccessory(accessory);
                    } else {
                        Toast.makeText(global_context, "Deny USB Permission", Toast.LENGTH_SHORT).show();
                        Log.d("LED", "permission denied for accessory " + accessory);
                    }
                    mPermissionRequestPending = false;
                }
            } else if (UsbManager.ACTION_USB_ACCESSORY_DETACHED.equals(action)) {
                DestroyAccessory();
                //CloseAccessory();
            } else {
                Log.d("LED", "....");
            }
        }
    };


    public boolean isUartEnable() {
        return uartEnable;
    }
}