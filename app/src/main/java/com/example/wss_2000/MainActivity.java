package com.example.wss_2000;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wss_2000.base.Activity.BaseActivity;
import com.example.wss_2000.base.BaseEventMessage;
import com.example.wss_2000.base.fragment.FragmentUtils;
import com.example.wss_2000.content.MessageContent;
import com.example.wss_2000.dualprocessdaemon.DualProcessDaemon;
import com.example.wss_2000.fragment.BlowFragment;
import com.example.wss_2000.fragment.Frame;
import com.example.wss_2000.util.CrashReStart.CrashReStart;
import com.example.wss_2000.util.FullScreen;
import com.example.wss_2000.util.InputMethod;
import com.example.wss_2000.util.Permission;
import com.example.wss_2000.util.Phone;
import com.example.wss_2000.util.WindowsCtrl.WindowsCtrl;
import com.example.wss_2000.widget.FloatingActionButton;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.wss_2000.MyApplication.getMyAppContext;
import static com.example.wss_2000.content.MessageContent.MAINACTIVITY_CHANGEMAINUI;
import static com.example.wss_2000.content.MessageContent.MAINACTIVITY_CLOSEANDHIDE;
import static com.example.wss_2000.content.MessageContent.MAINACTIVITY_FLOUTBTN;

public class MainActivity extends BaseActivity {

    public Button mfb;
    @BindView(R.id.txtLoginName)
    TextView txtLoginName;

    public static String mCompName;// 点击进入界面选中的监测因子


    //    public static int winID = R.id.btnBelow3;
    public static int winID = 0;

    private boolean isLogin = false;//登录按钮，不能连点

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        activityConfig();

        //添加主界面Fragment
//        FragmentUtils.addFragment(getSupportFragmentManager(), MainFragment.newInstance(), R.id.All);

        showPlatformUpContent(null);

        //显示浮动按钮
        showFloatingBtn();


    }


    @Override
    protected void initData() {
    }

    @Override
    protected void release() {
    }

    @Override
    protected boolean isRegisteredEventBus() {
        return true;
    }


    private void activityConfig() {
        // 异常崩溃注册
        CrashReStart.init((MyApplication) getApplication(), this);
        // 进程守护
        DualProcessDaemon.init(this.getClass());
        //设置广播发送隐藏虚拟按键命令
        WindowsCtrl.setFullScreenSticky(getWindow());
        //导航栏
        WindowsCtrl.setNavigationBar(true, getApplicationContext());
        //状态栏
        WindowsCtrl.setStatusBar(true, this);
        // 控件
        ButterKnife.bind(this);
        // 6.0以上版本不能读取外部存储权限的问题
        Permission.isGrantExternalRW(this);

        // 全屏设置
        FullScreen.broadCastFullScreen(Phone.getPhoneName(), true, getApplication());
    }


    /**
     * 显示浮动按钮
     */
    private void showFloatingBtn() {
        if (mfb == null) {
            mfb = FloatingActionButton.getFloatingActionButton(getApplicationContext(), R.drawable.btn_f, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    winID = R.id.All;
                    //TODO
//                    FragmentUtils.replaceFragment(getSupportFragmentManager(), R.id.All, new BelowSysUpList(), false);
                }
            });
            FloatingActionButton.initView(getApplicationContext(), getWindowManager(), mfb, getResources().getDimension(R.dimen.y140), getResources().getDimension(R.dimen.y25), getResources().getDimension(R.dimen.y25));
            FloatingActionButton.fixAPx(getApplicationContext(), getWindow());
        }
    }

    /**
     * 根据平台显示运行状态主界面
     *
     * @param compName
     */
    public void showPlatformUpContent(String compName) {
        if (!Frame.newInstance().isAdded()) {
            FragmentUtils.addFragment(getSupportFragmentManager(), Frame.newInstance(), R.id.All);
        } else {
            FragmentUtils.replaceFragment(getSupportFragmentManager(), R.id.All, Frame.newInstance(), false);
        }

//        //在 fragment Frame 上部 增加  Below3UpList
//        if (showModuleUpContent(compName)) {
//            FragmentUtils.addFragment(getSupportFragmentManager(), new Below3UpList_5(), R.id.layout_frame_up);
//        } else {
//            switch (QueryMeasCateg(compName)) {
//                case "4":
//                    FragmentUtils.addFragment(getSupportFragmentManager(), new Below3UpList_3(), R.id.layout_frame_up);
//                    break;
//                case "3":
//                case "8":
//                    FragmentUtils.addFragment(getSupportFragmentManager(), new Below3UpList_2(), R.id.layout_frame_up);
//                    break;
//                case "11":
//                    FragmentUtils.addFragment(getSupportFragmentManager(), new Below3UpList_6(), R.id.layout_frame_up);
//                    break;
//                default:
//                    FragmentUtils.addFragment(getSupportFragmentManager(), new Below3UpList(), R.id.layout_frame_up);
//                    break;
//            }
//        }
        //在 fragment Frame 下部 增加  Main_Fragment
        FragmentUtils.addFragment(getSupportFragmentManager(), new BlowFragment(), R.id.layout_frame_below);
    }


    /**
     * 接收到分发的事件
     *
     * @param event 事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveEvent(BaseEventMessage event) {
        Message msg = event.getMsg();
        String sCode = event.getsCode();
        if (sCode.equals("MainActivity")) {
            switch (msg.what) {
                case MAINACTIVITY_FLOUTBTN: // 浮动按钮 1，移除 2显示
                    if (msg.arg1 == 1) {
                        FloatingActionButton.removeFloatingActionButton(mfb, getWindowManager());
                    } else {
                        FloatingActionButton.initView(getApplicationContext(), getWindowManager(), mfb, getResources().getDimension(R.dimen.y140), getResources().getDimension(R.dimen.y25), getResources().getDimension(R.dimen.y25));
                    }
                    WindowsCtrl.setFullScreenSticky(getWindow());
                    break;
                case MAINACTIVITY_CHANGEMAINUI:// 运行主界面切换
                    showPlatformUpContent((String) msg.obj);
                    break;
                case MAINACTIVITY_CLOSEANDHIDE:// 编辑框确认后，关闭输入法及屏幕全屏
                    InputMethod.closeInputMethod(getMyAppContext(), (View) (msg.obj));
                    WindowsCtrl.setFullScreenSticky(getWindow());
                    break;
            }
        }
    }


}