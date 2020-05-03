package com.upgrad.videohoster.service.business;


import com.upgrad.videohoster.service.dao.VideoDao;
import com.upgrad.videohoster.service.entity.UserAuthTokenEntity;
import com.upgrad.videohoster.service.entity.VideoEntity;
import com.upgrad.videohoster.service.exception.UnauthorizedException;
import com.upgrad.videohoster.service.exception.UserNotSignedInException;
import com.upgrad.videohoster.service.exception.VideoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {

    @Autowired
    private VideoDao videoDao;

    public VideoEntity getVideo(final String videoUuid, final String authorization) throws VideoNotFoundException, UnauthorizedException, UserNotSignedInException {

        UserAuthTokenEntity userAuthTokenEntity = videoDao.getUserAuthToken(authorization);
        String role = userAuthTokenEntity.getUser().getRole();
        if(role != null && role.equals("admin")){       //checks the role in users table and only works if role is admin else throws exception of not authorised
            VideoEntity videoEntity = videoDao.getVideo(videoUuid);
            if(videoEntity == null){
                throw new VideoNotFoundException("VID-001" , "Video not found");
            }
            return videoEntity;     //returns if video details found and accessing user is admin
        }
        throw new UnauthorizedException("ATH-002" , "Not authorized to fetch video details");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public VideoEntity updateVideo(final VideoEntity videoEntity, final String authorization) throws VideoNotFoundException, UnauthorizedException, UserNotSignedInException {
        UserAuthTokenEntity userAuthTokenEntity = videoDao.getUserAuthToken(authorization);

        String role = userAuthTokenEntity.getUser().getRole();
        if(role != null && role.equals("admin")){
            VideoEntity videoEntity1 = videoDao.updateVideo(videoEntity);
            if(videoEntity1 == null){
                throw new VideoNotFoundException("VID-002" , "Video not found");
            }
            return videoEntity1;
        }
        throw new UnauthorizedException("ATH-003" , "Not authorized to update video");
    }
}
