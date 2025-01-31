package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private int id;
    private String description;
    private TaskStatus status;
    private String createdAt;
    private String updatedAt;

    public Task() {
    }

    public Task(int id, String description, TaskStatus status) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = getCurrentDateTime();
        this.updatedAt = this.createdAt;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
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

    public void setStatus(TaskStatus status) {
        this.status = status;
        updateTimeStamp();
    }

    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description=" + description + "\n" +
                ", status=" + status + "\n" +
                ", createdAt=" + createdAt + "\n" +
                ", updatedAt=" + updatedAt + "\n" +
                "}";
    }
}
