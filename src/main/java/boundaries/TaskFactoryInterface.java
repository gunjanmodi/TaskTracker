package boundaries;

import models.Task;
import models.TaskStatus;

public interface TaskFactoryInterface {
    Task createTask(String description, TaskStatus status);
}
