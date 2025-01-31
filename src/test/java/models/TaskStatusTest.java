package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskStatusTest {
    @Test
    public void testTaskStatus() {
        TaskStatus[] statuses = TaskStatus.values();
        assertEquals(3, statuses.length);
        assertEquals(TaskStatus.PENDING, statuses[0]);
        assertEquals(TaskStatus.IN_PROGRESS, statuses[1]);
        assertEquals(TaskStatus.COMPLETED, statuses[2]);
    }
}
