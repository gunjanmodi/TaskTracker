package boundaries;

import models.Task;
import models.TaskStatus;

import java.util.List;

public interface TaskRepositoryInterface {
    void addTasks(List<Task> tasks);

    List<Task> getAllTasks();

    void updateTask(int taskId, String newDescription, TaskStatus newStatus);

    void deleteTask(int taskId);
}
