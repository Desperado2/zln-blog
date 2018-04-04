package com.buss.service.impl;

import com.base.anno.Autowired;
import com.base.anno.Service;
import com.buss.service.LoginService;
import com.buss.service.SystemService;

@Service("loginService")
public class LoginServiceImpl implements LoginService{
    @Autowired
    private SystemService systemService;
    @Override
    public boolean checkLogin(String username, String passowrd) throws Exception {
        String sql = "select password,salt from base_user";
        return false;
    }
}
