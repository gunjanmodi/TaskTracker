package services;

import boundaries.TaskRepositoryInterface;
import models.Task;
import models.TaskStatus;

import java.util.List;

public class TaskService {
    TaskRepositoryInterface taskRepository;

    public TaskService(TaskRepositoryInterface taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(int id, String description, TaskStatus status) {
        List<Task> tasks = taskRepository.getAllTasks();
        tasks.add(new Task(id, description, status));
        taskRepository.addTasks(tasks);
    }

    public List<Task> listTasks() {
        return taskRepository.getAllTasks();
    }

    public void updateTask(int taskId, String newDescription, TaskStatus newStatus) {
        taskRepository.updateTask(taskId, newDescription, newStatus);
    }

    public void deleteTask(int taskId) {
        taskRepository.deleteTask(taskId);
    }

    public void markTaskInProgress(int taskId) {
        updateTask(taskId, null, TaskStatus.IN_PROGRESS);
    }

    public void markTaskCompleted(int taskId) {
        updateTask(taskId, null, TaskStatus.COMPLETED);
    }

    public List<Task> listPendingTasks() {
        List<Task> tasks = taskRepository.getAllTasks();
        tasks.removeIf(task -> !task.getStatus().equals(TaskStatus.PENDING));
        return tasks;
    }

    public List<Task> listInProgressTasks() {
        List<Task> tasks = taskRepository.getAllTasks();
        tasks.removeIf(task -> !task.getStatus().equals(TaskStatus.IN_PROGRESS));
        return tasks;
    }

    public List<Task> listCompletedTasks() {
        List<Task> tasks = taskRepository.getAllTasks();
        tasks.removeIf(task -> !task.getStatus().equals(TaskStatus.COMPLETED));
        return tasks;
    }
}
