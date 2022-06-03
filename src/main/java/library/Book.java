package library;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Book extends Item {
    public static final String BOOKS_TXT = "books.txt";
    public static final String DELIMETER = "\t";
    private String authorName;
    private String publishingData;
    private String bookName;


    public Book(int pages, String authorName, String publishingData, String bookName, boolean isBorrowed) {
        this();
        if (pages > 0) {
            this.pages = pages;
        }
        if (authorName != null && authorName.length() > 0) {
            this.authorName = authorName;
        }
        this.publishingData = publishingData;
        this.bookName = bookName;
        this.isBorrowed = isBorrowed;
    }

    public Book(Book book) {
        this.pages = book.getPages();
        this.authorName = book.getAuthorName();
        this.publishingData = book.getPublishingData();
        this.bookName = book.getBookName();
        this.isBorrowed = "yes".equals(book.getBorrowed());
    }

    public Book() {
        this.pages = 0;
        this.authorName = "default";
        this.publishingData = "default";
        this.bookName = "default";
        this.isBorrowed = false;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setPublishingData(String publishingData) {
        this.publishingData = publishingData;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setBorrowed(boolean isBorrowed) {
        this.isBorrowed = isBorrowed;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getPublishingData() {
        return publishingData;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBorrowed() {
        if (isBorrowed) {
            return "yes";
        }
        return "no";
    }

    @Override
    public String toString() {
        return "Book{" +
                "authorName='" + authorName + '\'' +
                ", publishingData='" + publishingData + '\'' +
                ", bookName='" + bookName + '\'' +
                ", pages=" + pages +
                ", isBorrowed=" + isBorrowed +
                '}';
    }

    public String toFileLine() {
        StringBuilder sb = new StringBuilder();
        sb.append(bookName).append(DELIMETER);
        sb.append(pages).append(DELIMETER);
        sb.append(authorName).append(DELIMETER);
        sb.append(publishingData).append(DELIMETER);
        sb.append(isBorrowed ? "yes" : "no");
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
//        testWriteBooks();
//        testReadBooks();
//        readBookFromUserInput();
        getTestBooks();
    }

    private static void testReadBookFromUserInput() throws IOException {
        Scanner scanner = new Scanner(System.in);
        Book book = readBookFromUserInput(scanner);
        List<Book> books = new ArrayList<>();
        books.add(book);
        writeBooks(books);
        System.out.println(book);
    }

    private static Book readBookFromUserInput(Scanner scanner) {
        System.out.println("Please enter book name");
        String bookName = scanner.nextLine();
        System.out.println("Please enter pages number");
        int pages = Integer.valueOf(scanner.nextLine());
        String author = scanner.nextLine();
        String publishDate = scanner.nextLine();
        boolean isBorrowed = "yes".equals(scanner.nextLine());

        Book book = new Book(pages, author, publishDate, bookName, isBorrowed);
        return book;
    }

    private static void testReadBooks() throws FileNotFoundException {
        List<Book> books = readBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public static List<Book> readBooks() throws FileNotFoundException {
        File file = new File(BOOKS_TXT);
        Scanner scanner = new Scanner(file);
        List<Book> books = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String lineBook = scanner.nextLine();
            Book book = bookFromLine(lineBook);
            books.add(book);
        }
        return books;
    }

    public static Book bookFromLine(String line) {
        Book book = new Book();
        String[] parts = line.split(DELIMETER);
        book.setBookName(parts[0]);
        book.setPages(Integer.valueOf(parts[1]));
        book.setAuthorName(parts[2]);
        book.setPublishingData(parts[3]);
        book.setBorrowed("yes".equals(parts[4]));
        return book;
    }

    private static void testWriteBooks() throws IOException {
        List<Book> books = getTestBooks();
        writeBooks(books);
    }

    private static List<Book> getTestBooks() {
        List<Book> books = new ArrayList<>();

        Book book1 = new Book();
        book1.setBookName("book 1");
        book1.setAuthorName("author 1");
        book1.setPages(100);
        book1.setPublishingData("01.01.2000");
        books.add(book1);

        Book book2 = new Book();
        book2.setBookName("book 2");
        book2.setAuthorName("author 2");
        book2.setPages(200);
        book2.setPublishingData("01.01.2000");
        books.add(book2);

        Book book3 = new Book();
        book3.setBookName("book 3");
        book3.setAuthorName("author 3");
        book3.setPages(300);
        book3.setPublishingData("01.01.2000");
        books.add(book3);


        printWithIndex(books);
        return books;
    }

    public static Book removeBook(List<Book> books, int bookIndex) {
        Book removeBook = books.remove(bookIndex - 1);
        return removeBook;
    }

    public static boolean borrowBook(List<Book> books, int bookIndex) {
        Book book = books.get(bookIndex - 1);
        if (book.isBorrowed) {
            return false;
        }
        book.setBorrowed(true);
        return true;

    }

    public static void writeBooks(List<Book> books) throws IOException {
        String fileName = "books.txt";
        writeItems(fileName, books);
    }

    public static void writeItems(String fileName, List<? extends Item> items) throws IOException {
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);
        for (Item item : items) {
            fw.write(item.toFileLine());
        }
        fw.close();
    }

    private static void printWithIndex(List<? extends Item> items) {
        int index = 1;
        for (Item item : items) {
            System.out.println(index++ + ") " + item.toString());
        }
    }
}