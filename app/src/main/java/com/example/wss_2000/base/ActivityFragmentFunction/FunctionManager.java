package com.example.wss_2000.base.ActivityFragmentFunction;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class FunctionManager {
    private static FunctionManager _instance;
    private HashMap<String, FunctionNoParamNoResult> mFunctionNoParamNoResult;
    private HashMap<String, FunctionWithParamOnly> mFunctionWithParamOnly;
    private HashMap<String, FunctionWithResultOnly> mFunctionWithResultOnly;
    private HashMap<String, FunctionWithParamAndResult> mFunctionWithParamAndResult;

    private HashMap<String, FunctionWithVariableParamsNoResult> mFunctionWithVariableParamNoResult;
    private HashMap<String, FunctionWithVariableParamsAndResult> mFunctionWithVariableParamAndResult;

    private FunctionManager() {
        mFunctionNoParamNoResult = new HashMap<>();
        mFunctionWithParamOnly = new HashMap<>();
        mFunctionWithResultOnly = new HashMap<>();
        mFunctionWithParamAndResult = new HashMap<>();
        mFunctionWithVariableParamNoResult = new HashMap<>();
        mFunctionWithVariableParamAndResult = new HashMap<>();
    }

    public static FunctionManager getInstance() {
        if (_instance == null) {
            synchronized (FunctionManager.class) {
                if (_instance == null) {
                    _instance = new FunctionManager();
                }
            }
        }
        return _instance;
    }


    /**
     * 无参数无返回
     * 执行接口里的方法
     *
     * @param funcName 接口方法名
     */
    public void InvokeFunc(String funcName) {
        if (TextUtils.isEmpty(funcName)) {
            return;
        }
        if (mFunctionNoParamNoResult != null && mFunctionNoParamNoResult.size() != 0) {
            FunctionNoParamNoResult func = mFunctionNoParamNoResult.get(funcName);
            if (func != null) {
                func.function();
            } else {
                try {
                    throw new FunctionException("Has no this function:" + funcName);
                } catch (FunctionException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 有参数无返回
     *
     * @param funcName 接口方法名
     * @param param    参数
     * @param <Param>  参数类型
     */
    public <Param> void InvokeFunc(String funcName, Param param) {
        if (TextUtils.isEmpty(funcName)) {
            return;
        }
        if (mFunctionWithParamOnly != null && mFunctionWithParamOnly.size() != 0) {
            FunctionWithParamOnly func = mFunctionWithParamOnly.get(funcName);
            if (func != null) {
                func.function(param);
            } else {
                try {
                    throw new FunctionException("Has no this function:" + funcName);
                } catch (FunctionException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 无参数有返回
     *
     * @param funcName 接口方法名
     * @param c        Result对应的class类型
     * @param <Result> 返回值类型
     * @return
     */
    public <Result> Result InvokeFunc(String funcName, Class<Result> c) {
        if (TextUtils.isEmpty(funcName)) {
            return null;
        }
        if (mFunctionWithResultOnly != null && mFunctionWithResultOnly.size() != 0) {
            FunctionWithResultOnly func = mFunctionWithResultOnly.get(funcName);
            if (func != null) {
                if (c != null) {
                    return c.cast(func.function());
                } else {
                    return (Result) func.function();
                }
            } else {
                try {
                    throw new FunctionException("Has no this function:" + funcName);
                } catch (FunctionException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    /**
     * 有参数有返回
     *
     * @param funcName 接口方法名
     * @param param    参数
     * @param c        Result对应的class类型
     * @param <Result> 类型
     * @param <Param>  类型
     * @return
     */
    public <Result, Param> Result InvokeFunc(String funcName, Param param, Class<Result> c) {
        if (TextUtils.isEmpty(funcName)) {
            return null;
        }
        if (mFunctionWithParamAndResult != null && mFunctionWithParamAndResult.size() != 0) {
            FunctionWithParamAndResult func = mFunctionWithParamAndResult.get(funcName);
            if (func != null) {
                if (c != null) {
                    return c.cast(func.function(param));
                } else {
                    return (Result) func.function(param);
                }
            } else {
                try {
                    throw new FunctionException("Has no this function:" + funcName);
                } catch (FunctionException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    /**
     * 有多参数,无返回
     *
     * @param pFunName          接口方法名
     * @param pIndefiniteParams 参数列表
     * @return
     */
    @SuppressWarnings("rawtypes")
    public void InvokeFunc(String pFunName, ArrayList<Object> pIndefiniteParams) {
        try {
            if (TextUtils.isEmpty(pFunName)) {
                return;
            }
            if (mFunctionWithVariableParamNoResult != null && mFunctionWithVariableParamNoResult.size() != 0) {
                FunctionWithVariableParamsNoResult funcWithVariableParamNoResult = mFunctionWithVariableParamNoResult.get(pFunName);
                if (funcWithVariableParamNoResult != null) {
                    if (pIndefiniteParams != null && !pIndefiniteParams.isEmpty()) {
                        funcWithVariableParamNoResult.function(pIndefiniteParams);
                    } else {
                        throw new Exception("the pIndefiniteParams is empty");
                    }
                } else {
                    throw new Exception("the function is no exited: " + pFunName);
                }
            }
        } catch (Exception pE) {
            pE.printStackTrace();
        }
    }

    /**
     * 有多参数,有返回
     *
     * @param pFunName          接口方法名
     * @param pIndefiniteParams 参数列表
     * @param c                 返回值的class类型
     * @return
     */
    @SuppressWarnings("rawtypes")
    public <Result> Result InvokeFunc(String pFunName, ArrayList<Object> pIndefiniteParams, Class<Result> c) {
        try {
            if (TextUtils.isEmpty(pFunName)) {
                return null;
            }
            if (mFunctionWithVariableParamAndResult != null && mFunctionWithVariableParamAndResult.size() != 0) {
                FunctionWithVariableParamsAndResult<?> funcWithVariableParamWithResult = mFunctionWithVariableParamAndResult.get(pFunName);

                if (funcWithVariableParamWithResult != null) {
                    if (pIndefiniteParams != null && c != null) {
                        return c.cast(funcWithVariableParamWithResult.function(pIndefiniteParams));
                    } else {
                        throw new Exception("the Param or Param'class is empty");
                    }
                } else {
                    throw new Exception("the function is no exited: " + pFunName);
                }
            }
        } catch (Exception pE) {
            pE.printStackTrace();
        }
        return null;
    }

    /**
     * 设置接收事件
     *
     * @param function
     * @return
     */
    public FunctionManager addFunction(Function function) {

        if (function instanceof FunctionNoParamNoResult) {
            //把无参无返回值类型的接口缓存
            if (mFunctionNoParamNoResult != null) {
                mFunctionNoParamNoResult.put(function.mFunctionName, (FunctionNoParamNoResult) function);
            }
        } else if (function instanceof FunctionWithParamOnly) {
            //把有参无返回值类型的接口缓存
            if (mFunctionWithParamOnly != null) {
                mFunctionWithParamOnly.put(function.mFunctionName, (FunctionWithParamOnly) function);
            }
        } else if (function instanceof FunctionWithResultOnly) {
            //把无参有返回值类型的接口缓存
            if (mFunctionWithResultOnly != null) {
                mFunctionWithResultOnly.put(function.mFunctionName, (FunctionWithResultOnly) function);
            }
        } else if (function instanceof FunctionWithParamAndResult) {
            //把有参有返回值类型的接口缓存
            if (mFunctionWithParamAndResult != null) {
                mFunctionWithParamAndResult.put(function.mFunctionName, (FunctionWithParamAndResult) function);
            }
        } else if (function instanceof FunctionWithVariableParamsNoResult) {
            //把多参无返回值类型的接口缓存
            if (mFunctionWithVariableParamNoResult != null) {
                mFunctionWithVariableParamNoResult.put(function.mFunctionName, (FunctionWithVariableParamsNoResult) function);
            }
        } else if (function instanceof FunctionWithVariableParamsAndResult) {
            //把多参多返回值类型的接口缓存
            if (mFunctionWithVariableParamAndResult != null) {
                mFunctionWithVariableParamAndResult.put(function.mFunctionName, (FunctionWithVariableParamsAndResult) function);
            }
        }
        return this;
    }

    /**
     * 接收事件页面在销毁时调用
     *
     * @param funcName 方法名
     */
    public void removeFunction(String funcName) {
        if (mFunctionNoParamNoResult != null) {
            if (mFunctionNoParamNoResult.containsKey(funcName)) {
                mFunctionNoParamNoResult.remove(funcName);
            }
        } else if (mFunctionWithParamOnly != null) {
            if (mFunctionWithParamOnly.containsKey(funcName)) {
                mFunctionWithParamOnly.remove(funcName);
            }
        } else if (mFunctionWithResultOnly != null) {
            if (mFunctionWithResultOnly.containsKey(funcName)) {
                mFunctionWithResultOnly.remove(funcName);
            }
        } else if (mFunctionWithParamAndResult != null) {
            if (mFunctionWithParamAndResult.containsKey(funcName)) {
                mFunctionWithParamAndResult.remove(funcName);
            }
        } else if (mFunctionWithVariableParamNoResult != null) {
            if (mFunctionWithVariableParamNoResult.containsKey(funcName)) {
                mFunctionWithVariableParamNoResult.remove(funcName);
            }
        } else if (mFunctionWithVariableParamAndResult != null) {
            if (mFunctionWithVariableParamAndResult.containsKey(funcName)) {
                mFunctionWithVariableParamAndResult.remove(funcName);
            }
        }
    }
}
