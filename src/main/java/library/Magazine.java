package library;

public class Magazine extends Item {
    private String MagName;

    public Magazine(int pages, String magName) {
        this.MagName = magName;
        this.pages = pages;
    }

    public String getMagName() {
        return MagName;
    }

    public void setMagName(String magName) {
        this.MagName = magName;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}


