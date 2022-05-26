package library;

public class Book extends Item {
    private String authorName;
    private String publishingData;
    private String bookName;
    private boolean isBorrowed;

    public Book(int pages, String authorName, String publishingData, String bookName, boolean isBorrowed) {
        this.pages = pages;
        this.authorName = authorName;
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
                "pages=" + pages +
                ", authorName='" + authorName + '\'' +
                ", publishingData='" + publishingData + '\'' +
                ", bookName='" + bookName + '\'' +
                '}';
    }

}