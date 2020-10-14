package com.example.test.aws.sagemaker;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import com.amazonaws.services.sagemakerruntime.AmazonSageMakerRuntime;
import com.amazonaws.services.sagemakerruntime.AmazonSageMakerRuntimeClientBuilder;
import com.amazonaws.services.sagemakerruntime.model.InvokeEndpointRequest;
import com.amazonaws.services.sagemakerruntime.model.InvokeEndpointResult;

public class endpoint {

    public void predict(ByteBuffer bodyBuffer)
    {
        AmazonSageMakerRuntime runtime = AmazonSageMakerRuntimeClientBuilder.defaultClient();

//        String body = "{\"instances\": [1.0,2.0,5.0]}";
//        ByteBuffer bodyBuffer = ByteBuffer.wrap(body.getBytes());

        InvokeEndpointRequest request = new InvokeEndpointRequest()
                .withEndpointName("object-detection-endpoint")
                .withBody(bodyBuffer);

        InvokeEndpointResult invokeEndpointResult = runtime.invokeEndpoint(request);
        String bodyResponse = new String(invokeEndpointResult.getBody().array());
        System.out.println(bodyResponse);
    }

    public BufferedImage resize(InputStream inputStream)
            throws IOException {
        BufferedImage inputImage = ImageIO.read(inputStream);

        BufferedImage outputImage =
                new BufferedImage(300, 300, inputImage.getType());

        Graphics2D graphics2D = outputImage.createGraphics();
        graphics2D.drawImage(inputImage, 0, 0, 300, 300, null);
        graphics2D.dispose();


        return outputImage;
    }
}
