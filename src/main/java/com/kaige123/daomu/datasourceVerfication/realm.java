package com.kaige123.daomu.datasourceVerfication;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;

//自定义验证的数据源，数据可从其他形式获得。不同于配置文件
public class realm implements Realm {
    //    内存配置文件名称
    @Override
    public String getName() {
        return "shiro.ini";
    }

    //    能限制提交的类型 instanceof UsernamePasswordToken 限制为UsernamePasswordToken用户密码令牌提交
    //    true 都放过
    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    //    身份验证 可从数据库获得信息在匹配 这模拟测试写死
    /**
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     * @Other getPrincipal 用户名 getCredentials密码,char数组
     */
    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("身份验证准备");

        String username = authenticationToken.getPrincipal().toString();
        String password = new String((char[]) authenticationToken.getCredentials());

        if (username.trim() != null && username.equals("system") &&
                password.trim() != null && password.equals("123456")) {
            return new SimpleAuthenticationInfo(username, password, getName());
        }
        throw new AuthenticationException();
    }
}
