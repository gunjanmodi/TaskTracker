package repositories;

import boundaries.TaskRepositoryInterface;
import models.Task;
import models.TaskStatus;
import utils.JsonUtils;

import java.util.List;

public class JsonTaskRepository implements TaskRepositoryInterface {
    private final String filePath;
    public JsonTaskRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void addTasks(List<Task> tasks) {
        JsonUtils.writeTasksToJsonFile(filePath, tasks);
    }

    @Override
    public List<Task> getAllTasks() {
        return JsonUtils.readTasksFromJsonFile(filePath);
    }

    @Override
    public void updateTask(int taskId, String newDescription, TaskStatus newStatus) {
        List<Task> tasks = getAllTasks();
        boolean taskFound = false;
        for(Task task: tasks) {
            if(task.getId() == taskId) {
                if(newDescription != null) task.setDescription(newDescription);
                if(newStatus != null) task.setStatus(newStatus);
                taskFound = true;
                break;
            }
        }

        if(!taskFound) {
            throw new IllegalArgumentException("Task not found");
        }
        JsonUtils.writeTasksToJsonFile(filePath, tasks);
    }

    @Override
    public void deleteTask(int taskId) {
        List<Task> tasks = getAllTasks();
        tasks.removeIf(task -> task.getId() == taskId);
        JsonUtils.writeTasksToJsonFile(filePath, tasks);
    }

}
