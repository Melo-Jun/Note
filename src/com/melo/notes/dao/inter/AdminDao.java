package com.melo.notes.dao.inter;

import com.melo.notes.entity.Admin;

/**
 * @author Jun
 * @program Note
 * @description 管理员数据库操作接口
 * @date 2021-4-10 8:19
 */
public interface AdminDao {

    /**
     * 验证是否为管理员
     * @param admin 管理员对象
     * @return boolean 是否为管理员
     */
     boolean isAdmin(Admin admin);
}
