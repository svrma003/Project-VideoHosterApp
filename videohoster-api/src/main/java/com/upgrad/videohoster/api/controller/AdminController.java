package com.upgrad.videohoster.api.controller;


import com.upgrad.videohoster.api.model.UpdateVideoRequest;
import com.upgrad.videohoster.api.model.UpdateVideoResponse;
import com.upgrad.videohoster.api.model.VideoDetailsResponse;
import com.upgrad.videohoster.api.model.VideoUploadResponse;
import com.upgrad.videohoster.service.business.AdminService;
import com.upgrad.videohoster.service.dao.VideoDao;
import com.upgrad.videohoster.service.entity.UserAuthTokenEntity;
import com.upgrad.videohoster.service.entity.UserEntity;
import com.upgrad.videohoster.service.entity.VideoEntity;
import com.upgrad.videohoster.service.exception.UnauthorizedException;
import com.upgrad.videohoster.service.exception.UserNotSignedInException;
import com.upgrad.videohoster.service.exception.VideoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class AdminController {


    @Autowired
    private AdminService adminService;
    @Autowired
    private VideoDao videoDao;

    // takes video_id as input and displays all the video details but only with right authorisation and giving defined exception if not
    @RequestMapping(method = RequestMethod.GET, path = "/videos/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<VideoDetailsResponse> getVideo(@PathVariable("id") final String videoUuid, @RequestHeader("authorization") final String authorization) throws VideoNotFoundException, UnauthorizedException, UserNotSignedInException {

        final VideoEntity videoEntity = adminService.getVideo(videoUuid, authorization);

        VideoDetailsResponse videoDetailsResponse = new VideoDetailsResponse().video(videoEntity.getVideo()).id((int) videoEntity.getId()).name(videoEntity.getName()).description(videoEntity.getDescription()).status(videoEntity.getStatus());
        return new ResponseEntity<VideoDetailsResponse>(videoDetailsResponse, HttpStatus.OK);
    }

    //updates the attributes of video uploaded with right authorisation with right access_token and if and only if user role is admin
    @RequestMapping(method = RequestMethod.PUT, path = "/videos/update/{video_id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UpdateVideoResponse> updateVideo(final UpdateVideoRequest updateVideoRequest, @PathVariable("video_id") final long video_id, @RequestHeader("authorization") final String authorization) throws VideoNotFoundException, UnauthorizedException, UserNotSignedInException {
        VideoEntity videoEntity = new VideoEntity();
        UserAuthTokenEntity userAuthTokenEntity =videoDao.getUserAuthToken(authorization);

        videoEntity.setVideo(updateVideoRequest.getVideo());
        videoEntity.setId(video_id);
        videoEntity.setUuid(UUID.randomUUID().toString());
        videoEntity.setName(updateVideoRequest.getName());
        videoEntity.setStatus(updateVideoRequest.getStatus());
        videoEntity.setDescription(updateVideoRequest.getDescription());
        videoEntity.setUser_id(userAuthTokenEntity.getUser());
        videoEntity.setCreated_at(ZonedDateTime.now());


        VideoEntity updatedvideoEntity = adminService.updateVideo(videoEntity, authorization);
        UpdateVideoResponse updateVideoResponse = new UpdateVideoResponse().id((int) updatedvideoEntity.getId()).status(updatedvideoEntity.getStatus());
        return new ResponseEntity<UpdateVideoResponse>(updateVideoResponse,HttpStatus.CREATED);
    }

}
