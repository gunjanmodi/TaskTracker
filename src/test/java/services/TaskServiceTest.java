package services;

import boundaries.TaskRepositoryInterface;
import factories.TaskFactory;
import models.Task;
import models.TaskStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import repositories.JsonTaskRepository;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class TaskServiceTest {
    private static final String TEST_FILE_PATH = "src/test/resources/tasks.json";
    private TaskService taskService;

    @BeforeEach
    public void setUp() {
        TaskRepositoryInterface jsonTaskRepository = new JsonTaskRepository(TEST_FILE_PATH);
        TaskFactory taskFactory = new TaskFactory(jsonTaskRepository);
        taskService = new TaskService(jsonTaskRepository, taskFactory);
    }

    @AfterEach
    public void tearDown() {
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testAddTask()    {
        taskService.addTask("Task 10", TaskStatus.COMPLETED);
        Task task = taskService.listTasks().get(0);
        assertEquals("Task 10", task.getDescription());
        assertEquals(TaskStatus.COMPLETED, task.getStatus());
    }

    @Test
    public void testListAllTasks() {
        taskService.addTask("Task 10", TaskStatus.COMPLETED);
        taskService.addTask("Task 11", TaskStatus.PENDING);
        assertEquals(2, taskService.listTasks().size());
    }

    @Test
    public void testUpdateTask()  {
        taskService.addTask("Task 10", TaskStatus.PENDING);

        Task task = taskService.listTasks().get(0);
        taskService.updateTask(task.getId(), "Task 10 Update", TaskStatus.COMPLETED);

        Task updatedTask = taskService.listTasks().get(0);
        assertEquals("Task 10 Update", updatedTask.getDescription());
        assertEquals(TaskStatus.COMPLETED, updatedTask.getStatus());
    }

    @Test
    public void testDeleteTask() {
        taskService.addTask("Task 10", TaskStatus.COMPLETED);
        taskService.addTask("Task 11", TaskStatus.PENDING);

        Task task = taskService.listTasks().get(0);
        taskService.deleteTask(task.getId());

        List<Task> tasks = taskService.listTasks();
        assertEquals(1, tasks.size());
    }

    @Test
    public void testMarkTaskInProgress() {
        taskService.addTask("Task 10", TaskStatus.PENDING);

        Task task = taskService.listTasks().get(0);
        taskService.markTaskInProgress(task.getId());

        List<Task> tasks = taskService.listTasks();
        Task updatedTask = taskService.listTasks().get(0);
        assertEquals(TaskStatus.IN_PROGRESS, updatedTask.getStatus());
        assertEquals("Task 10", updatedTask.getDescription());
        assertNotNull(task.getUpdatedAt());
    }

    @Test
    public void testMarkTaskCompleted() {
        taskService.addTask("Task 10", TaskStatus.IN_PROGRESS);
        Task task = taskService.listTasks().get(0);
        taskService.markTaskCompleted(task.getId());

        Task updatedTask = taskService.listTasks().get(0);

        assertEquals(TaskStatus.COMPLETED, updatedTask.getStatus());
        assertEquals("Task 10", updatedTask.getDescription());
        assertNotNull(task.getUpdatedAt());
    }

    @Test
    public void testListPendingTasks() {
        taskService.addTask("Task 10", TaskStatus.PENDING);
        taskService.addTask("Task 11", TaskStatus.IN_PROGRESS);

        List<Task> completedTasks = taskService.listPendingTasks();

        assertEquals(1, completedTasks.size());
    }

    @Test
    public void testListInProgressTasks() {
        taskService.addTask("Task 10", TaskStatus.IN_PROGRESS);
        taskService.addTask("Task 11", TaskStatus.PENDING);

        List<Task> completedTasks = taskService.listInProgressTasks();

        assertEquals(1, completedTasks.size());
    }

    @Test
    public void testListCompletedTasks() {
        taskService.addTask("Task 10", TaskStatus.COMPLETED);
        taskService.addTask("Task 11", TaskStatus.PENDING);

        List<Task> completedTasks = taskService.listCompletedTasks();

        assertEquals(1, completedTasks.size());
    }

}
