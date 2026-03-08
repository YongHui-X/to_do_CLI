import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JsonFileHandler {
    Gson gson = new Gson();

    public void writeTasks(List<Task> tasks){
        try(Writer writer = new FileWriter("tasks.json")){
            gson.toJson(tasks, writer); //pass in lists
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public List<Task> readTasks() {
        //pointing to where the file is, like writing down an address.
        Path path = Path.of("tasks.json");
        //return empty list
        if (!Files.exists(path)) return new ArrayList<>();
        try {
            //actually go to that house and read what's inside
            String content = Files.readString(path);
            List<Task> tasks = gson.fromJson(content, new TypeToken<List<Task>>(){}.getType());
            return tasks;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
