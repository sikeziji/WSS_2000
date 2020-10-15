package com.example.wss_2000.base.ActivityFragmentFunction;

/**
 * 按照参数和返回值类型来区分接口，此处继续抽象无参无返回值类型的接口
 */
public abstract class FunctionNoParamNoResult extends Function {
    public FunctionNoParamNoResult(String funcName) {
        super(funcName);
    }

    /**
     * 此处设计思想是：通常接口里的方法体都是空的，所以可以抽象为抽象方法,方法抽象就可以延迟其实现，从而保证了扩展性，其次再把方法看成由方法名、返回值、参数三部分构成，分别进行抽象
     */
    public abstract void function();
}
