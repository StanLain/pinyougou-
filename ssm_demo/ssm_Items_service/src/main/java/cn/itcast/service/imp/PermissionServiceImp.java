package cn.itcast.service.imp;

import cn.itcast.mapper.PermissionMapper;
import cn.itcast.pojo.Permission;
import cn.itcast.service.PermissionService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PermissionServiceImp implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    public List<Permission> findAll(int page,int pageSize) throws Exception {
        PageHelper.startPage(page,pageSize);
        return permissionMapper.findAll();
    }

    public void saveOne(Permission permission)throws Exception {
        permission.setId(UUID.randomUUID().toString());
        permissionMapper.saveOne(permission);
    }

    public Permission findById(String id) {
        return permissionMapper.findById(id);
    }
}
