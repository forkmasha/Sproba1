package library;
import java.io.*;
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
        out.write('\n' + obj.getBookName() + " " + obj.getPages() + " " + obj.getAuthorName() + " " + obj.getPublishingData() + " " + obj.getBorrowed());
        out.close();
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
            for (int i = 0; i < count; ++i) {
                System.out.print(i + 1 + ") Book name: " + words[j++]);
                System.out.print(" Pages: " + words[j++] + " Author name: " + words[j++]);
                System.out.println(" Publishing Date: " + words[j++]);
                System.out.print("Borrowed: ");
                if (words[j++].equals("yes"))
                    System.out.println("yes");
                else
                    System.out.println("no");
            }
            String confirm2 = "";
            while (true) {
                Scanner sc1 = new Scanner(System.in);
                confirm2 = sc1.nextLine();
                if ("yes".equals(confirm2)) {
                    System.out.print("Please select the book you want to delete: ");
                    int choice = sc1.nextInt();
                    BufferedWriter out = new BufferedWriter(new FileWriter(file));
                    Book book = new Book();
                    j = 0;
                    int books = 0;
                    for (int i = 0; i < count; ++i) {
                        for (; j < words.length; ++j) {
                            if (j % 5 == 0)
                                book.setBookName(words[j]);
                            else if (j % 5 == 1)
                                book.setPages(Integer.parseInt(words[j]));
                            else if (j % 5 == 2)
                                book.setAuthorName(words[j]);
                            else if (j % 5 == 3)
                                book.setPublishingData(words[j]);
                            else {
                                book.setBorrowed(words[j].equals("yes"));
                                ++books;
                                if (books != choice && books != count - 1)
                                    out.write(book.getBookName() + " " + book.getPages() + " " + book.getAuthorName() + " " + book.getPublishingData() + " " + book.getBorrowed() + "\n");
                                else if (books != choice)
                                    out.write(book.getBookName() + " " + book.getPages() + " " + book.getAuthorName() + " " + book.getPublishingData() + " " + book.getBorrowed());
                            }
                        }
                    }
                }
                else if ("2".equals(confirm2)) {
                    System.out.println("This function is not available now. Sorry");

                    break;
                }
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
            for (int i = 0; i < count; ++i) {
                System.out.print(i + 1 + ") Book name: " + words[j++]);
                System.out.print(" Pages: " + words[j++] + " Author name: " + words[j++]);
                System.out.println(" Publishing Date: " + words[j++]);
                System.out.print("Borrowed: ");
                if (words[j++].equals("yes"))
                    System.out.println("yes");
                else
                    System.out.println("no");
            }
            System.out.print("Select the book you want to borrow: ");
            Scanner sc4 = new Scanner(System.in);
            int choice = sc4.nextInt();
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            Book book = new Book();
            j = 0;
            int books = 0;
            for (int i = 0; i < count; ++i) {
                for (; j < words.length; ++j) {
                    if (j % 5 == 0)
                        book.setBookName(words[j]);
                    else if (j % 5 == 1)
                        book.setPages(Integer.parseInt(words[j]));
                    else if (j % 5 == 2)
                        book.setAuthorName(words[j]);
                    else if (j % 5 == 3)
                        book.setPublishingData(words[j]);
                    else {
                        book.setBorrowed(words[j].equals("yes"));
                        ++books;
                        if (books == choice && book.getBorrowed().equals("no")) {
                            book.setBorrowed(true);
                        } else if (books == choice && book.getBorrowed().equals("yes")) {
                            System.out.println("The book has already been borrowed.");
                        }
                        if (books <= count - 1)
                            out.write(book.getBookName() + " " + book.getPages() + " " + book.getAuthorName() + " " + book.getPublishingData() + " " + book.getBorrowed() + "\n");
                        else if (books >= count)
                            out.write(book.getBookName() + " " + book.getPages() + " " + book.getAuthorName() + " " + book.getPublishingData() + " " + book.getBorrowed());
                    }
                }
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