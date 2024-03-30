package systemes_repartis.TP3;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class CalculatorClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(null);
            Calculator stub = (Calculator) registry.lookup("CalculatorService");
            Scanner scanner = new Scanner(System.in);
            String continueCalculation = "yes";

            while ("yes".equalsIgnoreCase(continueCalculation)) {
                System.out.println("Enter operation code (add, subtract, multiply, divide): ");
                String operation = scanner.next();
                System.out.println("Enter first operand: ");
                int a = scanner.nextInt();
                System.out.println("Enter second operand: ");
                int b = scanner.nextInt();

                int result = 0;
                switch (operation.toLowerCase()) {
                    case "add":
                        result = stub.add(a, b);
                        break;
                    case "subtract":
                        result = stub.subtract(a, b);
                        break;
                    case "multiply":
                        result = stub.multiply(a, b);
                        break;
                    case "divide":
                        result = (int)stub.divide(a, b);
                        break;
                    default:
                        System.out.println("Invalid operation");
                        continue;
                }
                System.out.println("Result: " + result);
                System.out.println("Do you want to perform another operation? (yes/no): ");
                continueCalculation = scanner.next();
            }
            scanner.close();
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
