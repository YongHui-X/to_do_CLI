
public class Main {
    public static void main(String []args){
        TaskManager manager = new TaskManager();
        System.out.println("Welcome to the To Do List app! Add task to begin");

        if (args.length == 0) {
            System.out.println("No Command provided");
            return;
        }

        //args[0] = "add"
        switch (args[0]){
            case "add" -> manager.addTask(args[1]);
            case "update" -> manager.updateTask(Integer.parseInt(args[1]), args[2]);
            case "delete" -> manager.deleteTask(Integer.parseInt(args[1]));
            case "in-progress" -> manager.markInProgress(Integer.parseInt(args[1]));
            case "mark-done" -> manager.markDone(Integer.parseInt(args[1]));
            case "list" -> manager.listTasks(args.length > 1 ? args[1] : null); //f the user provided a second argument, use it — otherwise pass null
            default -> System.out.println("Unknown command");
        }
    }
}
