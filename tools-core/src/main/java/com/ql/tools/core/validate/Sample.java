package com.ql.tools.core.validate;

/**
 * @author wanqiuli
 * @date 2022/6/30 22:27
 */
public class Sample {

    public static void main(String[] args) {
        // test对象
        User user = User.create(null, null, null, null);
        // 校验字段
        String checkFields = "age,name,date,strList";
        // 校验
        ValidationUtils.check(user, checkFields);
    }
}
