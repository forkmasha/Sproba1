package library;

public class Magazine extends Item {
    private String MagName;
    private boolean isBorrowed;

    public Magazine(int pages, String magName,boolean isBorrowed) {
        this.MagName = magName;
        this.pages = pages;
        this.isBorrowed = isBorrowed;
    }

    public Magazine(Magazine magazine) {
        this.pages = magazine.getPages();
        this.MagName = magazine.getMagazineName();
        this.isBorrowed = "yes".equals(magazine.getBorrowed());
    }
    public Magazine(){
        this.MagName = "default";
        this.pages = 0;
        this.isBorrowed = false;
    }

    public String getMagazineName() {
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
    public void setBorrowed(boolean isBorrowed) {
        this.isBorrowed = isBorrowed;
    }
    public String getBorrowed() {
        if (isBorrowed) {
            return "yes";
        }
        return "no";
    }

}