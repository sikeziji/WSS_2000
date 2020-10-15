package com.example.wss_2000.base.ActivityFragmentFunction;

import java.util.ArrayList;

/**
 * 按照参数和返回值类型来区分接口，此处继续抽象有可变参数有返回值类型的接口
 *
 * @param <Result>
 */
public abstract class FunctionWithVariableParamsAndResult<Result> extends Function {
    public FunctionWithVariableParamsAndResult(String pFuncName) {
        super(pFuncName);
    }

    public abstract Result function(ArrayList<Object> pParam);
}
