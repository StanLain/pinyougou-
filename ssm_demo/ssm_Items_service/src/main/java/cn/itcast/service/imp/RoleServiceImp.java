package cn.itcast.service.imp;

import cn.itcast.mapper.RoleMapper;
import cn.itcast.pojo.Role;
import cn.itcast.service.RoleService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleServiceImp implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    public List<Role> findAll(int page,int pageSize) throws Exception{
        PageHelper.startPage(page,pageSize);
        return roleMapper.findAll();
    }

    public Role findById(String id) throws Exception {
      Role role=roleMapper.findById(id);
        return role;
    }

    public void saveOne(Role role) throws Exception {
       role.setId(UUID.randomUUID().toString());
        roleMapper.saveOne(role);
    }
}
