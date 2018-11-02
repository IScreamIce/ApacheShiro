package com.kaige123.daomu.iniFileVerfication;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

//使用Apache shiro验证用户身份登录
public class login {

    private static SecurityManager instance;

    static {
//        传入配置文件
        IniSecurityManagerFactory iniSecurityManagerFactory
                = new IniSecurityManagerFactory("classpath:shiro.ini");

//        工厂得到用户安全管理员实例
        instance = iniSecurityManagerFactory.getInstance();
        SecurityUtils.setSecurityManager(instance);
    }

    public static void main(String[] args) {

//        得到 用户安全管理类,也代表用户
        Subject subject = SecurityUtils.getSubject();

//        创建令牌
        UsernamePasswordToken usernamePasswordToken =
                new UsernamePasswordToken("root", "123456");

        try {
//            使用登录,进行用户安全验证
            subject.login(usernamePasswordToken);
            System.out.println("OK");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("Fail");
        }


    }

}
