package com.example.test.aws;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import lombok.Getter;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

@Service
@Getter
public class S3 {
    private AmazonS3 s3Client;              // JAVA AWS S3 클라이언트
    private Regions clientRegion;           // AWS S3 리전
    private String bucketName;              // AWS S3 버킷 이름
    private String fileObjKeyName;          // AWS S3 저장될 Key Path
    private String fileName;                // 저장 파일 이름
    private MultipartFile file;             // 이미지 파일 변수
    private Authentication authentication;  // 로그인 세션 Info
    private String date;                    // DateStamp
    private String datetime;                // Date and TimeStamp

    public S3() {                                   // 기본 생성자 선언
        clientRegion = Regions.AP_NORTHEAST_2;      // Region 선언
        bucketName = "mgt-web-data";                // Bucket Name 선언
        s3Client = AmazonS3ClientBuilder.standard() // AWS S3 클라이언트 생성
                .withRegion(clientRegion)
                .build();
    }

    private void init(MultipartFile file)                                                   // 초기화 함수
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");                  // DateStamp 생성
        date = dateFormat.format(new Date());

        DateFormat datetimeFormat = new SimpleDateFormat("-yyyy-MM-dd-HH-mm-ss.");   // Date and Timestamp 생성
        datetime = datetimeFormat.format(new Date());

        fileName = file.getOriginalFilename();                                              // S3 Key Path를 생성하기 위한 File Name 저장
        this.file = file;                                                                   // File Byte Data 저장
        authentication = SecurityContextHolder.getContext().getAuthentication();            // 로그인 세션 정보 저장
    }

    public String OriginalUpload(MultipartFile file) {                                      // 원본 이미지 S3 저장 함수
        init(file);                                                                         // 초기화 함수 실행

        fileObjKeyName = authentication.getName()+"/original/"+date+"/"                     // S3 Save Key Path 생성
                +FilenameUtils.getBaseName(fileName)+datetime+FilenameUtils.getExtension(fileName);

        return upload();                                                                    // S3 Upload 함수 실행
    }

    public String getSageMakerS3Path() {

        return authentication.getName()+"/sagemaker/"+date+"/"
                +FilenameUtils.getBaseName(fileName)+datetime+FilenameUtils.getExtension(fileName);
    }

    public String getRekognitionS3Path() {

        return authentication.getName()+"/rekognition/"+date+"/"
                +FilenameUtils.getBaseName(fileName)+datetime+FilenameUtils.getExtension(fileName);
    }

    public String getByte64ImageFromS3(String s3Path) throws IOException {
        S3Object response = s3Client.getObject(new GetObjectRequest(bucketName, s3Path));
        String[] temp = s3Path.split("/");
        return ByteArraytoBase64(IOUtils.toByteArray(response.getObjectContent()), temp[temp.length-1]);
    }

    private String upload() {                                                                 // S3 업로드 함수
        ObjectMetadata metadata = new ObjectMetadata();                                       // File Metadata 변수 선언
        metadata.setContentType(MediaType.IMAGE_JPEG_VALUE);                                  // Image Type 선언
        metadata.setContentLength(file.getSize());                                            // File Size 선언
        try {
            s3Client.putObject(bucketName, fileObjKeyName, file.getInputStream(), metadata);  // S3 업로드 실행
        } catch (AmazonServiceException e) {                                                  // AWS Exception 예외
            e.printStackTrace();
            return "S3Fail";
        } catch (SdkClientException e) {                                                      // AWS SDK Exception 예외
            e.printStackTrace();
            return "SDKFail";
        } catch (IOException e) {                                                             // File IO Exception 예외
            e.printStackTrace();
            return "FileStreamFail";
        }

        return fileObjKeyName;                                                                // Return S3 Key Path
    }

    private String ByteArraytoBase64(byte[] bytearray, String OriginalFilename) throws IOException {
        String fileExtName = OriginalFilename.substring(OriginalFilename.lastIndexOf(".")+1);
        return "data:image/"+ fileExtName +";base64, " + Base64.getEncoder().encodeToString(bytearray);
    }
}
