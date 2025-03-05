import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of floors for the car vending machine: ");
        int floors = scanner.nextInt();
        System.out.print("Enter the number of spaces for the car vending machine: ");
        int spaces = scanner.nextInt();

        VendingMachine vendingMachine = new VendingMachine(floors, spaces);

        boolean running = true;
        while (running) {
            System.out.println("\n=== Car Vending Machine Menu ===");
            System.out.println("1. Load Car Data");
            System.out.println("2. Display Vending Machine");
            System.out.println("3. Retrieve a Car");
            System.out.println("4. Print Sorted Inventory (Price)");
            System.out.println("5. Print Sorted Inventory (Year)");
            System.out.println("6. Exit");
            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.print("Enter the file name: ");
                String filename = scanner.next();
                readCarFromFile(filename, vendingMachine);
            } else if (choice == 2) {
                vendingMachine.displayVendingMachine();
            } else if (choice == 3) {
                System.out.print("Enter floor to retrieve car: ");
                int floor = scanner.nextInt() - 1;
                System.out.print("Enter space to retrieve car: ");
                int space = scanner.nextInt() - 1;
                vendingMachine.retrieveCar(floor, space);
            } else if (choice == 4) {
                vendingMachine.printSortedInventoryByPrice();
            } else if (choice == 5) {
                vendingMachine.printSortedInventoryByYear();
            } else if (choice == 6) {
                System.out.println("Exiting program. Goodbye!");
                running = false;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

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
    }
}

class Car {
    private int floor;
    private int space;
    private int year;
    private double price;
    private String make;
    private String model;

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

class VendingMachine {
    private int floors;
    private int spaces;
    private Car[][] inventory;

    public VendingMachine(int floors, int spaces) {
        this.floors = floors;
        this.spaces = spaces;
        this.inventory = new Car[floors][spaces];
    }

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
    }

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
    }

    public void retrieveCar(int floor, int space) {
        Car car = inventory[floor][space];
        if (car == null) {
            System.out.println("No car located at Floor " + (floor + 1) + " Location " + (space + 1));
        } else {
            System.out.println("Car retrieved from Floor " + (floor + 1) + " Location " + (space + 1) + ": " + car);
        }
    }

    public void printSortedInventoryByPrice() {
        Car[] cars = flatten2DArrayTo1DArray();
        insertionSort(cars, "price");
        System.out.println("Sorted Inventory by Price:");
        printCars(cars);
    }

    public void printSortedInventoryByYear() {
        Car[] cars = flatten2DArrayTo1DArray();
        insertionSort(cars, "year");
        System.out.println("Sorted Inventory by Year:");
        printCars(cars);
    }

    private void printCars(Car[] cars) {
        for (int i = 0; i < cars.length; i++) {
            if (cars[i] != null) {
                System.out.println(cars[i]);
            }
        }
    }

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

        Car[] flattenedCars = new Car[count];
        for (int i = 0; i < count; i++) {
            flattenedCars[i] = cars[i];
        }
        return flattenedCars;
    }

    private void insertionSort(Car[] cars, String sortBy) {
        for (int i = 1; i < cars.length; i++) {
            Car key = cars[i];
            int j = i - 1;

            if (sortBy.equals("price")) {
                while (j >= 0 && cars[j].getPrice() > key.getPrice()) {
                    cars[j + 1] = cars[j];
                    j = j - 1;
                }
            } else if (sortBy.equals("year")) {
                while (j >= 0 && cars[j].getYear() > key.getYear()) {
                    cars[j + 1] = cars[j];
                    j = j - 1;
                }
            }
            cars[j + 1] = key;
        }
    }
}
