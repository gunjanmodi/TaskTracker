package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private int id;
    private String description;
    private String status;
    private String createdAt;
    private String updatedAt;

    public Task() {
    }

    public Task(int id, String description, String status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
        updateTimeStamp();
    }

    private void updateTimeStamp() {
        this.updatedAt = getCurrentDateTime();
    }

    private String getCurrentDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentDateTime.format(formatter);
    }

    public void setStatus(String status) {
        this.status = status;
        updateTimeStamp();
    }

    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description" + description + "\n" +
                ", status" + status + "\n" +
                ", createdAt" + createdAt + "\n" +
                ", updatedAt" + updatedAt + "\n" +
                "}";
    }
}
