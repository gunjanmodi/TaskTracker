package factories;

import boundaries.TaskFactoryInterface;
import boundaries.TaskRepositoryInterface;
import models.Task;
import models.TaskStatus;
import repositories.JsonTaskRepository;

import java.util.List;


public class TaskFactory implements TaskFactoryInterface {
    private final TaskRepositoryInterface taskRepository;
    private static int idCounter = 0;

    public TaskFactory(TaskRepositoryInterface taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(String description, TaskStatus status) {
        return new Task(generateTaskId(), description, status);
    }

    private int generateTaskId() {
        List<Task> tasks = taskRepository.getAllTasks();
        for(Task task: tasks) {
            idCounter = Math.max(idCounter, task.getId());
        }
        return ++idCounter;
    }
}
