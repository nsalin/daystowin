package com.ind.dw.zephyr.client;

import com.ind.dw.zephyr.client.request.*;
import com.ind.dw.zephyr.client.response.*;
import lombok.extern.log4j.Log4j2;
import retrofit2.Response;

import java.io.IOException;
import java.util.*;

@Log4j2
public class ZephyrScaleClient {

    private ZephyrScaleAPIClient apiClient;

    protected ZephyrScaleClient(ZephyrScaleAPIClient apiClient) {
        this.apiClient = apiClient;
    }

    public List<CreateTestCaseRequest> getTestCycles() throws IOException {
        Response<GetTestCyclesResponse> response =
                apiClient.getTestCycles(null, null, null, null).execute();
        return Objects.requireNonNull(response.body()).getValues();
    }

    public CreateTestCaseRequest createTestCycle(String projectKey, String name) {
        CreateTestCycleRequest createTestCycleRequest = CreateTestCycleRequest.builder()
                .name(name)
                .projectKey(projectKey)
                .build();
        try {
            String testCycleKey =
                    Objects.requireNonNull(apiClient.createTestCycle(createTestCycleRequest).execute().body()).getKey();
            return getTestCycleByKey(testCycleKey);
        } catch (IOException e) {
            log.throwing(e);
            return null;
        }
    }

    public CreateTestCaseRequest getTestCycleByKey(String testCycleKeyOrId) {
        try {
            return apiClient.getTestCycle(testCycleKeyOrId).execute().body();
        } catch (IOException e) {
            log.throwing(e);
            return null;
        }
    }

    public Optional<CreateTestCaseRequest> getTestCycleByProjectKeyAndName(String projectKey, String testRunName) {
        try {
            List<CreateTestCaseRequest> testCycles = new ArrayList<>();
            int startAt = 0;
            int maxResults = 50;
            boolean isLast = false;
            while (!isLast) {
                GetTestCyclesResponse response =
                        Objects.requireNonNull(apiClient.getTestCycles(projectKey, null, maxResults, startAt).execute().body());
                testCycles.addAll(Optional.ofNullable(response.getValues()).orElse(Collections.emptyList()));
                isLast = response.getIsLast();
                if (!isLast) {
                    startAt += maxResults;
                }
            }
            return Objects.requireNonNull(testCycles).stream()
                    .filter(t -> t.getKey().matches("([A-Za-z]+)-R([0-9]+)")
                            && t.getName().equalsIgnoreCase(testRunName))
                    .findFirst();
        } catch (IOException e) {
            log.throwing(e);
            return Optional.empty();
        }
    }

    public void createTestExecution(CreateTestExecutionRequest execution) {
        try {
            apiClient.createTestExecution(execution).execute();
        } catch (IOException e) {
            log.throwing(e);
        }
    }

    // New Folder methods
    public Optional<FolderResponse> createFolder(CreateFolderRequest folderRequest) {
        try {
            Response<FolderResponse> response = apiClient.createFolder(folderRequest).execute();
            if (response.isSuccessful() && response.body() != null) {
                return Optional.of(response.body());
            } else {
                log.error("Failed to create folder: " + response.message());
            }
        } catch (IOException e) {
            log.error("IOException when creating folder", e);
        }
        return Optional.empty();
    }

    public Optional<GetFoldersResponse> getFolders(String projectKey) {
        try {
            Response<GetFoldersResponse> response = apiClient.getFolders(projectKey, null, null).execute();
            if (response.isSuccessful() && response.body() != null) {
                return Optional.of(response.body());
            } else {
                log.error("Failed to get folders: " + response.message());
            }
        } catch (IOException e) {
            log.error("IOException when fetching folders", e);
        }
        return Optional.empty();
    }

    public Optional<FolderResponse> getFolderById(int folderId) {
        try {
            Response<FolderResponse> response = apiClient.getFolderById(folderId).execute();
            if (response.isSuccessful() && response.body() != null) {
                return Optional.of(response.body());
            } else {
                log.error("Failed to get folder by ID: " + response.message());
            }
        } catch (IOException e) {
            log.error("IOException when fetching folder by ID", e);
        }
        return Optional.empty();
    }

    // New Test Case methods
    public Optional<CreateTestCaseResponse> createTestCase(CreateTestCaseRequest testCaseRequest) {
        try {
            Response<CreateTestCaseResponse> response = apiClient.createTestCase(testCaseRequest).execute();
            if (response.isSuccessful() && response.body() != null) {
                return Optional.of(response.body());
            } else {
                log.error("Failed to create test case: " + response.message());
            }
        } catch (IOException e) {
            log.error("IOException when creating test case", e);
        }
        return Optional.empty();
    }

    public Optional<UpdateTestCaseResponse> updateTestCase(int testCaseId, UpdateTestCaseRequest testCaseRequest) {
        try {
            Response<UpdateTestCaseResponse> response = apiClient.updateTestCase(testCaseId, testCaseRequest).execute();
            if (response.isSuccessful() && response.body() != null) {
                return Optional.of(response.body());
            } else {
                log.error("Failed to update test case: " + response.message());
            }
        } catch (IOException e) {
            log.error("IOException when updating test case", e);
        }
        return Optional.empty();
    }

    public String findOrCreateFolder(String projectKey, String testCycleKey, String folderName) throws IOException {
        // Fetch existing folders
        Optional<GetFoldersResponse> foldersResponse = getFolders(projectKey);
        if (foldersResponse.isPresent()) {
            for (FolderResponse folder : foldersResponse.get().getValues()) {
                if (folder.getName().equalsIgnoreCase(folderName) && folder.getParentId().equals(testCycleKey)) {
                    return String.valueOf(folder.getId()); // Folder found
                }
            }
        }

        // No matching folder found, create a new one
        CreateFolderRequest folderRequest = new CreateFolderRequest(projectKey, folderName);
        Optional<FolderResponse> folderResponse = createFolder(folderRequest);
        if (folderResponse.isPresent()) {
            return String.valueOf(folderResponse.get().getId()); // New folder created
        } else {
            throw new IOException("Failed to create or find folder");
        }
    }

}

