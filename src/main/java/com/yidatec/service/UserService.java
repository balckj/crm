package com.yidatec.service;

import com.yidatec.mapper.UserMapper;
import com.yidatec.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author QuShengWen
 */
@Service("userService")
public class UserService {

    @Autowired
    private UserMapper userMapper;
    
}
