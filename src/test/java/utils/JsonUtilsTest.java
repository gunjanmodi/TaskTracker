package utils;

import models.TaskStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import models.Task;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonUtilsTest {
    private static final String TEST_FILE_PATH = "src/test/resources/tasks.json";
    private List<Task> tasks;

    @BeforeEach
    public void setUp() {
        tasks = new ArrayList<>();
        tasks.add(new Task(1, "Task 1", TaskStatus.PENDING));
        tasks.add(new Task(2, "Task 2", TaskStatus.COMPLETED));
    }

    @AfterEach
    public void tearDown() {
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testWriteTasksToJsonFile() {
        JsonUtils.writeTasksToJsonFile(TEST_FILE_PATH, tasks);
        File file = new File(TEST_FILE_PATH);
        assertTrue(file.exists());
    }

    @Test
    public void testReadTasksFromJsonFile() {
        JsonUtils.writeTasksToJsonFile(TEST_FILE_PATH, tasks);
        List<Task> readTasks = JsonUtils.readTasksFromJsonFile(TEST_FILE_PATH);
        assertNotNull(readTasks);
        assertEquals(2, readTasks.size());
        assertEquals("Task 1", readTasks.get(0).getDescription());
        assertEquals("Task 2", readTasks.get(1).getDescription());
    }

}
