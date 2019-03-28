package cn.itcast.service;

import cn.itcast.pojo.Permission;

import java.util.List;

public interface PermissionService {

    /**
     * 分页查询所有权限
     * @return
     * @throws Exception
     */
    List<Permission> findAll(int page,int pageSize) throws Exception;

    /**
     * 新建一个权限
     * @param permission
     */
    void saveOne(Permission permission) throws Exception;

    /**
     * 通过权限id 查看 拥有此权限的角色，和拥有此角色的用户
     * @param id
     * @return
     */
    Permission findById(String id);
}
