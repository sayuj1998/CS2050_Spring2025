/*
 * This program simulates a car vending machine with multiple floors and spaces.
 * It allows loading car data from a file and displaying the vending machine.
 * It retrieves cars, and sorts the inventory by price or year.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {
    /**
     * @param args The main method to execute the car vending machine
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of floors for the car vending machine: ");
        int floors = scanner.nextInt();
        System.out.print("Enter the number of spaces for the car vending machine: ");
        int spaces = scanner.nextInt();

        VendingMachine vendingMachine = new VendingMachine(floors, spaces);

        boolean runningProgram = true;
        while (runningProgram) {
            System.out.println("\n=== Car Vending Machine Menu ===");
            System.out.println("1. Load Car Data");
            System.out.println("2. Display Vending Machine");
            System.out.println("3. Retrieve a Car");
            System.out.println("4. Print Sorted Inventory (Price)");
            System.out.println("5. Print Sorted Inventory (Year)");
            System.out.println("6. Exit");
            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the file name: ");
                    String filename = scanner.next();
                    readCarFromFile(filename, vendingMachine);
                    break;
                case 2:
                    vendingMachine.displayVendingMachine();
                    break;
                case 3:
                    System.out.print("Enter floor to retrieve car: ");
                    int floor = scanner.nextInt() - 1;
                    System.out.print("Enter space to retrieve car: ");
                    int space = scanner.nextInt() - 1;
                    vendingMachine.retrieveCar(floor, space);
                    break;
                case 4:
                    vendingMachine.printSortedInventoryByPrice();
                    break;
                case 5:
                    vendingMachine.printSortedInventoryByYear();
                    break;
                case 6:
                    System.out.println("Exiting program. Goodbye!");
                    runningProgram = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }//end main method

    /**
     * Reads car data from a file and adds it to the vending machine
     * @param fileName name of file containing car data
     * @param vendingMachine vending machine to add cars to
     */
    private static void readCarFromFile(String fileName, VendingMachine vendingMachine) {
        try {
            Scanner fileScanner = new Scanner(new File(fileName));
            while (fileScanner.hasNext()) {
                int floor = fileScanner.nextInt();
                int space = fileScanner.nextInt();
                int year = fileScanner.nextInt();
                double price = fileScanner.nextDouble();
                String make = fileScanner.next();
                String model = fileScanner.next();

                Car car = new Car(floor, space, year, price, make, model);
                vendingMachine.addCar(car);
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File " + fileName + " not found.");
        }
    }//end readCarFromFile method
}//end Driver class

/**
 * Car class represents a car with its details
 */
class Car {
    private int floor;
    private int space;
    private int year;
    private double price;
    private String make;
    private String model;

    /**
     * @param floor floor of where the car if located
     * @param space space of where the car is located
     * @param year manufacturing year of the car
     * @param price price of the car
     * @param make make of the car
     * @param model model of the car
     */
    public Car(int floor, int space, int year, double price, String make, String model) {
        this.floor = floor;
        this.space = space;
        this.year = year;
        this.price = price;
        this.make = make;
        this.model = model;
    }

    public int getFloor() {
        return floor;
    }

    public int getSpace() {
        return space;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return make + " " + model + " " + year + " - $" + price;
    }

}//end Car class

/**
 * Vending machine class represents a car vending machine with multiple floors and spaces
 */
class VendingMachine {
    private int floors;
    private int spaces;
    private Car[][] inventory;

    /**
     * @param floors number of floors in the vending machine
     * @param spaces number of spaces on each floor
     */
    public VendingMachine(int floors, int spaces) {
        this.floors = floors;
        this.spaces = spaces;
        this.inventory = new Car[floors][spaces];
    }

    /**
     * Adds a car to the vending machine
     *
     * @param car the car to add
     */
    public void addCar(Car car) {
        int floor = car.getFloor();
        int space = car.getSpace();
        if (floor >= floors || space >= spaces) {
            System.out.println("Error: Invalid position at Floor: " + (floor + 1) + " Space: " + (space + 1));
            System.out.println("Can not place Car " + car.getMake() + " " + car.getModel() + " " + car.getYear() + " - $" + car.getPrice());

        } else if (inventory[floor][space] != null) {
            System.out.println("Error: Slot at Floor: " + (floor + 1) + " Space: " + (space + 1) + " is already occupied.");
            System.out.println("Car " + car.getMake() + " " + car.getModel() + " " + car.getYear() + " - $" + car.getPrice() + " cannot be placed.");

        } else {
            inventory[floor][space] = car;
        }
    }//end addCar method

    /**
     * Displays the vending machine
     */
    public void displayVendingMachine() {
        for (int floor = 0; floor < floors; floor++) {
            System.out.println("Floor " + (floor + 1) + ":");
            for (int space = 0; space < spaces; space++) {
                Car car = inventory[floor][space];
                if (car == null) {
                    System.out.println("\tSpace " + (space + 1) + ": EMPTY");
                } else {
                    System.out.println("\tSpace " + (space + 1) + ": " + car);
                }
            }
        }
    }//end displayVendingMachine method

    /**
     * Retrieves a car from the vending machine
     *
     * @param floor the floor to retrieve the car from
     * @param space the space to retrieve the car from
     */
    public void retrieveCar(int floor, int space) {
        Car car = inventory[floor][space];
        if (car == null) {
            System.out.println("No car located at Floor " + (floor + 1) + " Location " + (space + 1));
        } else {
            System.out.println("Car retrieved from Floor " + (floor + 1) + " Location " + (space + 1) + ": " + car);
        }
    }//end retrieveCar method

    /**
     * Prints the inventory sorted by price
     */
    public void printSortedInventoryByPrice() {
        Car[] cars = flatten2DArrayTo1DArray();
        insertionSortByPrice(cars);
        System.out.println("Sorted Inventory by Price:");
        printCars(cars);
    }//end printSortedInventoryByPrice method

    /**
     * Prints the inventory sorted by year
     */
    public void printSortedInventoryByYear() {
        Car[] cars = flatten2DArrayTo1DArray();
        insertionSortByYear(cars);
        System.out.println("Sorted Inventory by Year:");
        printCars(cars);
    }//end printSortedInventoryByYear method

    /**
     * @param cars Prints the array of cars
     */
    private void printCars(Car[] cars) {
        for (int i = 0; i < cars.length; i++) {
            if (cars[i] != null) {
                System.out.println(cars[i]);
            }
        }
    }//end printCars method

    /**
     * Flattens the 2D array of cars into a 1D array
     *
     * @return the flattened 1D array of cars
     */
    private Car[] flatten2DArrayTo1DArray() {
        Car[] cars = new Car[floors * spaces];
        int count = 0;
        for (int floor = 0; floor < floors; floor++) {
            for (int space = 0; space < spaces; space++) {
                if (inventory[floor][space] != null) {
                    cars[count++] = inventory[floor][space];
                }
            }
        }

        Car[] flattenedArray = new Car[count];
        for (int i = 0; i < count; i++) {
            flattenedArray[i] = cars[i];
        }
        return flattenedArray;
    }//end flatten2DArrayTo1DArray method

    /**
     * Sorts an array of cars by price using insertion sort
     *
     * @param cars the array of cars to sort
     */
    private void insertionSortByPrice(Car[] cars) {
        for (int i = 1; i < cars.length; i++) {
            Car key = cars[i];
            int j = i - 1;
            while (j >= 0 && cars[j].getPrice() > key.getPrice()) {
                cars[j + 1] = cars[j];
                j = j - 1;
            }
            cars[j + 1] = key;
        }
    }//end of insertionSortByPrice

    /**
     * Sorts an array of cars by year using insertion sort
     *
     * @param cars the array of cars to sort
     */
    private void insertionSortByYear(Car[] cars) {
        for (int i = 1; i < cars.length; i++) {
            Car key = cars[i];
            int j = i - 1;
            while (j >= 0 && cars[j].getYear() > key.getYear()) {
                cars[j + 1] = cars[j];
                j = j - 1;
            }
            cars[j + 1] = key;
        }
    }
}//end of insertionSortByYear

