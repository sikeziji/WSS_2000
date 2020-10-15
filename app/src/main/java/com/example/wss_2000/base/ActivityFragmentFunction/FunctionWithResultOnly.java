package com.example.wss_2000.base.ActivityFragmentFunction;

/**按照参数和返回值类型来区分接口，此处继续抽象无参有返回值类型的接口
 * @param <Result>
 */
public abstract class FunctionWithResultOnly<Result> extends Function {
    public FunctionWithResultOnly(String funcName) {
        super(funcName);
    }

    public abstract Result function();
}
