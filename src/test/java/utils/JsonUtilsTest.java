package utils;

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
    private JsonUtils jsonUtils;
    private List<Task> tasks;

    @BeforeEach
    public void setUp() {
        jsonUtils = new JsonUtils();
        tasks = new ArrayList<>();
        tasks.add(new Task(1, "Task 1", "pending"));
        tasks.add(new Task(2, "Task 2", "completed"));
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
        jsonUtils.writeTasksToJsonFile(TEST_FILE_PATH, tasks);
        File file = new File(TEST_FILE_PATH);
        assertTrue(file.exists());
    }

    @Test
    public void testReadTasksFromJsonFile() {
        jsonUtils.writeTasksToJsonFile(TEST_FILE_PATH, tasks);
        List<Task> readTasks = jsonUtils.readTasksFromJsonFile(TEST_FILE_PATH);
        assertNotNull(readTasks);
        assertEquals(2, readTasks.size());
        assertEquals("Task 1", readTasks.get(0).getDescription());
        assertEquals("Task 2", readTasks.get(1).getDescription());
    }

}
