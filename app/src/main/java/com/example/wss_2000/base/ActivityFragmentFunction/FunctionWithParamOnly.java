package com.example.wss_2000.base.ActivityFragmentFunction;

/**按照参数和返回值类型来区分接口，此处继续抽象有参无返回值类型的接口
 * @param <Param>
 */
public abstract class FunctionWithParamOnly<Param> extends Function {
    public FunctionWithParamOnly(String funcName) {
        super(funcName);
    }

    public abstract void function(Param param);
}
