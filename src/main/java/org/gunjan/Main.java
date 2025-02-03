package org.gunjan;

import boundaries.TaskFactoryInterface;
import boundaries.TaskRepositoryInterface;
import factories.TaskFactory;
import models.Task;
import models.TaskStatus;
import repositories.JsonTaskRepository;
import services.TaskService;

import java.util.List;

public class Main {
    private static final String DATA_FILE_PATH = "src/main/resources/tasks.json";
    private static TaskService taskService;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java Main <command> [options]");
            return;
        }
        TaskRepositoryInterface jsonTaskRepository = new JsonTaskRepository(DATA_FILE_PATH);
        TaskFactoryInterface taskFactory = new TaskFactory(jsonTaskRepository);
        taskService = new TaskService(jsonTaskRepository, taskFactory);

        String command = args[0];
        handleAddTask(args, command);
        handleUpdateTask(args, command);
        handleDeleteTask(args, command);
        handleListTasks(args, command);
        handleMarkTaskInProgress(args, command);
        handleMarkTaskCompleted(args, command);

    }

    private static void handleMarkTaskCompleted(String[] args, String command) {
        if(command.equals("markCompleted")) {
            if(args.length != 2) {
                System.out.println("Usage: java Main markCompleted <id>");
            }

            int taskId = Integer.parseInt(args[1]);
            try {
                taskService.markTaskCompleted(taskId);
                System.out.println("Task marked as completed");
            } catch(IllegalArgumentException e) {
                System.out.println("Task not found");
            }
        }
    }
    private static void handleMarkTaskInProgress(String[] args, String command) {
        if (command.equals("markInProgress")) {
            if (args.length != 2) {
                System.out.println("Usage: java Main markInProgress <id>");
                return;
            }
            int taskId = Integer.parseInt(args[1]);
            try {
                taskService.markTaskInProgress(taskId);
                System.out.println("Task marked as in progress");
            } catch(IllegalArgumentException e) {
                System.out.println("Task not found");
            }
        }

    }
    private static void handleListTasks(String[] args, String command) {

        if (command.equals("list")) {
            if (args.length != 2) {
                System.out.println("Usage: java Main list <all|pending|in-progress|completed>");
                return;
            }
            String filter = args[1];
            List<Task> tasks;

            if(filter.equals("all")) {
                tasks = taskService.listTasks();
            } else if(filter.equals("pending")) {
                tasks = taskService.listPendingTasks();
            } else if(filter.equals("in_progress")) {
                tasks = taskService.listInProgressTasks();
            } else if(filter.equals("completed")) {
                tasks = taskService.listCompletedTasks();
            } else {
                System.out.println("Invalid filter");
                return;
            }
            tasks.forEach(task -> {
                System.out.println("Task Id: " + task.getId() + ", Description: " + task.getDescription() + ", Status: " + task.getStatus());
            });
        }
    }
    private static void handleDeleteTask(String[] args, String command) {
        if (command.equals("delete")) {
            if (args.length != 2) {
                System.out.println("Usage: java Main delete <id>");
                return;
            }
            try {
                int taskId = Integer.parseInt(args[1]);
                try {
                    taskService.deleteTask(taskId);
                    System.out.println("Task deleted");
                } catch(IllegalArgumentException e) {
                    System.out.println("Task not found");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid id");
            }
        }
    }
    private static void handleAddTask(String[] args, String command) {
        if(command.equals("add")) {
            if (args.length != 3) {
                System.out.println("Usage: java Main add <description> <status>");
                return;
            }

            String description = args[1];
            String status = args[2];

            try {
                TaskStatus taskStatus = TaskStatus.valueOf(status.toUpperCase());
                taskService.addTask(description, taskStatus);
                System.out.println("Task added");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid status");
            }

        }
    }
    private static void handleUpdateTask(String[] args, String command) {
        if(command.equals("update")) {
            if(args.length < 3 || args.length > 4) {
                System.out.println("Usage: java Main update <id> description=[<description>] status=[<status>]");
                return;
            }

            String taskId = args[1];
            String description = null;
            String status = null;

            if (args.length == 3) {

                if(args[2].contains("description=")) {
                    description = args[2].split("description=")[1];
                }
                else if(args[2].contains("status=")) {
                    status = args[2].split("status=")[1];
                }
            }

            if (args.length == 4) {
                if(args[2].contains("description=")) {
                    description = args[2].split("description=")[1];
                }
                if(args[3].contains("status=")) {
                    status = args[3].split("status=")[1];
                }
            }

            try {
                TaskStatus taskStatus = status != null ? TaskStatus.valueOf(status.toUpperCase()) : null;
                System.out.println("Task Id: " + taskId + " Description: " + description + " Status: " + taskStatus);
                taskService.updateTask(Integer.parseInt(taskId), description, taskStatus);
                System.out.println("Task updated");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid status");
            }
        }
    }
}