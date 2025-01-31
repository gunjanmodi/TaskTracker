package models;

import boundaries.TaskFactoryInterface;
import factories.TaskFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.MockTaskRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TaskTest {
    private TaskFactoryInterface taskFactory;

    @BeforeEach
    public void setUp() {
        taskFactory = new TaskFactory(new MockTaskRepository());
    }
    @Test
    public void testTaskInitialization() {
        Task task = taskFactory.createTask("Test Task", TaskStatus.PENDING);
        assertNotNull(task.getId());
        assertEquals("Test Task", task.getDescription());
        assertEquals(TaskStatus.PENDING, task.getStatus());
    }

    @Test
    public void testTaskSetDescription() {
        Task task = taskFactory.createTask("Test Task", TaskStatus.PENDING);
        task.setDescription("Updated Task");
        assertEquals("Updated Task", task.getDescription());
    }

    @Test
    public void testTaskSetStatus() {
        Task task = taskFactory.createTask("Test Task", TaskStatus.PENDING);
        task.setStatus(TaskStatus.COMPLETED);
        assertEquals(TaskStatus.COMPLETED, task.getStatus());
    }

    @Test
    public void testTaskSetId() {
        Task task = taskFactory.createTask("Test Task", TaskStatus.PENDING);
        task.setId(2);
        assertEquals(2, task.getId());
    }
}
