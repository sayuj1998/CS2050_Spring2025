package GuidedExploration;

public class ShresthaTestSimpleRectangle {

    public static void main(String[] args) {

        // Rectangle with length and width of 1
        SimpleRectangle rectangle1 = new SimpleRectangle();

        // Rectangle with length of 5 and width of 10
        SimpleRectangle rectangle2 = new SimpleRectangle(5,10);

        // Rectangle with length of 1 and width of 1
        SimpleRectangle rectangle3 = new SimpleRectangle(-30,-100);

        // Printing Rectangle 1 Area and Perimeter
        System.out.println("Rectangle 1: Area = " + rectangle1.getArea() +" Perimeter = " +rectangle1.getPerimeter());

        // Printing Rectangle 2 Area and Perimeter
        System.out.println("Rectangle 2: Area = " + rectangle2.getArea() +" Perimeter = " +rectangle2.getPerimeter());

        // Printing Rectangle 3 Area and Perimeter (Both negative values)
        System.out.println("Rectangle 3: Area = " + rectangle3.getArea() +" Perimeter = " +rectangle3.getPerimeter());

    } // main

} // TestSimpleRectangle

class SimpleRectangle {
    double length;
    double width;

    SimpleRectangle() {
        length = 1;
        width = 1;
    }

    // Sets length and width to 1 if not positive
    SimpleRectangle(double newLength, double newWidth) {
        if (newLength > 0) {
            this.length = newLength;
        } else {
            this.length = 1;
        }

        if (newWidth > 0) {
            this.width = newWidth;
        } else {
            this.width = 1;
        }
    }

    public double getArea() {
        return length * width;
    }

    public double getPerimeter() {
        return 2 * (length + width);
    }

    // Sets length to 1 if not positive
    public void setLength(double newLength) {
        if (newLength > 0) {
            this.length = newLength;
        } else {
            this.length = 1;
        }
    }

    // Sets width to 1 if not positive
    public void setWidth(double newWidth) {
        if (newWidth > 0) {
            this.width = newWidth;
        } else {
            this.width = 1;
        }
    }

} // GuidedExploration.SimpleRectangle
