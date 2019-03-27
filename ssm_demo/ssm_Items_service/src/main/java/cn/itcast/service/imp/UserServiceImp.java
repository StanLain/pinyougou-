package cn.itcast.service.imp;

import cn.itcast.mapper.UserMapper;
import cn.itcast.pojo.Role;
import cn.itcast.pojo.UserInfo;
import cn.itcast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo= null;
        try {
            userInfo = userMapper.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        User user=new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()==0 ? false:true,true,true,true,getRole(userInfo.getRoles()));
        return user;
    }
    public List<SimpleGrantedAuthority> getRole(List<Role> list){
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<SimpleGrantedAuthority>();
        for (Role role : list) {
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return simpleGrantedAuthorities;
    }

    public List<UserInfo> findAll() throws Exception {
        return userMapper.findAll();
    }

    public void saveOne(UserInfo userInfo) throws Exception{
        //生成永不重复的字符串id
        String string = UUID.randomUUID().toString();
        userInfo.setId(string);
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
       userMapper.saveAll(userInfo);
    }
}
