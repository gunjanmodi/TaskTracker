package services;

import boundaries.TaskRepositoryInterface;
import models.Task;

import java.util.List;

public class TaskService {
    TaskRepositoryInterface taskRepository;

    public TaskService(TaskRepositoryInterface taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(int id, String description, String status) {
        List<Task> tasks = taskRepository.getAllTasks();
        tasks.add(new Task(id, description, status));
        taskRepository.addTasks(tasks);
    }

    public List<Task> listTasks() {
        return taskRepository.getAllTasks();
    }

    public void updateTask(int taskId, String newDescription, String newStatus) {
        taskRepository.updateTask(taskId, newDescription, newStatus);
    }

    public void deleteTask(int taskId) {
        taskRepository.deleteTask(taskId);
    }

    public void markTaskInProgress(int taskId) {
        updateTask(taskId, null, "in-progress");
    }

    public void markTaskCompleted(int taskId) {
        updateTask(taskId, null, "completed");
    }

    public List<Task> listPendingTasks() {
        List<Task> tasks = taskRepository.getAllTasks();
        tasks.removeIf(task -> !task.getStatus().equals("pending"));
        return tasks;
    }

    public List<Task> listInProgressTasks() {
        List<Task> tasks = taskRepository.getAllTasks();
        tasks.removeIf(task -> !task.getStatus().equals("in-progress"));
        return tasks;
    }

    public List<Task> listCompletedTasks() {
        List<Task> tasks = taskRepository.getAllTasks();
        tasks.removeIf(task -> !task.getStatus().equals("completed"));
        return tasks;
    }
}
