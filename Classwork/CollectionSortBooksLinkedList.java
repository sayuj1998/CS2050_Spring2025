package Classwork;

import java.util.*;

/**
 *
 */
public class CollectionSortBooksLinkedList
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {

        // Step 1: Create a LinkedList (dynamic)
        LinkedList<Book> bookInventory = new LinkedList<>();

        bookInventory.add(new Book("Unmasking AI", "Dr. Joy Buolamwini", 2023));
        bookInventory.add(new Book("Hello World", "Hannah Fry", 2018));
        bookInventory.add(new Book("The Mathematics of Love", "Hannah Fry", 2015));
        bookInventory.add(new Book("Weapons of Math Destruction", "Cathy O’Neil", 2016));
        bookInventory.add(new Book("Race After Technology", "Ruha Benjamin", 2019));

        System.out.println("Original LinkedList of books:");
        for (Book currentBook : bookInventory)
        {
            System.out.println(currentBook);
        }

        System.out.println();

        // Step 2: Convert to ArrayList for sorting
        List<Book> books = new ArrayList<>(bookInventory);

        // Step 3: Sort by Title
        System.out.println("Books sorted by title:");
        books.sort(Comparator.comparing(Book::getTitle));
        for (Book currentBook : books)
        {
            System.out.println(currentBook);
        }

        System.out.println();

        // Step 4: Sort by year (newest to oldest)
        System.out.println("Books sorted by year: ");
        books.sort(Comparator.comparing(Book::getYear));
        for (Book currentBook : books)
        {
            System.out.println(currentBook);
        }

        System.out.println();

        // Step 5: Sort by author then title
        System.out.println("Books sorted by author then title: ");
        books.sort(Comparator.comparing(Book::getAuthor).thenComparing(Book::getTitle));
        for (Book currentBook : books)
        {
            System.out.println(currentBook);
        }

        System.out.println();

        // Step 6: Add Queue for signing out books
        Queue<Book> signOutQueue = new LinkedList<>();

        // Simulate students requesting to sign out books
        signOutQueue.add(bookInventory.get(0)); // Unmasking AI
        signOutQueue.add(bookInventory.get(2)); // The Mathematics of Love

        System.out.println();

        System.out.println("Sign-out queue:");
        for (Book currentBook : signOutQueue) {
            System.out.println(currentBook);
        }

        System.out.println();

        // Step 7: Process the sign-out queue
        System.out.println("Processing sign-outs:");
        signOutQueue.clear();

        //Step 8: Search by author
        System.out.println("\nSearching for books by Hannah Fry:");
        List<Book> searchBooksResult = findBooksByAuthor(bookInventory, "Hannah Fry");
        printBooks(searchBooksResult, "Hannah Fry", -1);

        //Step 9: search books by author and year
        //create a method that
        List<Book> foundBooks = findBooks(bookInventory, "Hannah Fry", 2018);
        printBooks(foundBooks, "Hannah Fry", 2018);

        // Testing for a second book
        List<Book> foundBooks2 = findBooks(bookInventory, "Cathy O’Neil", 2016);
        printBooks(foundBooks2, "Cathy O’Neil", 2016);

        // Adding TreeMap
        TreeMap<Integer, List<Book>> booksByYear = new TreeMap<>();
        for (Book currentBook : bookInventory) {
            booksByYear.putIfAbsent(currentBook.getYear(), new ArrayList<>());
            booksByYear.get(currentBook.getYear()).add(currentBook);
        }

        // Iterate through booksByYear.entrySet()
        // Books are automatically grouped and printed in year order
        for (Map.Entry<Integer, List<Book>> entry : booksByYear.entrySet()) {
            System.out.println("\nYear: " + entry.getKey());
            for (Book book : entry.getValue()) {
                System.out.println(book);
            }
        }

        // Step 10
        System.out.println("\nStep 10 HashMap of books by title:");
        Map<String, Book> bookMapByTitle = new HashMap<>();
        for (Book book : bookInventory) {
            bookMapByTitle.put(book.getTitle(), book);
        }

        String searchTitle = "Hello World";
        if (bookMapByTitle.containsKey(searchTitle)) {
            System.out.println("Found book: " + bookMapByTitle.get(searchTitle));
        } else {
            System.out.println("Classwork.Book not found: " + searchTitle);
        }

        // Allow user to enter a book title, author and year
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Author: ");
        String author = scanner.nextLine();

        System.out.print("Enter Year: ");
        int year = scanner.nextInt();

        if (bookMapByTitle.containsKey(title + author + year)) {
            System.out.println("Found book: " + bookMapByTitle.get(title + author + year));
        } else {
            System.out.println("Classwork.Book not found: " + title + " " + author + " " + year);
        }


    }
    public static List<Book> findBooksByAuthor(List<Book> inventory, String author) {
        List<Book> results = new ArrayList<>();
        for (Book currentBook : inventory) {
            if (currentBook.getAuthor().equalsIgnoreCase(author)) {
                results.add(currentBook);
            }
        }
        return results;
    }

    public static List<Book> findBooks(List<Book> inventory, String author, int year) {
        List<Book> results = new ArrayList<>();
        for (Book currentBook : inventory) {
            if (currentBook.getAuthor().equalsIgnoreCase(author) && currentBook.getYear() == year) {
                results.add(currentBook);
            }
        }
        results.sort(Comparator.comparing(Book::getTitle));
        return results;
    }

    public static void printBooks(List<Book> books, String author, int year) {
        String label;
        if (year != -1) {
            label = " in " + year;
        } else {
            label = "";
        }

        if (books.isEmpty()) {
            System.out.println("\nNo books found by " + author + label + ".");
        } else {
            System.out.println("\nBooks by " + author + label + ":");
            for (Book currentBook : books) {
                System.out.println(currentBook);
            }
        }
    }
}

class Book
{
    private String title;
    private String author;
    private int year;

    public Book(String title, String author, int year)
    {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle()
    {
        return title;
    }

    public String getAuthor()
    {
        return author;
    }

    public int getYear()
    {
        return year;
    }

    public String toString()
    {
        return title + " by " + author + " (" + year + ")";
    }
}