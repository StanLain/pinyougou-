package cn.itcast.mapper;

import cn.itcast.pojo.Role;

import java.util.List;

public interface RoleMapper {

    /**
     * 通过用户id查询用户角色
     * @param uid
     * @return
     */
    List<Role> findByUid(String uid);

    /**
     * 查询所有用户详情
     * @return
     * @throws Exception
     */
    List<Role> findAll() throws Exception;

    /**
     * 通过角色id 查询角色权限和拥有此角色的用户
     * @param id
     * @return
     */
    Role findById(String id);

    /**
     * 新建角色
     * @param role
     * @throws Exception
     */
    void saveOne(Role role) throws Exception;
}
