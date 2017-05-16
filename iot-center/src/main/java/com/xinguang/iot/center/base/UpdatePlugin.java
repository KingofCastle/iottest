package com.xinguang.iot.center.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Intercepts(@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}))
public class UpdatePlugin implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        Object parameter = invocation.getArgs()[1];
        if (null != parameter) {

            // update by example
            if (parameter instanceof Map) {
                parameter = ((Map) parameter).get("record");
                if (null == parameter) {
                    return invocation.proceed();
                }
            }

            HttpServletRequest request =null;
            try {
                request = ((ServletRequestAttributes) RequestContextHolder
                        .getRequestAttributes()).getRequest();
            }catch (Exception e){
            }
            String userId = "";
            if (null != request) userId = (String) request.getAttribute("userId");
            // delFlag, updateDate, createDate
            Date current = new Date();
            if (SqlCommandType.INSERT.equals(sqlCommandType)) {
                // createBy
                try {
                    Method createDateM = parameter.getClass().getMethod("setCreateBy", String.class);
                    createDateM.invoke(parameter, userId);
                } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                }
                // updateBy
                try {
                    Method updateDateM = parameter.getClass().getMethod("setUpdateBy", String.class);
                    updateDateM.invoke(parameter, userId);
                } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                }
                // createDate
                try {
                    Method createDateM = parameter.getClass().getMethod("setCreateDate", Date.class);
                    createDateM.invoke(parameter, current);
                } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                }
                // updateDate
                try {
                    Method updateDateM = parameter.getClass().getMethod("setUpdateDate", Date.class);
                    updateDateM.invoke(parameter, current);
                } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                }
                // delFlag
                try {
                    Method getdelFlagM = parameter.getClass().getMethod("getDelFlag");
                    if (null == getdelFlagM.invoke(parameter)) {
                        Method delFlagM = parameter.getClass().getMethod("setDelFlag", String.class);
                        delFlagM.invoke(parameter, BaseEntity.DEL_FLAG_NORMAL);
                    }
                } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                }
            } else if (SqlCommandType.UPDATE.equals(sqlCommandType)) {
                // updateDate
                try {
                    Method updateDateM = parameter.getClass().getMethod("setUpdateDate", Date.class);
                    updateDateM.invoke(parameter, current);
                } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                // updateBy
                try {
                    Method updateDateM = parameter.getClass().getMethod("setUpdateBy", String.class);
                    updateDateM.invoke(parameter, userId);
                } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {
    }

}
