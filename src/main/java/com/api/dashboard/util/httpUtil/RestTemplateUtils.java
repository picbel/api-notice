package com.api.dashboard.util.httpUtil;

import com.api.dashboard.util.exception.ApiException;
import com.api.dashboard.util.exception.model.ErrorCode;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

public class RestTemplateUtils {

    private RestTemplateUtils() {}

    public static RestTemplate restTemplateFactory(){
        return new RestTemplateBuilder()
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }

    static class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

        @Override
        public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
            return (httpResponse.getStatusCode().series() == CLIENT_ERROR || httpResponse.getStatusCode().series() == SERVER_ERROR);
        }

        @Override
        public void handleError(ClientHttpResponse httpResponse)
                throws IOException {

            if (httpResponse.getStatusCode().series() == SERVER_ERROR) {
                switch (httpResponse.getStatusCode()){
                    case SERVICE_UNAVAILABLE : throw new ApiException(ErrorCode.API_NOT_RESPONSE);
                    default: throw new ResponseStatusException(httpResponse.getStatusCode());
                }
            } else if (httpResponse.getStatusCode().series() == CLIENT_ERROR) {
                throw new ResponseStatusException(httpResponse.getStatusCode());
            }
        }
    }
}
