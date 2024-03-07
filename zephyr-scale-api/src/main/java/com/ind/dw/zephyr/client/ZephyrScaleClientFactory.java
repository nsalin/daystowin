package com.ind.dw.zephyr.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

public class ZephyrScaleClientFactory {

    final private String baseUrl = "https://api.zephyrscale.smartbear.com/v2/";
    final private String token;

    private ZephyrScaleClientFactory(String token) {
        this.token = token;
    }

    public static ZephyrScaleClientFactory builder() {
        return new ZephyrScaleClientFactory(getApiToken());
    }

    private static String getApiToken() {
//        return System.getProperty("ZEPHYR_SCALE_API_TOKEN", System.getenv("ZEPHYR_SCALE_API_TOKEN"));
        return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjb250ZXh0Ijp7ImJhc2VVcmwiOiJodHRwczovL2lubm92YXRpb25kYXlzLmF0bGFzc2lhbi5uZXQiLCJ1c2VyIjp7ImFjY291bnRJZCI6IjcxMjAyMDoxNzdhMDczNC01MTIxLTQxZDktOWE3OC04NjhiMzgzNTBlY2QifX0sImlzcyI6ImNvbS5rYW5vYWgudGVzdC1tYW5hZ2VyIiwic3ViIjoiZmQyNDk1ZjMtMTRmNC0zN2FmLTg1NWMtNDdiZmRlODFhMjBjIiwiZXhwIjoxNzQxMzM1NDQ5LCJpYXQiOjE3MDk3OTk0NDl9.7KQ7UQyaSRjHlNqYrkE59D54YEJXT2KpxzY8cyLOK4g";
    }

    public ZephyrScaleClient build() {
        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        BearerAuthInterceptor authInterceptor = new BearerAuthInterceptor(this.token);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.level(HttpLoggingInterceptor.Level.NONE);

        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(authInterceptor)
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();

        ZephyrScaleAPIClient apiClient = retrofit.create(ZephyrScaleAPIClient.class);

        return new ZephyrScaleClient(apiClient);
    }

}
