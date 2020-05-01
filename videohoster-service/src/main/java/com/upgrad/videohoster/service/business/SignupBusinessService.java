package com.upgrad.videohoster.service.business;

import com.upgrad.videohoster.service.dao.UserDao;
import com.upgrad.videohoster.service.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SignupBusinessService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordCryptographyProvider passwordCryptographyProvider;

    @Transactional(propagation = Propagation.REQUIRED)
    public UserEntity signup(UserEntity userEntity) {
        String[] encryptedText = passwordCryptographyProvider.encrypt(userEntity.getPassword());        //password is stored in encrypted form in database
        userEntity.setSalt(encryptedText[0]);       //salt hashed along with the password to make the encryption of password strong. Prevents attack from using rainbow tables
        userEntity.setPassword(encryptedText[1]);
        return userDao.createUser(userEntity);

    }
}
