package cn.itcast.mapper;

import cn.itcast.pojo.Role;

import java.util.List;

public interface RoleMapper {

    List<Role> findByUid(String uid);
}
