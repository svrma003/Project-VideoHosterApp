package com.upgrad.videohoster.api;

import com.upgrad.videohoster.service.ServiceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(ServiceConfiguration.class)
public class VideoHosterApplication {
    public static void main(String[] x) {
        SpringApplication.run(VideoHosterApplication.class,x);      //main class to run the application
    }
}
