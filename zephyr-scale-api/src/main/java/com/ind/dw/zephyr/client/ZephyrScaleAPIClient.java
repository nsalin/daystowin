package com.ind.dw.zephyr.client;

import com.ind.dw.zephyr.client.request.*;
import com.ind.dw.zephyr.client.response.*;
import retrofit2.Call;
import retrofit2.http.*;

interface ZephyrScaleAPIClient {
    @Headers("Content-Type: application/json")
    @POST("testcases")
    Call<CreateTestCaseResponse> createTestCase(@Body CreateTestCaseRequest body);

    @Headers("Content-Type: application/json")
    @GET("testcases")
    Call<GetTestCaseResponse> getTestCases(@Query("projectKey") String projectKey,
                                           @Query("maxResults") Integer maxResults,
                                           @Query("startAt") Integer startAt);

    @Headers("Content-Type: application/json")
    @PUT("testcases/{testCaseId}")
    Call<UpdateTestCaseResponse> updateTestCase(@Path("testCaseId") int testCaseId, @Body UpdateTestCaseRequest request);

    @Headers("Content-Type: application/json")
    @POST("folders")
    Call<FolderResponse> createFolder(@Body CreateFolderRequest body);
    @Headers("Content-Type: application/json")
    @GET("folders")
    Call<GetFoldersResponse> getFolders(@Query("projectKey") String projectKey,
                                        @Query("startAt") Integer startAt,
                                        @Query("maxResults") Integer maxResults);

    @Headers("Content-Type: application/json")
    @GET("folders/{folderId}")
    Call<FolderResponse> getFolderById(@Path("folderId") int folderId);


    @Headers("Content-Type: application/json")
    @GET("testcycles")
    Call<GetTestCyclesResponse> getTestCycles(@Query("projectKey") String projectKey,
                                              @Query("folderId") Integer folderId,
                                              @Query("maxResults") Integer maxResults,
                                              @Query("startAt") Integer startAt);

    @Headers("Content-Type: application/json")
    @GET("testcycles/{testCycleKeyOrId}")
    Call<CreateTestCaseRequest> getTestCycle(@Path("testCycleKeyOrId") String testCycleKeyOrId);

    @Headers("Content-Type: application/json")
    @POST("testcycles")
    Call<CreateTestCyclesResponse> createTestCycle(@Body CreateTestCycleRequest body);

    @Headers("Content-Type: application/json")
    @POST("testexecutions")
    Call<CreateTestExecutionRequest> createTestExecution(@Body CreateTestExecutionRequest execution);

}
