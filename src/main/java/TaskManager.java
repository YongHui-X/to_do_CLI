import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class TaskManager {
    JsonFileHandler fileHandler = new JsonFileHandler();

    // Define a custom formatter pattern for a readable format
    DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Task findById(List<Task> tasks, int id){
        return tasks.stream()
                .filter(t-> t.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addTask(String description){
        List<Task> tasks = fileHandler.readTasks();
        int newid;
        if (tasks == null || tasks.isEmpty()){
            newid = 1;
        } else {
            newid = tasks.stream() //loop
                    .mapToInt(Task::getId)//grab id from each task
                    .max() //find highest
                    .orElse(0) + 1; //if list empty , default to 0
        }

        // Format the LocalDateTime instance
        String formattedDateTime = LocalDateTime.now().format(customFormatter);

        Task task = new Task(newid, "todo", description, formattedDateTime, formattedDateTime);

        //tasks is the list, add item to list.
        tasks.add(task);
        //save the list to file
        fileHandler.writeTasks(tasks);
        System.out.println("Task added successfully (ID: " + newid +")");
    }

    public void updateTask(int id, String description){
        List<Task> tasks = fileHandler.readTasks(); // fresh read every time
        Task task = findById(tasks, id);

        if (task == null){
            System.out.println("Task not found");
            return;
        }

        String formattedDateTime = LocalDateTime.now().format(customFormatter);

        task.setDescription(description);
        task.setUpdatedAt(formattedDateTime);

        //save the entire list back to the file
        fileHandler.writeTasks(tasks);
        System.out.println("Task updated successfully");
    }

    public void deleteTask(int id){
        List<Task> tasks = fileHandler.readTasks();
        Task task = findById(tasks, id);

        if (task == null){
            System.out.println("Task not found");
            return;
        }
        tasks.remove(task);
        fileHandler.writeTasks(tasks);
        System.out.println("Task deleted successfully");
    }
}
