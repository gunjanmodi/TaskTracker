package repositories;

import boundaries.TaskRepositoryInterface;
import models.Task;
import models.TaskStatus;

import java.util.ArrayList;
import java.util.List;

public class MockTaskRepository implements TaskRepositoryInterface {
    private final List<Task> tasks = new ArrayList<>();

    @Override
    public void addTasks(List<Task> newTasks) {
        tasks.addAll(newTasks);
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>();
    }

    @Override
    public void updateTask(int taskId, String newDescription, TaskStatus newStatus) {
        for (Task task : tasks) {
            if (task.getId() == taskId) {
                if (newDescription != null) {
                    task.setDescription(newDescription);
                }
                if (newStatus != null) {
                    task.setStatus(newStatus);
                }
                break;
            }
        }
    }

    @Override
    public void deleteTask(int taskId) {
        tasks.removeIf(task -> task.getId() == taskId);
    }
}
