package cn.itcast.service;

import cn.itcast.pojo.Role;

import java.util.List;

public interface RoleService {

    /**
     * 查询所有角色详情
     * @return
     */
    List<Role> findAll(int page,int pageSize) throws Exception;

    /**
     * 通过角色id查询角色对应的权限和拥有此角色的用户
     * @param id
     * @return
     * @throws Exception
     */
    Role findById(String id)throws Exception;

    /**
     * 新建角色
     * @param role
     * @throws Exception
     */
    void saveOne(Role role) throws Exception;
}
