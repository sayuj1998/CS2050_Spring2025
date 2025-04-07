package Classwork;/*
 * Classwork.Stack Example
 * Add comments to explain the code
 */

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class StackTest {
    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();
        Stack<String> stack2 = new Stack<>();

       // Queue<String> queue = new Queue<>();
       // queue.add("A");

        System.out.println("Pushing elements: A, B, C");
        stack2.push("A");
        stack2.push("B");
        stack2.push("C");

        System.out.println("Pushing elements: 10, 20, 30");
        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Classwork.Stack after pushing: " + stack2);

        String popped2 = stack2.pop();
        System.out.println("Popped element: " + popped2);
        System.out.println("Classwork.Stack after popping: " + stack2);

        String topElement2 = stack2.peek();
        System.out.println("Top element: " + topElement2);

        System.out.println("Is stack empty? " + stack.isEmpty());

        popped2 = stack2.pop();
        System.out.println("Popped element: " + popped2);
        System.out.println("Classwork.Stack after popping: " + stack2);

        popped2 = stack2.pop();
        System.out.println("Popped element: " + popped2);
        System.out.println("Classwork.Stack after popping: " + stack2);

        popped2 = stack2.pop();
        System.out.println("Popped element: " + popped2);
        System.out.println("Classwork.Stack after popping: " + stack2);



        System.out.println("Classwork.Stack after pushing: " + stack2);

        int popped = stack.pop();
        System.out.println("Popped element: " + popped);
        System.out.println("Classwork.Stack after popping: " + stack);

        int topElement = stack.peek();
        System.out.println("Top element: " + topElement);

        System.out.println("Is stack empty? " + stack.isEmpty());

        popped = stack.pop();
        System.out.println("Popped element: " + popped);
        System.out.println("Classwork.Stack after popping: " + stack2);

        popped = stack.pop();
        System.out.println("Popped element: " + popped);
        System.out.println("Classwork.Stack after popping: " + stack);

        popped = stack.pop();
        System.out.println("Popped element: " + popped);
        System.out.println("Classwork.Stack after popping: " + stack);
    }
}



class Stack<T> {


    private ArrayList<T> items;


    public Stack() {
        this.items = new ArrayList<>();
    }


    public boolean isEmpty() {
        return this.items.isEmpty();
    }


    public void push(T item) {
        this.items.add(item);
    }


    public T pop() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Classwork.Stack is empty.");
        }
        return this.items.remove(items.size() - 1);
    }


    public T peek() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Classwork.Stack is empty.");
        }
        return this.items.get(items.size() - 1);
    }


    public int size() {
        return this.items.size();
    }


    @Override
    public String toString() {
        if (!this.items.isEmpty()) {
            return "bottom ->" + this.items.toString() + "<- top";
        } else {
            return "<<empty stack>>";
        }
    }
}

