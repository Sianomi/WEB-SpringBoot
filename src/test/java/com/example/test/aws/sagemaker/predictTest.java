package com.example.test.aws.sagemaker;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class endpointTest {
    @Test
    public void imageResizeTest() throws IOException {
        Resource resource = new ClassPathResource("20200429_105534_006.jpg");
        endpoint test = new endpoint();
        BufferedImage resizedImage =
                test.resize(resource.getInputStream());
        ImageIO.write(resizedImage, "jpg", new File("src/main/resources/dog_2.jpg"));
    }
}