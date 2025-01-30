package boundaries;

import models.Task;

import java.util.List;

public interface TaskRepositoryInterface {
    void addTasks(List<Task> tasks);

    List<Task> getAllTasks();

    void updateTask(int taskId, String newDescription, String newStatus);

    void deleteTask(int taskId);
}
