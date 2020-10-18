package com.example.test.aws;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.Getter;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Getter
public class S3 {
    private AmazonS3 s3Client;
    private Regions clientRegion;
    private String bucketName;
    private String fileObjKeyName;
    private String fileName;
    private MultipartFile file;
    private Authentication authentication;
    private String date;
    private String datetime;

    public S3() {
        clientRegion = Regions.AP_NORTHEAST_2;
        bucketName = "mgt-web-data";
        s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(clientRegion)
                .build();
    }

    private void init(MultipartFile file)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        date = dateFormat.format(new Date());

        DateFormat datetimeFormat = new SimpleDateFormat("-yyyy-MM-dd-HH-mm-ss.");
        datetime = datetimeFormat.format(new Date());

        fileName = file.getOriginalFilename();
        this.file = file;
        authentication = SecurityContextHolder.getContext().getAuthentication();
    }

    public String OriginalUpload(MultipartFile file) {
        init(file);

        fileObjKeyName = authentication.getName()+"/original/"+date+"/"
                +FilenameUtils.getBaseName(fileName)+datetime+FilenameUtils.getExtension(fileName);

        return upload();
    }

    public String SageMakerUpload(MultipartFile file) {
        init(file);

        fileObjKeyName = authentication.getName()+"/sagemaker/"+date+"/"
                +FilenameUtils.getBaseName(fileName)+datetime+FilenameUtils.getExtension(fileName);

        return upload();
    }

    public String RekognitionUpload(MultipartFile file) {
        init(file);

        fileObjKeyName = authentication.getName()+"/rekognition/"+date+"/"
                +FilenameUtils.getBaseName(fileName)+datetime+FilenameUtils.getExtension(fileName);

        return upload();
    }

    private String upload() {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(MediaType.IMAGE_JPEG_VALUE);
        metadata.setContentLength(file.getSize());
        try {
            s3Client.putObject(bucketName, fileObjKeyName, file.getInputStream(), metadata);
        } catch (AmazonServiceException e) {
            e.printStackTrace();
            return "S3Fail";
        } catch (SdkClientException e) {
            e.printStackTrace();
            return "SDKFail";
        } catch (IOException e) {
            e.printStackTrace();
            return "FileStreamFail";
        }

        return fileObjKeyName;
    }
}
