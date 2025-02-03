
# TaskTracker

TaskTracker is a simple command-line application for managing tasks. It allows you to add, update, delete, and list tasks with different statuses.

## Features

- Add a new task with a description and status
- Update an existing task's description and/or status
- Delete a task by its ID
- Mark a task as in_progress or completed
- List tasks by their status (all, pending, in_progress, completed)

The features are inspired from here: [TaskTracker Plan](https://roadmap.sh/projects/task-tracker)

## Requirements

- Java 11 or higher
- Maven

## Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/gunjanmodi/TaskTracker.git
    cd TaskTracker
    ```

2. Build the project using Maven:
    ```sh
    mvn clean install
    ```

## Usage

Run the application with the following commands:

### Add a Task
```sh
java -cp target/TaskTracker-1.0-SNAPSHOT.jar org.gunjan.Main add <description> <status>
```

### Update a Task
```sh
java -cp target/TaskTracker-1.0-SNAPSHOT.jar org.gunjan.Main update <id> description=[<description>] status=[<status>]
```

### Delete a Task
```sh
java -cp target/TaskTracker-1.0-SNAPSHOT.jar org.gunjan.Main delete <id>
```

### List Tasks
```sh
java -cp target/TaskTracker-1.0-SNAPSHOT.jar org.gunjan.Main list <all|pending|in-progress|completed>
```

### Mark Task as In-Progress
```sh
java -cp target/TaskTracker-1.0-SNAPSHOT.jar org.gunjan.Main markInProgress <id>
```

### Mark Task as Completed
```sh
java -cp target/TaskTracker-1.0-SNAPSHOT.jar org.gunjan.Main markCompleted <id>
```

## License

This project is licensed under the MIT License.
