package systemes_repartis.Projet.JavaRMI;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            TaskList taskList = (TaskList) registry.lookup("TaskList");


            List<String> tasks = taskList.getTaskList();

            System.out.println("Tasks in the list:");
            for (String task : tasks) {
                System.out.println(task);
            }
            String action="";
            do {
                System.out.println("Select action : add , remove  or type done to exit : ");
                Scanner scanner = new Scanner(System.in);
                 action = scanner.nextLine();
                switch (action){
                    case "add" :
                        System.out.println("Describe your task: ");
                        String task = scanner.nextLine();
                        taskList.addTask(task);
                        break;
                    case "remove" :
                        System.out.println("Describe the task to remove: ");
                        String taskToRemove = scanner.nextLine();
                        if(!taskList.removeTask(taskToRemove)) System.out.println("Task doesn't exist");;
                        break;
                    case "done":
                        continue;
                    default:
                        System.out.println("Invalid action");
                        break;
                }
                System.out.println("Task list after the action you made:");
                tasks = taskList.getTaskList();
                for (String task : tasks) {
                    System.out.println(task);
                }
            } while(!action.equals("done"));





        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

