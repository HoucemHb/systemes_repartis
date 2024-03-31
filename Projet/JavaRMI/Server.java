package systemes_repartis.Projet.JavaRMI;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            TaskList taskList = new TaskListImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("TaskList", taskList);
            System.out.println("Server started and TaskList bound in registry");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

