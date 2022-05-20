package library;
import java.io.*;
import java.util.Scanner;

public class Console {
    public static void main(String[] args) throws IOException {
        Console console = new Console();
        console.doLoop();
    }

    private static void WriteInformation(Book obj) throws IOException {

        String filePath = "1.txt";
        File file = new File(filePath);
        BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
        out.write('\n' + obj.getBookName() + " " + obj.getPages() + " " + obj.getAuthorName() + " " + obj.getPublishingData());
        out.close();
    }

    private static void WriteInformation(Magazine obj) throws IOException {

        String filePath = "2.txt";
        File file = new File(filePath);
        BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
        out.write('\n' + obj.getMagName() + " " + obj.getPages() + " ");
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
                Book obj = new Book(pages, authorName, publishingData, bookName);

                System.out.println("Book Details");
                System.out.println("Book Name :" + obj.getBookName());
                System.out.println("Author Name :" + obj.getAuthorName());
                System.out.println("Pages:" + obj.getPages());
                WriteInformation(obj);
            } else if ("no".equals(confirm)) {
                System.out.println("Maybe you want choosing smth else");
                System.out.println("Do you want to add a magazine?(yes/no)");
                confirm = sc.nextLine();
                if ("yes".equals(confirm)) {
                    System.out.println("Enter the magazine  name:");
                    String MagName = sc.nextLine();
                    System.out.println("Enter the magazine pages:");
                    int pages = sc.nextInt();
                    sc.nextLine();
                    Magazine obj = new Magazine(pages, MagName);}

                 else if ("no".equals(confirm)) {
                        System.out.println("I do not add any functions. Sorry, but I cant help you");}
                 break;

                // else {
                   // System.out.println("You write smth wrong. Please check your answer and try one more time");
               // }
            }
        }
    }
}