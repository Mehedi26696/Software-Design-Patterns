
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

/*
 * =========================================================
 * SMART TRANSPORTATION MANAGEMENT SYSTEM
 * Registry-Based Factory Pattern
 * =========================================================
 */

interface Vehicle {
    void startTrip();

    double calculateFare(double distance);

    void assignRoute(String route);

    void getVehicleInfo();

    String getVehicleType();
}

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

/*
 * =========================================================
 * REGISTRY-BASED FACTORY
 * =========================================================
 */
class VehicleFactory {

    private static final Map<String, Supplier<Vehicle>> registry = new HashMap<>();

    public static void registerVehicle(String type, Supplier<Vehicle> supplier) {
        registry.put(type.toLowerCase(), supplier);
    }

    public static Vehicle createVehicle(String type) {
        Supplier<Vehicle> supplier = registry.get(type.toLowerCase());

        if (supplier == null) {
            throw new IllegalArgumentException("Unknown vehicle type: " + type);
        }

        return supplier.get();
    }
}

/*
 * =========================================================
 * CLIENT CODE
 * =========================================================
 */
public class RegistryBased {

    public static void main(String[] args) {

        VehicleFactory.registerVehicle("bus", Bus::new);
        VehicleFactory.registerVehicle("taxi", Taxi::new);
        VehicleFactory.registerVehicle("motorcycle", MotorcycleDelivery::new);
        VehicleFactory.registerVehicle("scooter", ElectricScooter::new);

        Scanner scanner = new Scanner(System.in);

        System.out.println("======================================");
        System.out.println(" Smart Transportation Management System");
        System.out.println(" Registry-Based Factory Demo");
        System.out.println("======================================");

        System.out.println("Available Vehicle Types:");
        System.out.println("bus");
        System.out.println("taxi");
        System.out.println("motorcycle");
        System.out.println("scooter");

        System.out.print("Enter vehicle type: ");
        String type = scanner.nextLine();

        System.out.print("Enter route: ");
        String route = scanner.nextLine();

        System.out.print("Enter distance in KM: ");
        double distance = scanner.nextDouble();

        try {
            Vehicle vehicle = VehicleFactory.createVehicle(type);

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

 
