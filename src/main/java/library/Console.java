package library;
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Console {
    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);
        int x = 0;
        String s = "";

        Console console = new Console();
        System.out.println("Welcome to my library");

        while (true) {
            while (!"5".equals(s)) {
                System.out.println("1. If you want to add smth please click 1");
                System.out.println("2. If you want to delete smth please click 2");
                System.out.println("3. If you want to borrow smth click 3");
                System.out.println("4. If you want ro sort smth enter 4");
                System.out.println("5.If you want to Exit please click 5");
                s = scan.next();

                try {
                    x = Integer.parseInt(s);
                } catch (NumberFormatException e) {
                    System.out.println("You Write smth wrong");
                }

                switch (x) {
                    case 1:
                        console.addLoop();
                        break;
                    case 2:
                        console.DeleteBook();
                        break;
                    case 3:
                        console.BorrowBook();
                        break;
                    case 4:
                        console.SortBook();
                        break;
                }
            }
            System.out.println("Good bye!");
            break;
        }
    }


    private static void WriteInformation(Book obj) throws IOException {

        String filePath = "1.txt";
        File file = new File(filePath);
        BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
        out.write(obj.getBookName() + " " + obj.getPages() + " " + obj.getAuthorName() + " " + obj.getPublishingData() + " " + obj.getBorrowed() + '\n');
        out.close();
    }

    private static void PrintBook(Book[] books, int length){
        for(int i = 0; i < length; ++i){
            System.out.print(i + 1 + ") Book name: " + books[i].getBookName());
            System.out.print(" Pages: " + books[i].getPages() + " Author name: " + books[i].getAuthorName());
            System.out.println(" Publishing Date: " + books[i].getPublishingData());
            System.out.print("Borrowed: ");
            if (books[i].getBorrowed().equals("yes"))
                System.out.println("yes");
            else
                System.out.println("no");
        }
    }
    private static void WriteInformation(Magazine obj) throws IOException {

        String filePath = "2.txt";
        File file = new File(filePath);
        BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
        out.write('\n' + obj.getMagazineName() + " " + obj.getPages());
        out.close();
    }

    private void DeleteBook() throws IOException {
        String confirm = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("1.If you want to delete the book enter 1" + "\n" + "2.If you want to delete the magazine enter 2");
        confirm = sc.nextLine();
        if ("1".equals(confirm)) {
            System.out.println("Do you want to delete the book?(yes/no)");
            String confirm2 = "";
            while (true) {
                Scanner sc1 = new Scanner(System.in);
                confirm2 = sc1.nextLine();
                if ("yes".equals(confirm2)) {
                    String filePath = "1.txt";
                    File file = new File(filePath);
                    Scanner scanner = new Scanner(file);
                    String[] words;
                    StringBuilder text = new StringBuilder();
                    while (scanner.hasNextLine()) {
                        text.append(scanner.nextLine());
                        text.append(" ");
                    }
                    words = text.toString().split(" ");
                    int count = words.length / 5;
                    int j = 0;
                    Book[] books = new Book[count];
                    for(int i = 0; i < count; ++i){
                        books[i] = new Book();
                    }
                    for (int i = 0; i < count; ++i) {
                        books[i].setBookName(words[j++]);
                        books[i].setPages(Integer.parseInt(words[j++]));
                        books[i].setAuthorName(words[j++]);
                        books[i].setPublishingData(words[j++]);
                        books[i].setBorrowed(words[j++].equals("yes"));
                    }
                    PrintBook(books, count);
                    System.out.print("Please select the book you want to delete: ");
                    int choice = sc1.nextInt();
                    BufferedWriter out = new BufferedWriter(new FileWriter(file));
                    for (int i = 0; i < count; ++i) {
                        if(choice != i + 1){
                            out.write(books[i].getBookName() + " " + books[i].getPages() + " " + books[i].getAuthorName() + " " + books[i].getPublishingData() + " " + books[i].getBorrowed() + "\n");
                        }
                    }
                    out.close();
                    break;
                }
                else if ("2".equals(confirm2)) {
                    System.out.println("This function is not available now. Sorry");

                    break;
                }
            }
        }
    }

    public void SortBook() throws FileNotFoundException {
        String filePath = "1.txt";
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        String[] words;
        StringBuilder text = new StringBuilder();
        while (scanner.hasNextLine()) {
            text.append(scanner.nextLine());
            text.append(" ");
        }
        words = text.toString().split(" ");
        int count = words.length / 5;
        int j = 0;
        Book[] books = new Book[count];
        for(int i = 0; i < count; ++i){
            books[i] = new Book();
        }
        for(int i = 0; i < count; ++i){
            books[i].setBookName(words[j++]);
            books[i].setPages(Integer.parseInt(words[j++]));
            books[i].setAuthorName(words[j++]);
            books[i].setPublishingData(words[j++]);
            books[i].setBorrowed(words[j++].equals("yes"));
        }
        System.out.println("Types of sorting:");
        System.out.println("1 - sort books alphabetically");
        System.out.println("2 - sort books by number of pages");
        System.out.println("3 - sort books by author");
        System.out.print("Select an item: ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch (choice){
            case 1:{
                Arrays.sort(books, Comparator.comparing(Book::getBookName));
                PrintBook(books, count);
                break;
            }
            case 2:{
                for(int i = 0; i < count - 1; ++i){
                    for(int k = i + 1; k < count; ++k){
                        if(books[i].getPages() > books[k].getPages()){
                            Book firstbook = new Book(books[i]);
                            Book secondbook = new Book(books[k]);
                            books[i] = secondbook;
                            books[k] = firstbook;
                        }
                    }
                }
                PrintBook(books, count);
                break;
            }
            case 3:{
                Arrays.sort(books, Comparator.comparing(Book::getAuthorName));
                PrintBook(books, count);
                break;
            }
        }
    }
    public void BorrowBook() throws IOException {
        String confirm3 = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("1.If you want to borrow the book enter 1" + "\n" + "2.If you want to borrow the magazine enter 2");
        confirm3 = sc.nextLine();
        if ("1".equals(confirm3)) {

            String filePath = "1.txt";
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            String[] words;
            StringBuilder text = new StringBuilder();
            while (scanner.hasNextLine()) {
                text.append(scanner.nextLine());
                text.append(" ");
            }
            words = text.toString().split(" ");
            int count = words.length / 5;
            int j = 0;
            Book[] books = new Book[count];
            for(int i = 0; i < count; ++i){
                books[i] = new Book();
            }
            for (int i = 0; i < count; ++i) {
                books[i].setBookName(words[j++]);
                books[i].setPages(Integer.parseInt(words[j++]));
                books[i].setAuthorName(words[j++]);
                books[i].setPublishingData(words[j++]);
                books[i].setBorrowed(words[j++].equals("yes"));
            }
            PrintBook(books, count);
            System.out.print("Select the book you want to borrow: ");
            Scanner sc4 = new Scanner(System.in);
            int choice = sc4.nextInt();
            if(books[choice - 1].getBorrowed().equals("yes"))
                System.out.println("The book has already been borrowed.");
            else {
                BufferedWriter out = new BufferedWriter(new FileWriter(file));
                for (int i = 0; i < count; ++i) {
                    if(i != choice - 1)
                        out.write(books[i].getBookName() + " " + books[i].getPages() + " " + books[i].getAuthorName() + " " + books[i].getPublishingData() + " " + books[i].getBorrowed() + "\n");
                    else
                        out.write(books[i].getBookName() + " " + books[i].getPages() + " " + books[i].getAuthorName() + " " + books[i].getPublishingData() + " " + "yes" + "\n");
                }
                out.close();
            }
        }
        else {
            System.out.println("This function is in progress");
        }
    }

    public void addLoop() throws IOException {
        String confirm = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("1.If you want to add the book enter 1" + "\n" + "2.If you want to add the magazine enter 2");
        confirm = sc.nextLine();
        if ("1".equals(confirm)) {
            System.out.println("Enter the Book name:");
            String bookName = sc.nextLine();
            System.out.println("Enter the book pages:");
            int pages = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter the Author name:");
            String authorName = sc.nextLine();
            System.out.println("Enter the Publishing Date:");
            String publishingData = sc.nextLine();
            System.out.println("Enter the borrowed book:");
            String isBorrowed = sc.nextLine();
            boolean borrowed = false;
            if (isBorrowed.equals("yes"))
                borrowed = true;
            Book obj = new Book(pages, authorName, publishingData, bookName, borrowed);
            System.out.println("Book Details");
            System.out.println("Book Name :" + obj.getBookName());
            System.out.println("Author Name :" + obj.getAuthorName());
            System.out.println("Pages:" + obj.getPages());
            System.out.println("Publishing Data:" + obj.getPublishingData());
            System.out.println("Borrowed: " + obj.getBorrowed());
            WriteInformation(obj);
        } else if ("2".equals(confirm)) {
            System.out.println("Do you want to add a magazine?(yes/no)");
            confirm = sc.nextLine();
            if ("yes".equals(confirm)) {
                System.out.println("Enter the magazine name:");
                String MagName = sc.nextLine();
                System.out.println("Enter the magazine pages:");
                int pages = sc.nextInt();
                sc.nextLine();
                Magazine obj = new Magazine(pages, MagName);
                System.out.println("Magazine Details");
                System.out.println("Magazine Name :" + obj.getMagazineName());
                System.out.println("Pages:" + obj.getPages());
                WriteInformation(obj);
            }
        }
    }
}