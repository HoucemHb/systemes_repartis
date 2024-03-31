package systemes_repartis.Projet.JavaRMI;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TaskListImpl extends UnicastRemoteObject implements TaskList {
    private final List<String> taskList;

    protected TaskListImpl() throws RemoteException {
        taskList = new CopyOnWriteArrayList<>();
    }

    public void addTask(String task) throws RemoteException {
        taskList.add(task);
    }

    public boolean removeTask(String task) throws RemoteException {
        return taskList.remove(task);
    }

    public List<String> getTaskList() throws RemoteException {
        return taskList;
    }
}