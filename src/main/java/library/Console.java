package library;


import java.io.*;
import java.util.Scanner;


public class Console {
    public static void main(String[] args) throws IOException {
        Console   console = new Console();
        console.doLoop();
    }
    private static void WriteInformation(Book obj) throws IOException {

            String filePath = "1.txt";
            File file = new File(filePath);
            BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
            out.write('\n' + obj.getBookName() + " " + obj.getPages() + " " + obj.getAuthorName() + " " + obj.getPublishingData());
            out.close();
        }

    public void doLoop() throws IOException {
        Scanner sc=new Scanner(System.in);


        for(int i = 0; i < 3; ++i){
            System.out.println("Enter the Book name:");
            String bookName=sc.nextLine();
            System.out.println("Enter the book pages:");
            int pages=sc.nextInt();
            sc.nextLine();
            System.out.println("Enter the Author name:");
            String authorName=sc.nextLine();
            System.out.println("Enter the Publishing Date:");
            String publishingData=sc.nextLine();
            Book obj=new Book(pages, authorName,publishingData,bookName);

            System.out.println("Book Details");
            System.out.println("Book Name :"+obj.getBookName());
            System.out.println("Author Name :"+obj.getAuthorName());
            System.out.println("Pages:"+obj.getPages());
            WriteInformation(obj);



        }
    }
}