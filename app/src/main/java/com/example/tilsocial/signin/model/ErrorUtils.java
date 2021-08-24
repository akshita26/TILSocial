package com.example.tilsocial.signin.model;

import com.example.tilsocial.signin.data.SigninAPIClient;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class ErrorUtils {
        public static ErrorResponse parseError (Response<?> response){

            Converter<ResponseBody, ErrorResponse> converter = SigninAPIClient.getClient().responseBodyConverter(ErrorResponse.class , new Annotation[0]);
            ErrorResponse errorResponse;
            try{
                errorResponse = converter.convert(response.errorBody());
            }catch (IOException e){
                return new ErrorResponse();
            }
            return errorResponse;
        }

}
