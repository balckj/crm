package com.yidatec.security;

import com.yidatec.mapper.UserMapper;
import com.yidatec.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author QuShengWen
 */
@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String mobilePhone) throws UsernameNotFoundException {
        User user = userMapper.findByMobilePhone(mobilePhone);
        if (user == null) {
            throw new UsernameNotFoundException(mobilePhone);
        }
        if(user.getAvatar() == null || user.getAvatar().isEmpty()){
            user.setAvatar("/img/head.png");
        }
        return user;
    }
}
