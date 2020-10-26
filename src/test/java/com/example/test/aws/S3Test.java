package com.example.test.aws;

import com.example.test.TestApplication;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.*;

public class S3Test {

    @Test
    public void getByte64ImageFromS3() throws IOException {
        S3 s3 = new S3();
        System.out.println(s3.getByte64ImageFromS3("8shkim@naver.com/rekognition/2020-10-24/20200429_105534_018-2020-10-24-14-55-43.jpg"));
    }
}