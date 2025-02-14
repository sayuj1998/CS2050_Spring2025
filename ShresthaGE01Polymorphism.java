import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ShresthaGE01Polymorphism {
    public static void main(String[] args) {
        try {
            File animalsFile = new File("Animals.txt");
            Scanner fileScanner = new Scanner(animalsFile);

            int arraySize = fileScanner.nextInt();
            fileScanner.nextLine();

            Animal[] animals = new Animal[arraySize];

            for (int index = 0; index < arraySize; index++) {
                String type = fileScanner.next().trim();
                String name = fileScanner.next().trim();
                String food = fileScanner.next().trim();
                int weight = fileScanner.nextInt();
                int sleep = fileScanner.nextInt();
                String location = fileScanner.nextLine().trim();

                if (type.equals("Bear")) {
                    animals[index] = new Bear(name, food, weight, sleep, location);
                } else if (type.equals("Elephant")) {
                    animals[index] = new Elephant(name, food, weight, sleep, location);
                } else if (type.equals("Monkey")) {
                    animals[index] = new Monkey(name, food, weight, sleep, location);
                } else if (type.equals("Sloth")) {
                    animals[index] = new Sloth(name, food, weight, sleep, location);
                }
            }

            fileScanner.close();

            for (int index = 0; index < animals.length; index++) {
                Animal animal = animals[index];

                System.out.print("Animal[" + index + "] is a ");
                if (animal instanceof Bear) {
                    System.out.print("Bear\n");
                } else if (animal instanceof Elephant) {
                    System.out.print("Elephant\n");
                } else if (animal instanceof Monkey) {
                    System.out.print("Monkey\n");
                } else if (animal instanceof Sloth) {
                    System.out.print("Sloth\n");
                }

                System.out.println(animal);

                animal.eat();
                animal.sleep();
                animal.swim();
                System.out.println();

            } // end for loop

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + e.getMessage());
        } // end try/catch
    } // end Main
} // end Polymorphism class

class Animal {
    private String name;
    private String food;
    private int weight;
    private int sleep;
    private String location;

    public Animal(String name, String food, int weight, int sleep, String location) {
        this.name = name;
        this.food = food;
        this.weight = weight;
        this.sleep = sleep;
        this.location = location;
    }

    public String getName() {
        return name;
    }
    public String getFood() {
        return food;
    }
    public int getWeight() {
        return weight;
    }
    public int getSleep() {
        return sleep;
    }
    public String getLocation() {
        return location;
    }

    public void eat() {
        System.out.println("Animal is eating");
    }
    public void sleep() {
        System.out.println("Animal is sleeping");
    }
    public void swim() {
        System.out.println("Animal is swimming");
    }
} // end Animal

class Bear extends Animal {
    public Bear(String name, String food, int weight, int sleep, String location) {
        super(name, food, weight, sleep, location);
    }

    @Override
    public void eat() {
        System.out.println("Bear is eating " + getFood());
    }

    @Override
    public void sleep() {
        System.out.println("Bear is sleeping " + getSleep() + " hours");
    }

    @Override
    public void swim() {
        System.out.println("Bear is swimming");
    }

    @Override
    public String toString() {
        return "Bear: Name: " + getName() + " - Weighs: " + getWeight() + " lbs - Sleeps: " + getSleep() + " hours - Location: " + getLocation();
    }

} // end Bear

class Elephant extends Animal {
    public Elephant(String name, String food, int weight, int sleep, String location) {
        super(name, food, weight, sleep, location);
    }

    @Override
    public void sleep() {
        System.out.println("Elephant is sleeping " + getSleep() + " hours");
    }

    @Override
    public String toString() {
        return "Elephant: Name: " + getName() + " - Weighs: " + getWeight() + " lbs - Sleeps: " + getSleep() + " hours - Location: " + getLocation();
    }

} // end Elephant

class Monkey extends Animal {
    public Monkey(String name, String food, int weight, int sleep, String location) {
        super(name, food, weight, sleep, location);
    }

    @Override
    public void eat() {
        System.out.println("Monkey is eating " + getFood());
    }

    @Override
    public void swim() {
        System.out.println("Monkey is swimming");
    }

    @Override
    public String toString() {
        return "Monkey: Name: " + getName() + " - Weighs: " + getWeight() + " lbs - Sleeps: " + getSleep() + " hours - Location: " + getLocation();
    }

} // end Monkey

class Sloth extends Animal {
    public Sloth(String name, String food, int weight, int sleep, String location) {
        super(name, food, weight, sleep, location);
    }

    @Override
    public String toString() {
        return "Sloth: Name: " + getName() + " - Weighs: " + getWeight() + " lbs - Sleeps: " + getSleep() + " hours - Location: " + getLocation();
    }

} // end Sloth

