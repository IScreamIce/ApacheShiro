package com.kaige123.daomu.datasourceVerfication;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

public class packing {

    static {
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

        //        可省,省略按缺省走
        ModularRealmAuthenticator modularRealmAuthenticator = new ModularRealmAuthenticator();
        modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());    //        验证策略,验证成功一个配置即可
        ModularRealmAuthorizer modularRealmAuthorizer = new ModularRealmAuthorizer();
        modularRealmAuthorizer.setPermissionResolver(new WildcardPermissionResolver());
        defaultSecurityManager.setAuthorizer(modularRealmAuthorizer);

        //        添加自定义的shiro验证数据源,等效于说明ini配置文件
        defaultSecurityManager.setRealm(new realm());
        SecurityUtils.setSecurityManager(defaultSecurityManager);
    }

    public static void main(String[] args) {
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken =
                new UsernamePasswordToken("system", "123456");

        try {
            subject.login(usernamePasswordToken);
            System.out.println("OK");
        } catch (AuthenticationException e) {
            System.out.println("Fail");
        }
    }

}
