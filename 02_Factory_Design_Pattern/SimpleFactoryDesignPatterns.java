import java.util.Scanner;

/*
 * =========================================================
 * SMART TRANSPORTATION MANAGEMENT SYSTEM
 * Using Simple Factory Design Pattern
 * =========================================================
 */

/* =========================================================
 * PRODUCT INTERFACE
 * =========================================================
 */
interface Vehicle {
    void startTrip();

    double calculateFare(double distance);

    void assignRoute(String route);

    void getVehicleInfo();

    String getVehicleType();
}

/* =========================================================
 * CONCRETE PRODUCTS
 * =========================================================
 */

class Bus implements Vehicle {

    private final int capacity = 40;
    private final double farePerKm = 15;
    private String route;

    @Override
    public void startTrip() {
        System.out.println("Bus trip started.");
    }

    @Override
    public double calculateFare(double distance) {
        return distance * farePerKm;
    }

    @Override
    public void assignRoute(String route) {
        this.route = route;
        System.out.println("Bus assigned to route: " + route);
    }

    @Override
    public void getVehicleInfo() {
        System.out.println("Vehicle: Bus");
        System.out.println("Capacity: " + capacity);
        System.out.println("Fare per KM: " + farePerKm);
        System.out.println("Route: " + route);
    }

    @Override
    public String getVehicleType() {
        return "Bus";
    }
}

class Taxi implements Vehicle {

    private final int capacity = 4;
    private final double farePerKm = 30;
    private String route;

    @Override
    public void startTrip() {
        System.out.println("Taxi trip started.");
    }

    @Override
    public double calculateFare(double distance) {
        return distance * farePerKm;
    }

    @Override
    public void assignRoute(String route) {
        this.route = route;
        System.out.println("Taxi assigned to route: " + route);
    }

    @Override
    public void getVehicleInfo() {
        System.out.println("Vehicle: Taxi");
        System.out.println("Capacity: " + capacity);
        System.out.println("Fare per KM: " + farePerKm);
        System.out.println("Route: " + route);
    }

    @Override
    public String getVehicleType() {
        return "Taxi";
    }
}

class MotorcycleDelivery implements Vehicle {

    private final int capacity = 1;
    private final double farePerKm = 10;
    private String route;

    @Override
    public void startTrip() {
        System.out.println("Motorcycle delivery started.");
    }

    @Override
    public double calculateFare(double distance) {
        return distance * farePerKm;
    }

    @Override
    public void assignRoute(String route) {
        this.route = route;
        System.out.println("Motorcycle assigned to route: " + route);
    }

    @Override
    public void getVehicleInfo() {
        System.out.println("Vehicle: Motorcycle Delivery");
        System.out.println("Capacity: " + capacity);
        System.out.println("Fare per KM: " + farePerKm);
        System.out.println("Route: " + route);
    }

    @Override
    public String getVehicleType() {
        return "Motorcycle Delivery";
    }
}

class ElectricScooter implements Vehicle {

    private final int capacity = 1;
    private final double farePerKm = 8;
    private String route;

    @Override
    public void startTrip() {
        System.out.println("Electric scooter trip started.");
    }

    @Override
    public double calculateFare(double distance) {
        return distance * farePerKm;
    }

    @Override
    public void assignRoute(String route) {
        this.route = route;
        System.out.println("Scooter assigned to route: " + route);
    }

    @Override
    public void getVehicleInfo() {
        System.out.println("Vehicle: Electric Scooter");
        System.out.println("Capacity: " + capacity);
        System.out.println("Fare per KM: " + farePerKm);
        System.out.println("Route: " + route);
    }

    @Override
    public String getVehicleType() {
        return "Electric Scooter";
    }
}

 
class VehicleFactory {

    public static Vehicle createVehicle(int choice) {
        switch (choice) {
            case 1:
                return new Bus();

            case 2:
                return new Taxi();

            case 3:
                return new MotorcycleDelivery();

            case 4:
                return new ElectricScooter();

            default:
                throw new IllegalArgumentException("Invalid vehicle choice.");
        }
    }
}
 
public class SimpleFactoryDesignPatterns {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("======================================");
        System.out.println(" Smart Transportation Management System");
        System.out.println("======================================");

        System.out.println("Choose Vehicle Type:");
        System.out.println("1. Bus");
        System.out.println("2. Taxi");
        System.out.println("3. Motorcycle Delivery");
        System.out.println("4. Electric Scooter");

        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter route: ");
        String route = scanner.nextLine();

        System.out.print("Enter distance in KM: ");
        double distance = scanner.nextDouble();

        try {
            Vehicle vehicle = VehicleFactory.createVehicle(choice);

            System.out.println("\n----- Trip Details -----");

            vehicle.assignRoute(route);
            vehicle.getVehicleInfo();
            vehicle.startTrip();

            double fare = vehicle.calculateFare(distance);

            System.out.println("Total Fare: " + fare);
            System.out.println("Selected Vehicle: " + vehicle.getVehicleType());
            System.out.println("------------------------");

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}

 
