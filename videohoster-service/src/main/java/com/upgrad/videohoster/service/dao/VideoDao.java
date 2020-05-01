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

    public VideoEntity updateVideo(final VideoEntity videoEntity) {
        return entityManager.merge(videoEntity);
    }
}
