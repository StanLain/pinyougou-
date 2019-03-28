package cn.itcast.mapper;

import cn.itcast.pojo.UserInfo;

import java.util.List;

public interface UserMapper {
    /**
     * 根据用户名查找用户和用户的角色与权限
     * @param username
     * @return
     */
    UserInfo findByUsername(String username) throws  Exception;

    /**
     * 查询所有用户
     * @return
     */
    List<UserInfo> findAll() throws Exception;

    /**
     * 保存用户
     * @param userInfo
     * @throws Exception
     */
    void saveAll(UserInfo userInfo) throws Exception;

    /**
     * 查询用户详情（角色，权限）
     * @param id
     * @return
     * @throws Exception
     */
    UserInfo findById(String id) throws Exception;
}
