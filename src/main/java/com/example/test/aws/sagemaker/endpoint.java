package com.example.test.aws.sagemaker;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Base64;

import com.amazonaws.services.sagemakerruntime.AmazonSageMakerRuntime;
import com.amazonaws.services.sagemakerruntime.AmazonSageMakerRuntimeClientBuilder;
import com.amazonaws.services.sagemakerruntime.model.InvokeEndpointRequest;
import com.amazonaws.services.sagemakerruntime.model.InvokeEndpointResult;
import org.springframework.web.multipart.MultipartFile;

public class endpoint {
    public static String predict(MultipartFile mpf) throws IOException {
        String OriginalFilename = mpf.getOriginalFilename();

        BufferedImage resizedImage = resize(mpf.getInputStream());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(resizedImage, "jpg", baos);

        ByteBuffer bodyBuffer = ByteBuffer.wrap(baos.toByteArray());

        AmazonSageMakerRuntime runtime = AmazonSageMakerRuntimeClientBuilder.defaultClient();

        InvokeEndpointRequest request = new InvokeEndpointRequest()
                .withEndpointName("object-detection-endpoint")
                .withBody(bodyBuffer);

        InvokeEndpointResult invokeEndpointResult = runtime.invokeEndpoint(request);
        String bodyResponse = new String(invokeEndpointResult.getBody().array());
        System.out.println(bodyResponse);

        return ByteArraytoBase64(baos.toByteArray(), OriginalFilename);
    }
    public static BufferedImage resize(InputStream inputStream)
            throws IOException {
        BufferedImage inputImage = ImageIO.read(inputStream);

        BufferedImage outputImage =
                new BufferedImage(300, 300, inputImage.getType());

        Graphics2D graphics2D = outputImage.createGraphics();
        graphics2D.drawImage(inputImage, 0, 0, 300, 300, null);
        graphics2D.dispose();

        return outputImage;
    }

    public static String ByteArraytoBase64(byte[] bytearray, String OriginalFilename) throws IOException {
        String fileExtName = OriginalFilename.substring(OriginalFilename.lastIndexOf(".")+1);
        return "data:image/"+ fileExtName +";base64, " + Base64.getEncoder().encodeToString(bytearray);
    }
}
