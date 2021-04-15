package com.melo.notes.util;

import com.melo.notes.service.Result;
import com.melo.notes.service.constant.Status;

/**
 * @author Jun
 * @program Note
 * @description 负责返回状态.数据等信息
 * @date 2021-4-11 21:05
 */
public class ServiceUtils {

    /**
     * 返回状态
     * @param status 状态量
     * @return Result 返回结果封装类
     */
    public static Result setResult(Status status){
        Result result = new Result();
        result.setStatus(status);
        return result;
    }
}
