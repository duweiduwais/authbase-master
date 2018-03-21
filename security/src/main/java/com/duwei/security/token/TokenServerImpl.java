package com.duwei.security.token;

import com.duwei.security.resource.UserEntity;
import com.duwei.security.resource.UserRepository;
import com.duwei.security.vo.ConvPoToVo;
import com.duwei.security.vo.User;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Optional;

@Service
public class TokenServerImpl implements TokenServer{


    @Autowired
    private  TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Token saveToken(Token token) {
        Preconditions.checkArgument(token != null);
        if(authToken(token.getToken())) return token;
        String username = getUserName(token.getToken());
        token.setUserName(username);
        return tokenRepository.save(token);
    }

    @Override
    public Token getToken(String username, String password, String ip, String url, String contextType) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(username) && !Strings.isNullOrEmpty(password));

        return new Token(username+password);
    }

    @Override
    public String getUserName(String token) {
        return token.substring(0,6);
    }

    @Override
    public User getUser(String token) {
        String username = getUserName(token);
        List<UserEntity> userEntities = userRepository.findAll((root, cq, cb) -> cb.equal(root.get("name").as(String.class),username));
        if(userEntities == null || userEntities.isEmpty()){
            throw new TokenException("token conv to user . user is null");
        }
        return ConvPoToVo.convUserEntityToUser(userEntities.get(0));
    }

    @Override
    public boolean authByUsernameAndPassword(String name, String password) {
        try {
            Token token = getToken(name,password,"","","");
            return authToken(token.getToken());
        } catch (Exception e) {
            return  false;
        }

    }

    @Override
    public boolean authToken(String token) {
        if(Strings.isNullOrEmpty(token)) return false;
        Optional<Token> tokenSave = tokenRepository.findOne((root, cb, cq) -> cq.equal(root.get("token").as(String.class),token));
        if(tokenSave.isPresent()) return true;
        return false;
    }

    @Override
    public boolean authUser(String userName, String pwd) {
         if(Strings.isNullOrEmpty(userName)) return false;
         if(Strings.isNullOrEmpty(pwd)) return false;
        try {
            Optional<UserEntity> userEntity = userRepository.findOne((root,cq,cb) -> {
                Predicate p1 = cb.equal(root.get("name").as(String.class),userName);
                Predicate p2 = cb.equal(root.get("password").as(String.class),pwd);
               return cb.and(p1,p2);
            });
            if(!userEntity.isPresent()) return  false;
            UserEntity user = userEntity.get();
            if(user.getName().equals(userName)) return  true;
        } catch (Exception e) {
           return false;
        }
        return  false;
    }

    @Override
    public Optional<Token> getTokenByUserName(String userName) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(userName));
        return tokenRepository.findOne((root,cq,cb) -> cb.equal(root.get("userName").as(String.class),userName));
    }
}
