import java.util.Scanner;

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

 
abstract class VehicleCreator {

    public abstract Vehicle createVehicle();
 
    public void manageTrip(String route, double distance) {
        Vehicle vehicle = createVehicle();

        System.out.println("\n----- Trip Details -----");

        vehicle.assignRoute(route);
        vehicle.getVehicleInfo();
        vehicle.startTrip();

        double fare = vehicle.calculateFare(distance);

        System.out.println("Total Fare: " + fare);
        System.out.println("Selected Vehicle: " + vehicle.getVehicleType());
        System.out.println("------------------------");
    }
}


class BusCreator extends VehicleCreator {
    @Override
    public Vehicle createVehicle() {
        return new Bus();
    }
}

class TaxiCreator extends VehicleCreator {
    @Override
    public Vehicle createVehicle() {
        return new Taxi();
    }
}

class MotorcycleDeliveryCreator extends VehicleCreator {
    @Override
    public Vehicle createVehicle() {
        return new MotorcycleDelivery();
    }
}

class ElectricScooterCreator extends VehicleCreator {
    @Override
    public Vehicle createVehicle() {
        return new ElectricScooter();
    }
}
 
public class Mehedi_13 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("======================================");
        System.out.println(" Smart Transportation Management System");
        System.out.println(" Factory Method Pattern Demo");
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

        VehicleCreator creator;

        switch (choice) {
            case 1:
                creator = new BusCreator();
                break;

            case 2:
                creator = new TaxiCreator();
                break;

            case 3:
                creator = new MotorcycleDeliveryCreator();
                break;

            case 4:
                creator = new ElectricScooterCreator();
                break;

            default:
                System.out.println("Invalid vehicle choice.");
                scanner.close();
                return;
        }

        creator.manageTrip(route, distance);

        scanner.close();
    }
}