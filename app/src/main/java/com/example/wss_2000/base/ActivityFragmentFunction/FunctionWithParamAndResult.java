package com.example.wss_2000.base.ActivityFragmentFunction;


/**
 *按照参数和返回值类型来区分接口，此处继续抽象有参有返回值类型的接口
 * @param <Result>
 * @param <Param>
 */
public abstract class FunctionWithParamAndResult<Result,Param> extends Function {
    public FunctionWithParamAndResult(String funcName) {
        super(funcName);
    }

    public abstract Result function(Param param);
}
