package com.duwei.security.token;

import com.duwei.security.vo.User;

import java.util.Optional;

public interface TokenServer  {

    /**保存Token
     *
     * @param username
     * @param password
     * @return
     */
     Token saveToken(Token token);

    /**
     * 得到Token
     * @param username
     * @param password
     * @return
     */
     Token getToken(String username, String password,String ip,String url,String contextType);

    /**
     * 从Token得到用户名
     */
     String getUserName(String token);

    /**
     * 从Token得到用户对象
     */
     User getUser(String token);
    /**
     * 认证Token
     * @param name
     * @param password
     * @return
     */
    boolean authByUsernameAndPassword(String name, String password);

    /**
     * 认证Token
     * @param token
     * @return
     */
    boolean authToken(String token);

    /**
     * 认证用户名密码
     */
    boolean authUser(String userName , String pwd);

    /**
     * 通过用户名得到Token
     */
    Optional<Token> getTokenByUserName(String userName);

}
