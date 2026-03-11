import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


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

    public boolean isNotFound(Task task){
        if (task == null){
            System.out.println("Task not found");
            return true;
        }
        return false;
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

        if (isNotFound(task)) return;

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

        if (isNotFound(task)) return;

        tasks.remove(task);
        fileHandler.writeTasks(tasks);
        System.out.println("Task deleted successfully");
    }

    public void markInProgress(int id){
        List<Task> tasks = fileHandler.readTasks();
        Task task = findById(tasks, id);

        if (isNotFound(task)) return;

        task.setStatus("in-progress");
        String formattedDateTime = LocalDateTime.now().format(customFormatter);
        task.setUpdatedAt(formattedDateTime);
        fileHandler.writeTasks(tasks);
        System.out.println("Task marked in progress");
    }

    public void markDone(int id){
        List<Task> tasks = fileHandler.readTasks();
        Task task = findById(tasks, id);

        if (isNotFound(task)) return;

        task.setStatus("done");
        String formattedDateTime = LocalDateTime.now().format(customFormatter);
        task.setUpdatedAt(formattedDateTime);
        fileHandler.writeTasks(tasks);
        System.out.println("Task marked done");
    }

    public void listTasks(String filter){
        List<Task> tasks = fileHandler.readTasks();
        List<Task> result;

        if (filter == null) {
            result = tasks;
        } else {
            result = tasks.stream()
                    .filter(t -> t.getStatus().equals(filter))
                    .collect(Collectors.toList());
        }

        for (Task t : result){
            System.out.println("[" + t.getStatus() + "] " + t.getId() + " - " + t.getDescription());
        }
    }


}
