package library;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Item {
    protected int pages;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    /*public int setPages(int pages) {
        if (pages < 1) {
            pages = 1;
        }
    }
    //return (this.pages = pages);
    // } */
    public static void main(String args[]) {

        List<Book> listBook = booksFromfile();

        System.out.println(listBook);
        System.out.println("****************************");

        listBook.get(0).setAuthorName("myAuthorName");
        listBook.get(0).setBookName("myBookName");
        listBook.get(0).setPublishingData("myPublishingData");

        System.out.println(listBook);

        booksToFile(listBook);
    }
    private static List<Book> booksFromfile() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Book> listBook = null;
        try {
            listBook = objectMapper
                    .readValue(Paths.get("book.json").toFile(),
                            objectMapper.getTypeFactory().constructCollectionType(List.class, Book.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listBook;
    }

    private static void booksToFile(List<Book> books) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File("book.json"), books);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
