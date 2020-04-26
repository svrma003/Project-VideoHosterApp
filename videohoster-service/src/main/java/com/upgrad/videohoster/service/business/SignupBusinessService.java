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


    @Transactional(propagation = Propagation.REQUIRED)
    public UserEntity signup(UserEntity userEntity) {
        return userDao.createUser(userEntity);

    }
}
