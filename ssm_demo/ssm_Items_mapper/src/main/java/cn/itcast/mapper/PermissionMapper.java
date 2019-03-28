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
}
