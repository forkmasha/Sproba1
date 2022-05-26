package library;

public class Magazine extends Item {
    private String MagazineName;

    public Magazine(int pages, String magazineName) {
        this.MagazineName = magazineName;
        this.pages = pages;
    }

    public String getMagazineName() {
        return MagazineName;
    }

    public void setMagazineName(String magazineName) {
        this.MagazineName = magazineName;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}


