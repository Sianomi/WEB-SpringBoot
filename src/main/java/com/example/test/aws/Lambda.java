package com.example.test.aws;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.amazonaws.services.lambda.model.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

public class Lambda {                                                       // AWS Lambda 호출을 위한 Class

    private final Logger logger;                                            // Log 작성을 위한 logger
    AWSLambda awsLambda;                                                    // AWS Lambda Client

    public Lambda() {                                                       // Lambda Class 생성자
        logger = LoggerFactory.getLogger(this.getClass());                  // Logger 변수 생성
        awsLambda = AWSLambdaClientBuilder.standard()                       // AWS Lambda Client 변수 생성
                .withRegion(Regions.AP_NORTHEAST_2)
                .build();
    }

    public String invokeRequest(String functionName, String jsonRequest){   // AWS Lambda 호출을 위한 내부 함수. 호출 함수명과 event json String을 매개변수로 요구함.
        InvokeRequest invokeRequest = new InvokeRequest()                   // AWS Lambda 호출을 위한 요청변수 생성
                .withFunctionName(functionName)                             // AWS Lambda 함수명
                .withPayload(jsonRequest);                                  // event json String
        InvokeResult invokeResult = null;                                   // AWS Lambda 호출 결과 변수 생성

        try {

            invokeResult = awsLambda.invoke(invokeRequest);                 // AWS Lambda 호출

            String ans = new String(invokeResult.getPayload().array(),      // invokeResult 변수안에 저장된 AWS Labmda Return 결과를 UTF-8 String으로 추출.
                    StandardCharsets.UTF_8);

            return ans;                                                     // AWS Lambda Return 값을 Return

        } catch (ServiceException e) {
            logger.error(e.toString());                                     // 만일 에러가 발생한다면 해당 내용을 Log로 남기고 빈 String을 Return
            return "";
        }
    }
}
