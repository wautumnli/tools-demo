package com.ql.tools.core.validate;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.ql.tools.core.exception.WesBaseException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.*;
import java.util.function.Function;

/**
 * @author wanqiuli
 * @date 2022/6/30 20:44
 */
public class ValidationUtils {

    /**
     * function is check
     *
     * @param t           待校验对象
     * @param checkFields 校验字段
     */
    public static <T> void check(T t, String checkFields) {
        // 空校验返回
        if (StrUtil.isBlank(checkFields)) {
            return;
        }
        // 存储校验结果
        List<String> checkResult = CollectionUtil.newArrayList();
        // 校验每个字段并存储结果
        for (String field : checkFields.split(",")) {
            Object value = getFieldValue(t, field);
            if (checkEmpty(value)) {
                checkResult.add(field);
            }
        }
        // 返回异常结果
        returnResult(checkResult);
    }

    private static void returnResult(List<String> checkResult) {
        if (CollUtil.isNotEmpty(checkResult)) {
            String msg = MessageFormat.format(
                    "字段: {0}为空，请检查",
                    StrUtil.join(",", checkResult)
            );
            throw new WesBaseException(msg);
        }
    }

    @SuppressWarnings({"rawtypes"})
    private static boolean checkEmpty(Object value) {
        if (value instanceof Collection) {
            return checkValue(value, Objects::nonNull) && checkValue(value, v -> ((Collection) v).isEmpty());
        } else if (value instanceof String) {
            return checkValue(String.valueOf(value), StrUtil::isBlank);
        } else {
            return checkValue(value, Objects::isNull);
        }
    }

    private static Boolean checkValue(Object value, Function<Object, Boolean> validate) {
        return validate.apply(value);
    }

    private static Boolean checkValue(String value, Function<String, Boolean> validate) {
        return validate.apply(value);
    }

    private static <T> Object getFieldValue(T t, String field) {
        try {
            field = field.substring(0, 1).toUpperCase() + field.substring(1);
            String methodName = "get" + field;
            Method method = t.getClass().getMethod(methodName);
            return method.invoke(t);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
