# Task CLI

A command-line to-do list app built with Java. Tasks are stored locally in a `tasks.json` file in the current directory.

Task 1 is deleted.

<img width="336" height="95" alt="Screenshot 2026-03-11 161618" src="https://github.com/user-attachments/assets/cb9e5fea-3d61-4459-806a-e504c358cf7e" />

## Tech Stack

- Java
- Lombok
- Gson

## Getting Started

### Prerequisites

- Java 17+
- Maven
- IntelliJ IDEA (recommended)

### Running the App

1. Clone the repository
2. Open the project in IntelliJ
3. Go to **Run > Edit Configurations**
4. Click **+** and select **Application**
5. Set **Main class** to `Main`
6. Set **Program arguments** to your command (see below)
7. Click **OK** and hit Run

## Commands

### Add a task
```
add "Task description"
```
Example: `add "Buy groceries"`
Output: `Task added successfully (ID: 1)`

### Update a task
```
update <id> "New description"
```
Example: `update 1 "Buy groceries and cook dinner"`
Output: `Task updated successfully`

### Delete a task
```
delete <id>
```
Example: `delete 1`
Output: `Task deleted successfully`

### Mark a task as in progress
```
in-progress <id>
```
Example: `in-progress 1`
Output: `Task marked in progress`

### Mark a task as done
```
mark-done <id>
```
Example: `mark-done 1`
Output: `Task marked done`

### List all tasks
```
list
```

### List tasks by status
```
list todo
list in-progress
list done
```

## Task Properties

Each task stored in `tasks.json` has the following fields:

| Field | Description |
|---|---|
| `id` | Auto-incremented unique identifier |
| `description` | Task description |
| `status` | `todo`, `in-progress`, or `done` |
| `createdAt` | Timestamp when task was created |
| `updatedAt` | Timestamp when task was last updated |

## Project Structure

```
src/
├── Main.java            # Entry point, command routing
├── TaskManager.java     # Business logic for all task operations
├── JsonFileHandler.java # Reads and writes tasks.json
└── Task.java            # Task data model
```

## Example Output

```
[todo] 1 - Buy groceries
[in-progress] 2 - Cook dinner
[done] 3 - Walk the dog
```
