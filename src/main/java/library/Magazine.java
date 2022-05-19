package library;

public class Magazine extends Item {
    private String MagName;


    public Magazine(String magName, int pages) {
        MagName = magName;
        this.pages = pages;
    }

    public String getMagName() {
        return MagName;
    }

    public void setMagName(String magName) {
        MagName = magName;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}

