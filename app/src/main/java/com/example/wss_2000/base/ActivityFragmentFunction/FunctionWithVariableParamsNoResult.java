package com.example.wss_2000.base.ActivityFragmentFunction;

import java.util.ArrayList;

/**
 * 按照参数和返回值类型来区分接口，此处继续抽象有可变参数无返回值类型的接口
 *
 * @param <Param>
 */
public abstract class FunctionWithVariableParamsNoResult<Param> extends Function {
    public FunctionWithVariableParamsNoResult(String pFuncName) {
        super(pFuncName);
    }

    public abstract void function(ArrayList<Object> pParam);
}
