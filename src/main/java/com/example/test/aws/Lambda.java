package com.example.test.aws;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.amazonaws.services.lambda.model.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

public class Lambda {

    private final Logger logger;
    AWSLambda awsLambda;

    public Lambda() {
        logger = LoggerFactory.getLogger(this.getClass());
        awsLambda = AWSLambdaClientBuilder.standard()
                .withRegion(Regions.AP_NORTHEAST_2)
                .build();
    }

    public String invokeRequest(String functionName, String jsonRequest){
        InvokeRequest invokeRequest = new InvokeRequest()
                .withFunctionName(functionName)
                .withPayload(jsonRequest);
        InvokeResult invokeResult = null;

        try {


            invokeResult = awsLambda.invoke(invokeRequest);

            String ans = new String(invokeResult.getPayload().array(), StandardCharsets.UTF_8);

            return ans;

        } catch (ServiceException e) {
            logger.error(e.toString());
        }

        System.out.println(invokeResult.getStatusCode());
        return "";
    }
}
