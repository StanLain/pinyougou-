package cn.itcast.service;

import cn.itcast.pojo.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    /**
     * 查询所有用户
     * @return
     * @throws Exception
     */
    List<UserInfo> findAll() throws Exception;

    /**
     * 保存用户
     * @param userInfo
     */
    void saveOne(UserInfo userInfo) throws Exception;
}
