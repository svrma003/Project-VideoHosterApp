package com.upgrad.videohoster.service.dao;

import com.upgrad.videohoster.service.entity.UserAuthTokenEntity;
import com.upgrad.videohoster.service.entity.VideoEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class VideoDao {

    @PersistenceContext
    private EntityManager entityManager;

    public VideoEntity createVideo(VideoEntity videoEntity) {
        entityManager.persist(videoEntity);
        return videoEntity;
    }

    public UserAuthTokenEntity getUserAuthToken(final String accessToken) {
        try{
            return entityManager.createNamedQuery("userAuthTokenByAccessToken", UserAuthTokenEntity.class).setParameter("accessToken", accessToken).getSingleResult();
        }
        catch(NoResultException nre){
            return null;
        }
    }

    public VideoEntity getVideo(final String videoUuid) {
        try {
            //works on query defined in VideoEntity class
            return entityManager.createNamedQuery("VideoEntityByUuid", VideoEntity.class).setParameter("uuid", videoUuid).getSingleResult();
        }catch(NoResultException nre){
            return null;
        }
    }

    public VideoEntity getVideoById(final long Id) {
        try{
            //works on query defined in VideoEntity class
            return entityManager.createNamedQuery("VideoEntityByid", VideoEntity.class).setParameter("id", Id).getSingleResult();
        }catch (NoResultException nre){
            return null;
        }
    }

    public VideoEntity updateVideo(final VideoEntity videoEntity) {
        return entityManager.merge(videoEntity);
    }
}
