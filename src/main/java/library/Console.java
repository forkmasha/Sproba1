package library;
import java.io.*;
import java.util.Scanner;

public class Console {
    public static void main(String[] args) throws IOException {
        Console console = new Console();
        console.doLoop();
        console.DeleteBook();
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
        int count = words.length / 4;
        int j = 0;
        for (int i = 0; i < count; ++i) {
            System.out.print(i + 1 + ") Book name: " + words[j++]);
            System.out.print(" Pages: " + words[j++] + " Author name: " + words[j++]);
            System.out.println(" Publishing Date: " + words[j++]);
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
                        if (j % 4 == 0)
                            book.setBookName(words[j]);
                        else if (j % 4 == 1)
                            book.setPages(Integer.parseInt(words[j]));
                        else if (j % 4 == 2)
                            book.setAuthorName(words[j]);
                        else {
                            book.setPublishingData(words[j]);
                            ++books;
                            if (books != choice)
                                out.write(book.getBookName() + " " + book.getPages() + " " + book.getAuthorName() + " " + book.getPublishingData() + "\n");
                        }
                    }
                }

                out.close();

            } else if ("no".equals(confirm2)) {
                System.out.println("Make your day better");
            }
        }
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
