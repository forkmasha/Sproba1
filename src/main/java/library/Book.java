package library;

public class Book extends Item{
    private String authorName;
    private String publishingData;
    private String bookName;

    public Book(int pages, String authorName, String publishingData, String bookName) {
        this.pages = pages;
        this.authorName = authorName;
        this.publishingData = publishingData;
        this.bookName = bookName;
    }

    public Book() {
        this.pages =0;
        this.authorName ="default";
        this.publishingData ="default";
         this.bookName ="default";
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


    public String getAuthorName() {
        return authorName;
    }

    public String getPublishingData() {
        return publishingData;
    }

    public String getBookName() {
        return bookName;
    }

    @Override
    public String toString() {
        return "Book{" +
                "pages=" + pages +
                ", authorName='" + authorName + '\'' +
                ", publishingData='" + publishingData + '\'' +
                ", bookName='" + bookName + '\'' +
                '}';
    }
}
