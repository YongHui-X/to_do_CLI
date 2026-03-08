import java.util.List;

public class TaskManager {
    JsonFileHandler fileHandler = new JsonFileHandler();
    public void addTask(String description){
        List<Task> tasks = fileHandler.readTasks();


    }

    public void updateTask(String description){

    }
}
