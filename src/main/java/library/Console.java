package library;
import java.io.*;
import java.util.Scanner;

public class Console {
    public static void main(String[] args) throws IOException {
        Console console = new Console();
        console.doLoop();
        console.DeleteBook();
        console.BorrowBook();
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
        out.write('\n' + obj.getMagName() + " " + obj.getPages());
        out.close();
    }

    private void DeleteBook() throws IOException {
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
            if(words[j++].equals("yes"))
                System.out.println("yes");
            else
                System.out.println("no");
        }
        String confirm2 = "";
        while (true) {
            Scanner sc = new Scanner(System.in);
            confirm2 = sc.nextLine();
            if ("yes".equals(confirm2)) {
                System.out.print("Please select the book you want to delete: ");
                int choice = sc.nextInt();
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
                        else if(j % 5 == 3)
                            book.setPublishingData(words[j]);
                        else{
                            book.setBorrowed(words[j].equals("yes"));
                            ++books;
                            if (books != choice && books != count - 1)
                                out.write(book.getBookName() + " " + book.getPages() + " " + book.getAuthorName() + " " + book.getPublishingData() + " " + book.getBorrowed() + "\n");
                            else if(books != choice)
                                out.write(book.getBookName() + " " + book.getPages() + " " + book.getAuthorName() + " " + book.getPublishingData() + " " + book.getBorrowed());
                        }
                    }
                }

                out.close();

            } else if ("no".equals(confirm2)) {
                System.out.println("Make your day better");
                break;
            }
        }
    }

    public void BorrowBook() throws IOException {
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
            if(words[j++].equals("yes"))
                System.out.println("yes");
            else
                System.out.println("no");
        }
        System.out.print("Select the book you want to borrow: ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
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
                else if(j % 5 == 3)
                    book.setPublishingData(words[j]);
                else{
                    book.setBorrowed(words[j].equals("yes"));
                    ++books;
                    if(books == choice && book.getBorrowed().equals("no")){
                        book.setBorrowed(true);
                    }
                    else if(books == choice && book.getBorrowed().equals("yes")){
                        System.out.println("The book has already been borrowed.");
                    }
                    if (books <= count - 1)
                        out.write(book.getBookName() + " " + book.getPages() + " " + book.getAuthorName() + " " + book.getPublishingData() + " " + book.getBorrowed() + "\n");
                    else if(books >= count)
                        out.write(book.getBookName() + " " + book.getPages() + " " + book.getAuthorName() + " " + book.getPublishingData() + " " + book.getBorrowed());
                }
            }
        }

        out.close();
    }
    public void doLoop() throws IOException {
        String confirm = "";
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Do you want to add the book? (yes/no)");
            confirm = sc.nextLine();
            if ("yes".equals(confirm)) {
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
                if(isBorrowed.equals("yes"))
                    borrowed = true;
                Book obj = new Book(pages, authorName, publishingData, bookName, borrowed);
                System.out.println("Book Details");
                System.out.println("Book Name :" + obj.getBookName());
                System.out.println("Author Name :" + obj.getAuthorName());
                System.out.println("Pages:" + obj.getPages());
                System.out.println("Publishing Data:" + obj.getPublishingData());
                System.out.println("Borrowed: " + obj.getBorrowed());
                WriteInformation(obj);
            } else if ("no".equals(confirm)) {
                System.out.println("Maybe you want choosing smth else");
            }
            String confirm1 = "";

            while (true) {

                System.out.println("Do you want to add a magazine?(yes/no)");
                confirm1 = sc.nextLine();
                if ("yes".equals(confirm1)) {
                    System.out.println("Enter the magazine name:");
                    String MagName = sc.nextLine();
                    System.out.println("Enter the magazine pages:");
                    int pages = sc.nextInt();
                    sc.nextLine();
                    Magazine obj = new Magazine(pages, MagName);
                    System.out.println("Magazine Details");
                    System.out.println("Magazine Name :" + obj.getMagName());
                    System.out.println("Pages:" + obj.getPages());
                    WriteInformation(obj);
                    break;
                }
                else if ("no".equals(confirm1)) {
                    System.out.println("Maybe you want to delete the book?");
                    break;
                }
            }
            break;
            // else {
            // System.out.println("You write smth wrong. Please check your answer and try one more time");
            // }
        }
    }
}
