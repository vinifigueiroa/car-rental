import java.util.*;

/* This application handles different types of vehicles such as cars, motorcycles, and trucks.
It allows the user to create objects of different vehicle types,
provide relevant information, and display the details of each vehicle.
*/

public class CarRental {

    public static void main(String[] args) {

        // Initialize Scanner
        Scanner scanner = new Scanner(System.in);

        // Load default Vehicles
        {
            new Car("Toyota", "Camry", 2022, 4, "Petrol");
            new Car("Honda", "Civic", 2020, 4, "Petrol");
            new Car("Ford", "Mustang", 2019, 2, "Petrol");
            new Motorcycle("Harley-Davidson", "Sportster", 2021, 2, "Cruiser");
            new Motorcycle("Yamaha", "YZF-R6", 2023, 2, "Sport");
            new Motorcycle("Kawasaki", "KLR650", 2020, 2, "Off-Road");
            new Truck("Ford", "F-150", 2020, 2, "Automatic");
            new Truck("Chevrolet", "Silverado", 2021, 3, "Automatic");
            new Truck("Dodge", "Ram 1500", 2019, 2, "Manual");
        }

        // Intro
        System.out.println("VEHICLE MANAGEMENT SYSTEM. \n\nWELCOME.");

        // Menu
        boolean loop = true;
        while (loop) {

            System.out.println("\n\nMENU \n\nA - Create a new Vehicle \nB - List All Vehicles \nE - Exit \n \nTYPE THE CORRESPONDING LETTER\n");
            String choice = scanner.nextLine().toLowerCase();
            System.out.println("\n");

            switch (choice) {
                case "a":
                    createNewVehicle(scanner);
                    break;

                case "b":
                    listAllVehicles(scanner);
                    break;

                case "e":
                    loop = false;
                    break;

                default:
                    break;
            }
        }
    }

    public static void createNewVehicle(Scanner scanner) {

        System.out.println("\n\nWhich type of vehicle would you like to create? \n\nC - Car \nM - Motorcycle \nT - Truck \n\nTYPE THE CORRESPONDING LETTER\n");
        String choice = scanner.nextLine().toLowerCase();

        System.out.println("\n \nType the make of the Vehicle:");
        String make = scanner.nextLine();

        System.out.println("\n \nType the model of the Vehicle:");
        String model = scanner.nextLine();

        System.out.println("\n \nType the year of the Vehicle:");
        int yearOfManufacture = positiveIntegerInput(scanner);

        boolean condition = true;
        String answer = "n";

        while (condition) {

            System.out.println("\n\n Would you like to add more specific information? \n\n Type 'Y' for yes and 'N' for no.");
            answer = scanner.nextLine().toLowerCase();

            if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n")) {
                condition = false;
                continue;
            }

            System.err.println("ERROR: Type 'Y' for yes and 'N' for no");

        }

        switch (choice) {
            case "c":
                createCar(scanner, answer, make, model, yearOfManufacture);
                break;

            case "m":
                createMotorcycle(scanner, answer, make, model, yearOfManufacture);
                break;

            case "t":
                createTruck(scanner, answer, make, model, yearOfManufacture);
                break;

            default:
                break;
        }
    }

    public static void createCar(Scanner scanner, String answer, String make, String model, int yearOfManufacture) {

        if (answer.equalsIgnoreCase("n")) {

            Car car = new Car(make, model, yearOfManufacture);
            return;
        }

        System.out.println("\n \nType the number of doors of the car:");
        int numberOfDoors = positiveIntegerInput(scanner);

        System.out.println("\n \nEnter the type of fuel of the car:");
        String fuelType = scanner.nextLine();

        Car car = new Car(make, model, yearOfManufacture, numberOfDoors, fuelType);

    }

    public static void createMotorcycle(Scanner scanner, String answer, String make, String model, int yearOfManufacture) {

        if (answer.equalsIgnoreCase("n")) {

            new Motorcycle(make, model, yearOfManufacture);
            return;
        }

        System.out.println("\n \nType the number of wheel of the motorcycle:");
        int numberOfWheels = positiveIntegerInput(scanner);

        System.out.println("\n \nEnter the road type of the motorcycle:");
        String roadType = scanner.nextLine();

        new Motorcycle(make, model, yearOfManufacture, numberOfWheels, roadType);
    }

    public static void createTruck(Scanner scanner, String answer, String make, String model, int yearOfManufacture) {

        if (answer.equalsIgnoreCase("n")) {

            new Truck(make, model, yearOfManufacture);
            return;
        }

        System.out.println("\n \nType the cargo capacity l of the truck:");
        int cargoCapacity = positiveIntegerInput(scanner);

        System.out.println("\n \nEnter the transmisison type of the truck:");
        String transmissionType = scanner.nextLine();

        new Truck(make, model, yearOfManufacture, cargoCapacity, transmissionType);
    }

    public static void listAllVehicles(Scanner scanner) {
        System.out.println("\n \nCARS:\n");
        Car.listAllCars();

        System.out.println("\n \nMOTOCYCLES:\n");
        Motorcycle.listAllMotorcycles();

        System.out.println("\n \nTRUCKS:\n");
        Truck.listAllTrucks();

        pressEnter(scanner);
    }

    // Helper Functions

    public static void pressEnter(Scanner scanner) {

        // Prompts the user for confirmation to continue.

        System.out.println("\nPRESS ENTER TO CONTINUE \n");
        scanner.nextLine();
    }

    public static int positiveIntegerInput(Scanner scanner) {

        //Evaluates if a string input is a valid positive integer and returns the integer value.

        int integer = 0;
        boolean condition = true;

        while(condition) {

            String input = scanner.nextLine();

            try {
                integer = Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.err.println("ERROR: Please enter a number.");
                continue;
            }

            if (integer < 0) {
                System.err.println("ERROR: Please enter a positive amount.");
                continue;

            } else if (integer == 0) {
                return 0;

            }

            condition = false;


        }
        System.out.println("\n");
        return integer;

    }

}


public interface Vehicle {

    public String getMake();
    public String getModel();
    public int getYearOfManufacture();

}


public class Car implements Vehicle, CarVehicle {

    // Class Variables
    private static Map<Integer, Car> allCars = new HashMap<>();
    private static int carsCount = 0;
    private static final int idFormatter = 101;

    // Instance Variables
    private String make;
    private String model;
    private int yearOfManufacture;
    private int numberOfDoors;
    private String fuelType;
    private int ID;


    // Constructors
    public Car(String make, String model, int yearOfManufacture) {
        this.make = make;
        this.model = model;
        this.yearOfManufacture = yearOfManufacture;
        this.ID = carsCount + idFormatter;
        allCars.put(ID, this);
        carsCount++;
    }

    public Car(String make, String model, int yearOfManufacture, int numberOfDoors, String fuelType) {
        this(make, model, yearOfManufacture);
        this.setTypeOfFuel(fuelType);
        this.setNumberOfDoors(numberOfDoors);
    }


    // Vehicle Interface Implementations
    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }


    // CarVehicle Interface Implementations
    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int num) {

        if (num > 0 && num <= 4) {
            this.numberOfDoors = num;
            return;
        }

        throw new IllegalArgumentException("Number should be between 1 and 4");
    }

    public String getTypeOfFuel() {
        return fuelType;
    }

    public void setTypeOfFuel(String fuel) {

        if (fuel.equalsIgnoreCase("Petrol") || fuel.equalsIgnoreCase("Diesel") || fuel.equalsIgnoreCase("Electric")) {
            this.fuelType = fuel;
            return;
        }

        throw new IllegalArgumentException("TypeOfFuel should be 'Petrol', 'Diesel' or 'Electric'.");
    }

    // Instance Methods
    public String toString() {
        return "Make: " + make + " || Model: " + model + " || Year: " + yearOfManufacture + " || Doors: " + numberOfDoors + " || Fuel: " + fuelType + " || ID: " + ID + "\n";
    }

    // Class Methods
    public static void listAllCars() {

        for (Car car : allCars.values()) {
            System.out.println(car.toString());
        }
    }

}


public interface CarVehicle {

    public int getNumberOfDoors();
    public void setNumberOfDoors(int num);
    public String getTypeOfFuel();
    public void setTypeOfFuel(String fuel);

}


public class Motorcycle implements Vehicle, MotorVehicle {

    // Class Variables
    private static Map<Integer, Motorcycle> allMotorcycles = new HashMap<>();
    private static int motorcyclesCount = 0;
    private static final int idFormatter = 101;

    // Instance Variables
    private String make;
    private String model;
    private int yearOfManufacture;
    private int numberOfWheels;
    private String roadType;
    private int ID;


    // Constructors
    public Motorcycle(String make, String model, int yearOfManufacture) {
        this.make = make;
        this.model = model;
        this.yearOfManufacture = yearOfManufacture;
        this.ID = motorcyclesCount + idFormatter;
        allMotorcycles.put(ID, this);
        motorcyclesCount++;
    }

    public Motorcycle(String make, String model, int yearOfManufacture, int numberOfWheels, String roadType) {
        this(make, model, yearOfManufacture);
        this.setNumberOfWheels(numberOfWheels);
        this.setRoadType(roadType);
    }


    // Vehicle Interface Implementations
    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }


    // MotorVehicle Interface Implementations
    public int getNumberOfWheels() {
        return numberOfWheels;
    }


    public void setNumberOfWheels(int num) {

        if (num > 0 && num <=3) {
            this.numberOfWheels = num;
            return;
        }

        throw new IllegalArgumentException("Number of wheel should be between 1 and 3");
    }


    public String getRoadType() {
        return roadType;
    }

    public void setRoadType(String type) {

        if (type.equalsIgnoreCase("Sport") || type.equalsIgnoreCase("Cruiser") || type.equalsIgnoreCase("Off-Road")) {
            this.roadType = type;
            return;
        }

        throw new IllegalArgumentException("RoadType should be 'Sport', 'Cruiser' or 'Off-Road'.");

    }

    // Instance Methods
    public String toString() {
        return "Make: " + make + " || Model: " + model + " || Year: " + yearOfManufacture + " || Wheels: " + numberOfWheels + " || Road: " + roadType + " || ID: " + ID + "\n";
    }

    // Class Methods
    public static void listAllMotorcycles() {

        for (Motorcycle motorcycle : allMotorcycles.values()) {
            System.out.println(motorcycle.toString());
        }
    }
}


public interface MotorVehicle {

    public int getNumberOfWheels();
    public void setNumberOfWheels(int num);
    public String getRoadType();
    public void setRoadType(String type);

}


public class Truck implements Vehicle, TruckVehicle {

     // Class Variables
    private static Map<Integer, Truck> allTrucks = new HashMap<>();
    private static int trucksCount = 0;
    private static final int idFormatter = 101;

    // Instance Variables
    private String make;
    private String model;
    private int yearOfManufacture;
    private int cargoCapacity;
    private String transmissionType;
    private int ID;


    // Constructors
    public Truck(String make, String model, int yearOfManufacture) {
        this.make = make;
        this.model = model;
        this.yearOfManufacture = yearOfManufacture;
        this.ID = trucksCount + idFormatter;
        allTrucks.put(ID, this);
        trucksCount++;
    }

    public Truck(String make, String model, int yearOfManufacture, int cargoCapacity, String transmissionType) {
        this(make, model, yearOfManufacture);
        this.setCargoCapacity(cargoCapacity);
        this.setTransmissionType(transmissionType);
    }


    // Vehicle Interface Implementations
    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }


    // TruckVehicle Interface Implementations
    public int getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(int num) {

        if (num > 0) {
            this.cargoCapacity = num;
            return;
        }

        throw new IllegalArgumentException("Cargo capacity should be greater than zero.");
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String type) {

        if (type.equalsIgnoreCase("Manual") || type.equalsIgnoreCase("Automatic")) {
            this.transmissionType = type;
            return;
        }

        throw new IllegalArgumentException("transmissionType should be 'Manual' or 'Automatic'.");
    }

    // Instance Methods
    public String toString() {
        return "Make: " + make + " || Model: " + model + " || Year: " + yearOfManufacture + " || Capacity: " + cargoCapacity + " || Transmission: " + transmissionType + " || ID: " + ID + "\n";
    }

    // Class Methods
    public static void listAllTrucks() {

        for (Truck truck : allTrucks.values()) {
            System.out.println(truck.toString());
        }
    }

}

public interface TruckVehicle {

    public int getCargoCapacity();
    public void setCargoCapacity(int num);
    public String getTransmissionType();
    public void setTransmissionType(String type);
}
