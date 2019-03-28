package cn.itcast.service;

import cn.itcast.pojo.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    /**
     * 查询所有用户(分页)
     * @return
     * @throws Exception
     */
    List<UserInfo> findAll(int page,int pageSize) throws Exception;

    /**
     * 保存用户
     * @param userInfo
     */
    void saveOne(UserInfo userInfo) throws Exception;

    /**
     * 查询一个用户的详情（角色，权限）
     * @param id
     * @return
     * @throws Exception
     */
    UserInfo findById(String id) throws Exception;
}
