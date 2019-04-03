package com.pinyougou.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pinyougou.pojo.TbSeller;
import com.pinyougou.sellergoods.service.SellerService;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	
	private SellerService sellerService;
	
	public void setSellerService(SellerService sellerService) {
		this.sellerService = sellerService;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//默认seller表中有账户就是有seller角色
		List<GrantedAuthority> grantedAuths=new ArrayList<>();
		grantedAuths.add(new SimpleGrantedAuthority("ROLE_SELLER"));
		
		
		TbSeller seller=null;
		try {
			seller = sellerService.findOne(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		if(seller!=null) {
			if(seller.getStatus().equals("1")) {
				return new User(username, seller.getPassword(), grantedAuths);
			}else {
				return null;
			}
		}else {
			return null; 
		}
		
	
		/*User user=new User(seller.getSellerId(), seller.getPassword(), seller.getStatus().equals("1")?true:false,true,true,true,getRoule(seller) );
	
		return user;*/
	}
/*	public List<SimpleGrantedAuthority> getRoule(TbSeller seller){
		List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<SimpleGrantedAuthority>();
        for (Role role : list) {
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
		 simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_SELLER"));
        return simpleGrantedAuthorities;
	}*/
}
