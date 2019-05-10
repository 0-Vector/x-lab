package com.penny.xlab.groovy;

import groovy.lang.GroovyObject;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.ScriptException;
import java.io.IOException;

/**
 * @author 0-Vector
 * @date 2019/5/9 11:26
 */
public class GroovyCommonUtil {

    private static final Logger log = LoggerFactory.getLogger(GroovyCommonUtil.class);
    //该变量用于指明groovy脚本所在的父目录
    static String root[] = new String[]{"bin/groovy/"};
    static GroovyScriptEngine groovyScriptEngine;

    static {
        try {
            groovyScriptEngine = new GroovyScriptEngine(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用于调用指定Groovy脚本中的指定方法
     *
     * @param scriptName 脚本名称
     * @param methodName 方法名称
     * @param params     方法参数
     * @return
     */
    @SuppressWarnings({"rawtypes"})
    public Object invokeMethod(String scriptName, String methodName, Object... params) throws Exception {
        Object ret = null;
        Class scriptClass = null;
        GroovyObject scriptInstance = null;

        try {
            scriptClass = groovyScriptEngine.loadScriptByName(scriptName);
            scriptInstance = (GroovyObject) scriptClass.newInstance();
        } catch (ResourceException | InstantiationException | IllegalAccessException e1) {
            log.warn("加载脚本[" + scriptName + "]出现异常", e1);
            throw new Exception("加载脚本" + scriptName + "失败");
        }

        try {
            ret = (String) scriptInstance.invokeMethod(methodName, params);
        } catch (IllegalArgumentException e) {
            log.warn("执行方法" + methodName + "参数出现异常, 参数为" + params, e);
            throw new Exception("调用方法[" + methodName + "]失败，因参数不合法");
        } catch (Exception e) {
            log.warn("执行方法" + methodName + "出现异常", e);
            throw new Exception("调用方法[" + methodName + "]失败");
        }

        return ret;
    }
}
