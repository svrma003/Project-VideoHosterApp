package com.upgrad.videohoster.service.business;


import com.upgrad.videohoster.service.dao.UserDao;
import com.upgrad.videohoster.service.entity.UserAuthTokenEntity;
import com.upgrad.videohoster.service.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Service
public class AuthenticationService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordCryptographyProvider CryptographyProvider;

    @Transactional(propagation = Propagation.REQUIRED)
    public UserAuthTokenEntity authenticate(final String username, final String password) {
        UserEntity userEntity = userDao.getUserByEmail(username);

        final String encryptedPassword = CryptographyProvider.encrypt(password, userEntity.getSalt());
        UserAuthTokenEntity userAuthToken = new UserAuthTokenEntity();
        if (encryptedPassword.equals(userEntity.getPassword())) {
            JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(encryptedPassword);
            userAuthToken.setUser(userEntity);
            final ZonedDateTime now = ZonedDateTime.now();
            final ZonedDateTime expiresAt = now.plusHours(8);
            userAuthToken.setAccessToken(jwtTokenProvider.generateToken(userEntity.getUuid(), now, expiresAt));
            userAuthToken.setLoginAt(now);
            userAuthToken.setExpiresAt(expiresAt);
            userDao.createAuthToken(userAuthToken);
            userDao.updateUser(userEntity);
            userEntity.setLastLoginAt(now);
        }
        return userAuthToken;
    }
}

