package services;

import models.Task;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import repositories.JsonTaskRepository;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskServiceTest {
    private static final String TEST_FILE_PATH = "src/test/resources/tasks.json";
    private JsonTaskRepository jsonTaskRepository;
    private TaskService taskService;

    @BeforeEach
    public void setUp() {
        jsonTaskRepository = new JsonTaskRepository(TEST_FILE_PATH);
        taskService = new TaskService(jsonTaskRepository);
    }

    @AfterEach
    public void tearDown() {
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testAddTask() {
        taskService.addTask(10, "Task 10", "completed");
        Task task = taskService.listTasks().get(0);
        assertEquals(10, task.getId());
        assertEquals("Task 10", task.getDescription());
        assertEquals("completed", task.getStatus());
    }

    @Test
    public void testListAllTasks() {
        taskService.addTask(10, "Task 10", "completed");
        taskService.addTask(11, "Task 11", "pending");
        assertEquals(2, taskService.listTasks().size());
    }

    @Test
    public void updateTask()  {
        taskService.addTask(10, "Task 10", "pending");

        taskService.updateTask(10, "Task 10 Update", "completed");

        List<Task> tasks = taskService.listTasks();
        Task task = tasks.get(0);
        assertEquals("Task 10 Update", task.getDescription());
        assertEquals("completed", task.getStatus());
    }

    @Test
    public void deleteTask() {
        taskService.addTask(10, "Task 10", "completed");
        taskService.addTask(11, "Task 11", "pending");

        taskService.deleteTask(10);

        List<Task> tasks = taskService.listTasks();
        assertEquals(1, tasks.size());
        assertEquals(11, tasks.get(0).getId());
    }

}
