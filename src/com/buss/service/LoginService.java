package com.buss.service;

public interface LoginService {
    /**
     * 登陆验证
     * @param username  用户名
     * @param passowrd  密码
     * @return  验证结果
     * @throws Exception
     */
    boolean checkLogin(String username,String passowrd) throws Exception ;
}
