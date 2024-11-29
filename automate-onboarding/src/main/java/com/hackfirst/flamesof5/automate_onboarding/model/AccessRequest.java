package com.hackfirst.flamesof5.automate_onboarding.model;

public class AccessRequest {
    private String userId;
    private String projectName;
    private String team;

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public AccessRequest(String userId, String projectName, String team) {
        this.userId = userId;
        this.projectName = projectName;
        this.team = team;
    }
}
