package systemes_repartis.TP3;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.Naming;

public class CalculatorServer {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            Calculator calculator = new CalculatorImpl();
            Naming.rebind("CalculatorService", calculator);
            System.out.println("Calculator Service is running.");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

