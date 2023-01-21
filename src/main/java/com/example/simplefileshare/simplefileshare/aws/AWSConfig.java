package com.example.simplefileshare.simplefileshare.aws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import static com.example.simplefileshare.simplefileshare.utils.Constants.AWS_ACCESS_KEY_ENV_KEY;
import static com.example.simplefileshare.simplefileshare.utils.Constants.AWS_SECRET_KEY_ENV_KEY;

@Configuration
public class AWSConfig {

    @Autowired
    Environment env;

    public AWSCredentials getAWSCredentials() {
        return new BasicAWSCredentials(
                env.getProperty(AWS_ACCESS_KEY_ENV_KEY, ""),
                env.getProperty(AWS_SECRET_KEY_ENV_KEY, "")
        );
    }

    @Bean
    public AmazonS3 getAmazonS3Bean() {
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(getAWSCredentials()))
                .withRegion(Regions.AP_SOUTH_1)
                .build();
    }
}
