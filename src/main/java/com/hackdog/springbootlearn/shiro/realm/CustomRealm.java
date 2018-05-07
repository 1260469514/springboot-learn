package com.hackdog.springbootlearn.shiro.realm;

import com.hackdog.springbootlearn.pojo.User;
import com.hackdog.springbootlearn.shiro.service.PermService;
import com.hackdog.springbootlearn.shiro.service.RoleService;
import com.hackdog.springbootlearn.shiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class CustomRealm extends AuthorizingRealm {

    private final static Logger logger = LoggerFactory.getLogger(CustomRealm.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermService permService;

    {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
        matcher.setStoredCredentialsHexEncoded(false);
        matcher.setHashIterations(1024);
        this.setCredentialsMatcher(matcher);
    }

    /**
     * 用户授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //null usernames are invalid
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }

        User user = (User) getAvailablePrincipal(principals);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        logger.info("获取角色信息:{}", user.getRoles());
        logger.info("获取权限信息:{}", user.getPerms());
        info.setRoles(user.getRoles());
        info.setStringPermissions(user.getPerms());
        return info;
    }

    /**
     * 用户认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        if (username == null) {
            logger.error("用户名称不允许为空！");
            throw new AuthenticationException("用户名称不允许为空！");
        }
        String pwd = String.valueOf(upToken.getPassword());
        User user = userService.findUserByName(username, pwd);
        if (user == null) {
            logger.error("没有找到账户[{}].", username);
            throw new UnknownAccountException("没有找到账户[" + username + "].");
        }
        Set<String> roles = roleService.getRolesByUserId(user.getUid());
        Set<String> perms = permService.getPermsByUserId(user.getUid());
        user.getRoles().addAll(roles);
        user.getPerms().addAll(perms);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPwd(), getName());
        if (user.getSalt() != null) {
            info.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
        }
        return info;
    }
}
