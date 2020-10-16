package com.example.wss_2000.fragment.below5;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wss_2000.MainActivity;
import com.example.wss_2000.MyApplication;
import com.example.wss_2000.R;
import com.example.wss_2000.base.fragment.BaseFragment;
import com.example.wss_2000.util.PackageUtils;
import com.example.wss_2000.util.UpdateApp.UpdateApp;
import com.example.wss_2000.util.UpdateApp.UpdateManager;

import butterknife.BindView;

/**
 * @ProjectName: WSS_2000
 * @Package: com.example.wss_2000.fragment.below5
 * @ClassName: Left5_Content1
 * @Description: java类作用描述
 * @Author: wangj
 * @CreateDate: 2020/10/16 11:48
 * @Version: 1.0
 */
public class Left5_Content1 extends BaseFragment {


    @BindView(R.id.tvHMIVer)
    TextView tvHMIVer;
    @BindView(R.id.tvJKBVer)
    TextView tvJKBVer;
    @BindView(R.id.layout_JKB)
    LinearLayout layoutJKB;
    @BindView(R.id.tvCKAVer)
    TextView tvCKAVer;
    @BindView(R.id.tvCKBVer)
    TextView tvCKBVer;
    @BindView(R.id.layout_CKB)
    LinearLayout layoutCKB;
    @BindView(R.id.tvCDJLVer)
    TextView tvCDJLVer;
    @BindView(R.id.layout_CDJLB)
    LinearLayout layoutCDJLB;
    @BindView(R.id.tvJLBVer)
    TextView tvJLBVer;
    @BindView(R.id.layout_JLB)
    LinearLayout layoutJLB;
    @BindView(R.id.tvORPVer)
    TextView tvORPVer;
    @BindView(R.id.layout_ORPDJB)
    LinearLayout layoutORPDJB;


    //组件名称
    private static String componetName = "";
    //   static int length = getListAll5721Component().get(1).length;
    static int length = 1;
    //0 测控板A 1 测控板B 2 磁导计量板 3 接口板 4  计量板 5 ORP电极板
    public static String[][] verStrArr = new String[length][6];
    public static int id = 0;
    private final String sdPath1 = "/storage/usbdisk1/zt/";
    private final String sdPath2 = "/storage/usbdisk2/zt/";
    private final String sdPath3 = "/storage/usbdisk3/zt/";
    private final String sdPath4 = "/storage/usbdisk4/zt/";

    private static Left5_Content1 fragment;

    public static Left5_Content1 newInstance() {

        Bundle args = new Bundle();
        if (fragment == null) {
            fragment = new Left5_Content1();
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.left5fragment1;
    }

    @Override
    protected void release() {

    }

    @Override
    protected View initView(View view) {
        componetName = MainActivity.mCompName;

        id = getComponentId(componetName);
        //TODO
//        if (QueryMeasCateg(componetName).equals("5") || QueryMeasCateg(componetName).equals("6")) {
//            verStrArr[id][0] = "";
//            verStrArr[id][1] = "";
//            verStrArr[id][2] = "";
//        }
        tvHMIVer.setText("Water_" + PackageUtils.getVersionName(MyApplication.getMyAppContext()));
        tvHMIVer.setOnLongClickListener(new HmiOnclickLister());
        String strClientVersion = "";
            /*if (blAuthenticationFunction) {
                strClientVersion = "_Auth";
            } else if (blChongQingFunctionFlag) {
                strClientVersion = "_Contest";
            }*/
        String strHmi = tvHMIVer.getText().toString() + strClientVersion;
        tvHMIVer.setText(strHmi);

        tvCKAVer.setText("");
        tvCKBVer.setText("");
        tvCDJLVer.setText("");
        tvJKBVer.setText("");
        tvJLBVer.setText("");
        tvORPVer.setText("");

        return null;
    }

    private int getComponentId(String compName) {

        //TODO 获取数据Component
//        for (int i = 0; i < getListAll5721Component().get(1).length; i++) {
//            if (getListAll5721Component().get(1)[i].equals(compName)) {
//                return i;
//            }
//        }
        return 0;
    }

    @Override
    protected void initData() {

    }

    private class HmiOnclickLister implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
            try {
                String sdFileName = UpdateApp.getLatestAPKName(sdPath1, PackageUtils.getAppName(MyApplication.getMyAppContext()));
                String fPath = sdPath1;
                if (sdFileName == null) {
                    sdFileName = UpdateApp.getLatestAPKName(sdPath2, PackageUtils.getAppName(MyApplication.getMyAppContext()));
                    fPath = sdPath2;
                }
                if (sdFileName == null) {
                    sdFileName = UpdateApp.getLatestAPKName(sdPath3, PackageUtils.getAppName(MyApplication.getMyAppContext()));
                    fPath = sdPath3;
                }
                if (sdFileName == null) {
                    sdFileName = UpdateApp.getLatestAPKName(sdPath4, PackageUtils.getAppName(MyApplication.getMyAppContext()));
                    fPath = sdPath4;
                }
                if (sdFileName == null) {
                    Toast.makeText(MyApplication.getMyAppContext(), getString(R.string.findNotFile) + "....", Toast.LENGTH_SHORT).show();
                } else {
                    if (!sdFileName.contains(tvHMIVer.getText().toString())) {
                        Toast.makeText(MyApplication.getMyAppContext(), getString(R.string.is_under_installation) + "....", Toast.LENGTH_SHORT).show();
                        UpdateManager mUpdateManager = new UpdateManager(MyApplication.getMyAppContext(), fPath, sdFileName);
                        mUpdateManager.checkUpdateInfo();
                    } else {
                        Toast.makeText(MyApplication.getMyAppContext(), getString(R.string.checkVersion) + "....", Toast.LENGTH_SHORT).show();
                    }
                }
            } catch (Exception e) {

            }

            return false;
        }
    }
}
