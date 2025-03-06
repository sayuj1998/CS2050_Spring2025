package GuidedExploration;

public class ShresthaGE012DArray {
    public static void main(String[] args) {
        Car[][] cars = new Car[2][3];

        cars[0][0] = new Car("Ford");
        cars[0][1] = new Car("Dodge");
        cars[0][2] = new Car("Toyota");
        cars[1][0] = new Car("Hyundai");
        cars[1][1] = new Car("Chevrolet");
        cars[1][2] = new Car("Subaru");

        printCars(cars);

    }//end Main method

    public static void printCars(Car[][] carArray) {
        int numRows = carArray.length;
        int numCols = carArray[0].length;

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                carArray[row][col].printMake();
            }
            System.out.println();
        }
    }
}//end GE Class

class Car {
    private String make;

    public Car() {
        this.make = "Unknown";
    }

    public Car(String make) {
        this.make = make;
    }

    public void printMake() {
        System.out.print(this.make + " ");
    }
}//end Car class