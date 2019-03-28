package cn.itcast.mapper;

import cn.itcast.pojo.Permission;

import java.util.List;

public interface PermissionMapper {
    /**
     * 通过oid 查询权限详情
     * @return
     * @throws Exception
     */
    List<Permission> findByRid()throws Exception;

    /**
     * 查询所有权限
     * @return
     * @throws Exception
     */
    List<Permission> findAll() throws Exception;

    /**
     * 新建一个权限
     * @param permission
     * @throws Exception
     */
    void saveOne(Permission permission) throws Exception;

    /**
     * 通过权限id 查看拥有此权限的角色，和拥有此角色的 用户
     * @param id
     * @return
     */
    Permission findById(String id);
}
