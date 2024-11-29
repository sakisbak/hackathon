package com.hackfirst.flamesof5.automate_onboarding.controller;

import com.hackfirst.flamesof5.automate_onboarding.model.AccessRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;

@RestController
@RequestMapping("/api/auto-onboard")
public class AutomateOnboardingController {
    // URL for SailPoint API (example, replace with actual URL)
    private static final String SAILPOINT_API_URL = "https://your-sailpoint-instance/api/access-request";
    private static final String SERVICENOW_API_URL = "https://your-servicenow-instance/api/access-request";

    @Autowired
    private RestTemplate restTemplate; // Spring's RestTemplate to send HTTP requests

    @PostMapping("/sailpoint-request")
    public ResponseEntity<String> createSailpointRequest(@RequestBody AccessRequest accessRequest) {
        // Log input for debugging
        System.out.println("Received Sailpoint Access Request: " + accessRequest.getUserId() + " " +
                accessRequest.getProjectName() + " " + accessRequest.getTeam());

        // Create payload for SailPoint API (you might need to adjust based on the API specs)
        String payload = "{\n" +
                "  \"userId\": \"" + accessRequest.getUserId() + "\",\n" +
                "  \"projectName\": \"" + accessRequest.getProjectName() + "\",\n" +
                "  \"team\": \"" + accessRequest.getTeam() + "\"\n" +
                "}";

        // Set headers (you may need an API key or OAuth token depending on SailPoint's API)
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer <YOUR_SAILPOINT_API_TOKEN>"); // Authorization header (use your SailPoint token)

        HttpEntity<String> entity = new HttpEntity<>(payload, headers);

        try {
            // Send POST request to SailPoint API
            String response = restTemplate.postForObject(SAILPOINT_API_URL, entity, String.class);
            return ResponseEntity.ok("Access request created successfully. Response: " + response);
        } catch (Exception e) {
            // Handle any errors or exceptions
            return ResponseEntity.status(500).body("Failed to create access request. Error: " + e.getMessage());
        }
    }


    @PostMapping("/servicenow-request")
    public ResponseEntity<String> createServiceNowRequest(@RequestBody AccessRequest accessRequest) {
        // Log input for debugging
        System.out.println("Received Service Now Access Request: " + accessRequest.getUserId() + " " +
                accessRequest.getProjectName() + " " + accessRequest.getTeam());

        // Create payload for SailPoint API (you might need to adjust based on the API specs)
        String payload = "{\n" +
                "  \"userId\": \"" + accessRequest.getUserId() + "\",\n" +
                "  \"projectName\": \"" + accessRequest.getProjectName() + "\",\n" +
                "  \"team\": \"" + accessRequest.getTeam() + "\"\n" +
                "}";

        // Set headers (you may need an API key or OAuth token depending on SailPoint's API)
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer <YOUR_SERVICENOW_API_TOKEN>");

        HttpEntity<String> entity = new HttpEntity<>(payload, headers);

        try {
            // Send POST request to SERVICE NOW API
            String response = restTemplate.postForObject(SERVICENOW_API_URL, entity, String.class);
            return ResponseEntity.ok("ServiceNow Access request created successfully. Response: " + response);
        } catch (Exception e) {
            // Handle any errors or exceptions
            return ResponseEntity.status(500).body("Failed to create ServiceNow access request. Error: " + e.getMessage());
        }
    }
}
