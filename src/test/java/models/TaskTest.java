package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void testTaskInitialization() {
        Task task = new Task(1, "Test Task", TaskStatus.PENDING);
        assertEquals(1, task.getId());
        assertEquals("Test Task", task.getDescription());
        assertEquals(TaskStatus.PENDING, task.getStatus());
    }

    @Test
    public void testTaskSetDescription() {
        Task task = new Task(1, "Test Task", TaskStatus.PENDING);
        task.setDescription("Updated Task");
        assertEquals("Updated Task", task.getDescription());
    }

    @Test
    public void testTaskSetStatus() {
        Task task = new Task(1, "Test Task", TaskStatus.PENDING);
        task.setStatus(TaskStatus.COMPLETED);
        assertEquals(TaskStatus.COMPLETED, task.getStatus());
    }

    @Test
    public void testTaskSetId() {
        Task task = new Task(1, "Test Task", TaskStatus.PENDING);
        task.setId(2);
        assertEquals(2, task.getId());
    }
}
