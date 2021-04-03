package com.melo.notes.service.inter;

/**
 * @author Jun
 * @program Note
 * @description 注册页面相关逻辑接口
 * @date 2021-4-3 14:30
 */
public interface RegisterService {

    /**
     * 判断输入是否有效
     * @param userName
     * @param firstPass
     * @param secondPass
     * @return String 呈现给页面的信息
     */
    String isValid(String userName,String firstPass,String secondPass);
}
