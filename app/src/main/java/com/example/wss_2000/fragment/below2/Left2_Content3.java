package com.example.wss_2000.fragment.below2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import com.example.wss_2000.R;
import com.example.wss_2000.base.fragment.BaseFragment;
import com.example.wss_2000.util.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @ProjectName: WSS_2000
 * @Package: com.example.wss_2000.fragment.below2
 * @ClassName: Left2_Content2
 * @Description: java类作用描述
 * @Author: wangj
 * @CreateDate: 2020/10/15 16:27
 * @Version: 1.0
 */
public class Left2_Content3 extends BaseFragment {

    private static Left2_Content3 fragment;

    @BindView(R.id.btnJLDY)
    Button btnJLDY;
    @BindView(R.id.btnCLDY)
    Button btnCLDY;
    @BindView(R.id.btnBFZJ)
    Button btnBFZJ;
    @BindView(R.id.btnJLCS)
    Button btnJLCS;
    @BindView(R.id.btnJLBSZ)
    Button btnJLBSZ;
    @BindView(R.id.btnJLBCS)
    Button btnJLBCS;
    @BindView(R.id.btnWKCS)
    Button btnWKCS;
    @BindView(R.id.btnDDCS)
    Button btnDDCS;
    @BindView(R.id.btnJLBCS_C)
    Button btnJLBCSC;
    @BindView(R.id.btnZSBCS)
    Button btnZSBCS;
    @BindView(R.id.gl_0505)
    GridLayout gl0505;


    public static Left2_Content3 newInstance() {
        Bundle args = new Bundle();
        if (fragment == null) {
            fragment = new Left2_Content3();
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.left2fragment3;
    }

    @Override
    protected void release() {

    }

    @Override
    protected View initView(View view) {
        return null;
    }

    @Override
    protected void initData() {

        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        get0505ShowButton(list);
    }


    private void get0505ShowButton(List list) {

        gl0505.removeAllViews();
        for (int i = 0; i < list.size(); i++) {
            try {
                LayoutInflater inflater = getActivity().getLayoutInflater();
                LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.ft_linelayout, null);
                Button btn = (Button) inflater.inflate(R.layout.ft_0505button, null);

                switch ((Integer) list.get(i)) {
                    case 1://常规计量单元
                        btn.setId(R.id.btnJLDY);
                        break;
                    case 2:// 测量单元
                        btn.setId(R.id.btnCLDY);
                        break;
                    case 3:// 常规泵阀组件
                        btn.setId(R.id.btnBFZJ);
                        break;
                    case 4:// 常规计量参数
                        btn.setId(R.id.btnJLCS);
                        break;
                    case 5:// 滴定计量板设置(计量单元B)
                        btn.setId(R.id.btnJLBSZ);
                        break;
                    case 6:// 滴定计量板参数
                        btn.setId(R.id.btnJLBCS);
                        break;
                    case 7:// 滴定温控参数
                        btn.setId(R.id.btnWKCS);
                        break;
                    case 8: // 滴定参数
                        btn.setId(R.id.btnDDCS);
                        break;
                    case 9: // W200 计量参数C
                        btn.setId(R.id.btnJLBCS_C);
                        break;
                    case 10: // 注射泵参数
                        btn.setId(R.id.btnZSBCS);
                        break;
                }
                layout.addView(btn);
                gl0505.addView(layout);
                GridLayout.LayoutParams layoutParams = (GridLayout.LayoutParams) layout.getLayoutParams();
                layoutParams.setMargins((int) ResourceUtil.getDimens(R.dimen.y5), (int) ResourceUtil.getDimens(R.dimen.x3), (int) ResourceUtil.getDimens(R.dimen.y5), (int) ResourceUtil.getDimens(R.dimen.x3));
                layout.setLayoutParams(layoutParams);
                btn.getLayoutParams().height = (int) ResourceUtil.getDimens(R.dimen.x105);
                btn.getLayoutParams().width = (int) ResourceUtil.getDimens(R.dimen.y190);
                btn.setOnClickListener(new btnClick());


            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private class btnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {

        }
    }
}
