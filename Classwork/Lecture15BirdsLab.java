package Classwork;

public class Lecture15BirdsLab {
    public static void main(String[] args) {
        Penguin myBird = new Penguin("Classwork.Penguin", "Bobo", 15);
        myBird.displayFact();
    }
}

interface Swimmer {
    void swim();
}

abstract class Birds {
    private String type;
    private String name;
    private int swimSpeed;

    public Birds(String type, String name, int swimSpeed) {
        this.type = type;
        this.name = name;
        this.swimSpeed = swimSpeed;
    }

    public abstract void displayFact();
}

class Penguin extends Birds implements Swimmer {
    public Penguin(String type, String name, int swimSpeed) {
        super(type, name, swimSpeed);
    }

    @Override
    public void displayFact() {
        System.out.println("I can't fly but I'm the fastest swimmer and deepest diver.");
    }

    @Override
    public void swim() {

    }
}//end Classwork.Penguin

class Duck extends Birds implements Swimmer {
    public Duck(String type, String name, int swimSpeed) {
        super(type, name, swimSpeed);
    }

    @Override
    public void displayFact() {
        System.out.println("A duck's highest documented flight was at 21,000 feet!");
    }

    @Override
    public void swim() {

    }
}//end Classwork.Duck

abstract class Sootytern extends Birds {
    public Sootytern(String type, String name, int swimSpeed) {
        super(type, name, swimSpeed);
    }

    @Override
    public void displayFact() {
        System.out.println("A duck's highest documented flight was at 21,000 feet!");
    }
}//end Classwork.Sootytern

abstract class Ostrich extends Birds {
    public Ostrich(String type, String name, int swimSpeed) {
        super(type, name, swimSpeed);
    }

    @Override
    public void displayFact() {
        System.out.println("Who needs flying when you're the biggest bird on earth!");
    }
}//end Classwork.Ostrich


