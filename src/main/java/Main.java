
public class Main {
    public static void main(String []args){
        TaskManager manager = new TaskManager();
        System.out.println("Welcome to the To Do List app! Add task to begin");

        if (args.length == 0) {
            System.out.println("No Command provided");
        }

        //args[0] = "add"
        switch (args[0]){
            case "add" -> manager.addTask(args[1]);
        }
    }
}
