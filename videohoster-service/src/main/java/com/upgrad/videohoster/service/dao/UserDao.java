package com.upgrad.videohoster.service.dao;

import com.upgrad.videohoster.service.entity.UserAuthTokenEntity;
import com.upgrad.videohoster.service.entity.UserEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class UserDao {


    @PersistenceContext
    private EntityManager entityManager;

    public UserEntity createUser(UserEntity userEntity) {

        entityManager.persist(userEntity);      // adds the entity object to the persistent context
        return userEntity;
    }

    public UserEntity getUserByEmail(final String email) {
        try{
            //works on query defined in UserEntity class
            return entityManager.createNamedQuery("userByEmail" , UserEntity.class).setParameter("email" , email).getSingleResult();
        }catch (NoResultException nre){
            return null;
        }
    }

    public UserAuthTokenEntity createAuthToken(final UserAuthTokenEntity userAuthTokenEntity) {
        entityManager.persist(userAuthTokenEntity);
        return userAuthTokenEntity;
    }

    public void updateUser(final UserEntity updatedUserEntity) {
        entityManager.merge(updatedUserEntity);
    }


}
